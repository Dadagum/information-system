package com.dadagum.controller;

import com.dadagum.bean.User;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.dto.UserDto;
import com.dadagum.dto.ReturnJson;
import com.dadagum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * user register
     * @param user
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> register(User user, @RequestParam String r_password){
        List<String> error = userService.addUser(user, r_password);
        if (error != null){
            Map<String, List<String>> result = new HashMap<>();
            result.put("msg", error);
            return new ReturnJson<>(result, "注册失败", false);
        }
        return new ReturnJson<>(new UserDto(user), "注册成功", true);
    }

    /**
     * user login
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> login(User user){
        return userService.checkIfUsernameMatchPassword(user) ? new ReturnJson<>(null, "登陆成功", true) : new ReturnJson<>(null, "用户名不存在或者密码错误", false);
    }

    /**
     * update personal profile
     * @param user
     * @return
     */
    @RequestMapping(value = "/profile/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> update(User user, HttpSession session){
        User s_user = (User) session.getAttribute("user");
        if(s_user == null) return new ReturnJson<>(null, "请先登陆", false);
        return userService.update(user, s_user.getUser_id()) ? new ReturnJson<>(new UserDto(user), "更新成功", true) : new ReturnJson<>(null, "您无权更新他人信息", false);
    }

    /**
     * get personal profile info
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> getPersonalInfo(@RequestParam int user_id){
        UserDto result = userService.getPersonalInfo(user_id);
        return new ReturnJson<>(result, "成功", true);
    }

    @RequestMapping(value = "/favor", method = RequestMethod.POST, produces = "application.json")
    @ResponseBody
    public ReturnJson<?> getFavorList(@RequestParam int user_id, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUser_id() != user_id) return new ReturnJson<>(null, "请先登陆", false);
        List<ActivityInfoDto> result = userService.getFavorList(user_id);
        return new ReturnJson<>(result, "成功", true);
    }

}
