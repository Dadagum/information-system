package com.dadagum.controller;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.bean.User;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.dto.ReturnJson;
import com.dadagum.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * add an activity
     * @param
     * @return
     */
    @RequestMapping(value = "/addition", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> addActivityInfo(@Valid ActivityInformation activity, BindingResult bindingResult, HttpSession session){
        System.out.println(activity);
        // check if session exists
        User user = (User) session.getAttribute("user");
        if (user == null) return new ReturnJson<>(null, "请先登陆", false);
        if (bindingResult.hasErrors()){
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list) System.out.println(fieldError.getField());
            return new ReturnJson<>(null, "请检查您的输入信息", false);
        }
        activity.setUser_id(user.getUser_id());
        try {
            return activityService.addActivity(activity, user.getUser_id()) ? new ReturnJson<>(activity, "您的活动请求我们已经收到，活动有待审核！", true): new ReturnJson<>(null, "您无权添加活动", false);
        } catch (RuntimeException e){
            return new ReturnJson<>(null, "发生一个意外的错误，添加活动失败", false);
        }
    }

    /**
     * get pass activity information list
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> getList(){
        List<ActivityInfoDto> result = activityService.getPassInfoList();
        return new ReturnJson<>(result, "成功", true);
    }

    /**
     * view a specific information
     * @param info_id
     * @return
     */
    @RequestMapping(value = "/{info_id}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> getSpecificInfo(@PathVariable int info_id){
        ActivityInfoDto result = activityService.getSpecificInfo(info_id);
        return new ReturnJson<>(result, "成功", true);
    }

    /** TODO
     * update a activity information
     * @param activity
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> updateActivityInfo(@Valid ActivityInformation activity, BindingResult bindingResult, HttpSession session){
        System.out.println(activity);
        // check if session exists
        User user = (User) session.getAttribute("user");
        if (user == null) return new ReturnJson<>(null, "请先登陆", false);
        if (bindingResult.hasErrors()){
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list) System.out.println(fieldError.getField());
            return new ReturnJson<>(null, "请检查您的输入信息", false);
        }
        try {
            return activityService.updateActivity(activity, user.getUser_id()) ? new ReturnJson<>(new ActivityInfoDto(activity), "您的活动请求我们已经收到，活动有待审核！", true): new ReturnJson<>(null, "您无权更新活动", false);
        } catch (RuntimeException e){
            return new ReturnJson<>(null, "发生一个意外的错误，更新活动失败", false);
        }
    }

    //TODO
    @RequestMapping(value = "/favor/addition", method = RequestMethod.POST, produces = "application.json")
    @ResponseBody
    public ReturnJson<?> addFavor(@RequestParam int user_id, @RequestParam int info_id, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUser_id() != user_id) return new ReturnJson<>(null, "请先登陆", false);
        return null;
    }

}
