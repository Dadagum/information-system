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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        try {
            return activityService.addActivity(activity, user.getUser_id()) ? new ReturnJson<>(new ActivityInfoDto(activity), "您的活动请求我们已经收到，活动有待审核！", true): new ReturnJson<>(null, "您无权添加活动", false);
        } catch (RuntimeException e){
            return new ReturnJson<>(null, "发生一个意外的错误，添加活动失败", false);
        }
    }

    /**  TODO
     * get activity info list(title, org_name)
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> getList(){
        return null;
    }

    /** TODO
     * view a specific information
     * @param info_id
     * @return
     */
    @RequestMapping(value = "/{info_id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> getSpecificInfo(@PathVariable int info_id){
        return null;
    }

    /** TODO
     * update a activity information
     * @param activityInfoDto
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application")
    @ResponseBody
    public ReturnJson<?> updateActivityInfo(@Valid ActivityInfoDto activityInfoDto, BindingResult bindingResult){
        return null;
    }


}
