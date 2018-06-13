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
     * 增加一个活动
     * @param activity 活动信息
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> addActivityInfo(@Valid ActivityInformation activity, BindingResult bindingResult, HttpSession session){
        System.out.println(activity);
        // check if session exists
        User user = (User) session.getAttribute("user");
        if (user == null || user.getPriority().equals("org")) return new ReturnJson<>(null, "您没有权限增加活动", false);
        if (bindingResult.hasErrors()){
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list) System.out.println(fieldError.getField());
            return new ReturnJson<>(null, "请检查您的输入信息", false);
        }
        activity.setUser_id(user.getUser_id());
        try {
             activityService.addActivity(activity);
             return new ReturnJson<>(new ActivityInfoDto(activity), "您的活动请求我们已经收到，活动有待审核！", true);
        } catch (RuntimeException e){
            return new ReturnJson<>(null, "发生一个意外的错误，添加活动失败", false);
        }
    }

    /**
     * 显示已经经过审核的活动
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> getList(){
        List<ActivityInfoDto> result = activityService.getPassInfoList();
        return new ReturnJson<>(result, "成功", true);
    }

    /**
     * 查看一个活动的详细信息
     * @param info_id
     * @return
     */
    @RequestMapping(value = "/info/{info_id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> getSpecificInfo(@PathVariable int info_id){
        ActivityInfoDto result = activityService.getSpecificInfo(info_id);
        return new ReturnJson<>(result, "成功", true);
    }

    /**
     * 更新一个活动的信息
     * @param activity
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.PATCH, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> updateActivityInfo(@Valid ActivityInformation activity, BindingResult bindingResult, HttpSession session){
        System.out.println(activity);
        // check if session exists
        User user = (User) session.getAttribute("user");
        if (user == null || user.getPriority().equals("org")) return new ReturnJson<>(null, "您无权更新活动", false);
        if (bindingResult.hasErrors()){
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list) System.out.println(fieldError.getField());
            return new ReturnJson<>(null, "请检查您的输入信息", false);
        }
        try {
            return activityService.updateActivity(activity, user.getUser_id()) ? new ReturnJson<>(new ActivityInfoDto(activity), "更新成功", true): new ReturnJson<>(null, "您无权更新活动", false);
        } catch (RuntimeException e){
            return new ReturnJson<>(null, "发生一个意外的错误，更新活动失败", false);
        }
    }



}
