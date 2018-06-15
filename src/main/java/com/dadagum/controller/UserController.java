package com.dadagum.controller;

import com.dadagum.bean.User;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.dto.UserDto;
import com.dadagum.dto.ReturnJson;
import com.dadagum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册一个账号
     * @param user
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> register(@Valid User user, BindingResult bindingResult, @RequestParam String r_password){
        System.out.println(user + " " + r_password);
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list) System.out.println(fieldError.getField());
            return new ReturnJson<>(null, "请检查输入的信息", false);
        }
        List<String> error = userService.addUser(user, r_password);
        if (error != null){
            Map<String, List<String>> result = new HashMap<>();
            result.put("msg", error);
            return new ReturnJson<>(result, "注册失败", false);
        }
        return new ReturnJson<>(new UserDto(user), "注册成功", true);
    }

    /**
     * 用户登陆
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> login(User user, HttpSession session){
        if (user.getUsername() == null || "".equals(user.getUsername()) || user.getPassword() == null || "".equals(user.getPassword())) return new ReturnJson<>(null, "请填好用户名或者信息", false);
        int user_id = userService.loginCheck(user);
        System.out.println(user_id);
        if (user_id != 0){
            user = userService.getUser(user.getUsername());
            String priority = userService.getUserPriority(user_id);
            if (priority != null) user.setPriority(priority);
            System.out.println(user.getPriority());
            session.setAttribute("user", user);
            return new ReturnJson<>(null, "登陆成功", true);
        }
        return new ReturnJson<>(null, "用户不存在或者密密码错误", false);
    }

    /**
     * 更新个人信息(暂时为密码)
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.PATCH, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> update(@RequestBody Map<String, String> password, HttpSession session){
        if (password.get("password") == null) return new ReturnJson<>(null, "请填写修改后的密码", false);
        System.out.println(password);
        User s_user = (User) session.getAttribute("user");
        if(s_user == null) return new ReturnJson<>(null, "请先登陆", false);
        User user = new User();
        user.setPassword(password.get("password"));
        user.setUser_id(s_user.getUser_id());
        System.out.println(user.getUser_id());
        return userService.update(user) ? new ReturnJson<>(new UserDto(user), "更新成功", true) : new ReturnJson<>(null, "您无权更新他人信息", false);
    }

    /**
     * 得到个人信息
     * @return
     */
    @RequestMapping(value = "/profile/{user_id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> getPersonalInfo(@PathVariable int user_id){
        UserDto result = userService.getPersonalInfo(user_id);
        return new ReturnJson<>(result, "成功", true);
    }

    /**
     * 得到个人的收藏信息
     * @param session
     * @return
     */
    @RequestMapping(value = "/favor", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> getFavorList( HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null ) return new ReturnJson<>(null, "请先登陆", false);
        List<ActivityInfoDto> result = userService.getFavorList(user.getUser_id());
        return new ReturnJson<>(result, "成功", true);
    }

    /**
     * 得到个人收藏列表
     * @param info_id
     * @param session
     * @return
     */
    @RequestMapping(value = "/favor", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> addFavorActivity(@RequestParam int info_id, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) return new ReturnJson<>(null, "请先登陆", false);
        return userService.addFavorActivity(user.getUser_id(), info_id) ? new ReturnJson<>(null, "关注成功！", true) : new ReturnJson<>(null, "活动不存在", false);
    }

}
