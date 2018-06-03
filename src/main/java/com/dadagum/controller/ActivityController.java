package com.dadagum.controller;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.dto.ReturnJson;
import com.dadagum.service.ActivityService;
import com.dadagum.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ReturnJson<?> addActivityInfo(@Valid ActivityInfoDto activityInfoDto, BindingResult bindingResult){
        System.out.println(activityInfoDto);
        if (bindingResult.hasErrors()){
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list) System.out.println(fieldError.getField());
            return new ReturnJson<>(null, "fail to add a activity", false);
        }
        activityService.addActivity(activityInfoDto);
        return new ReturnJson<>(activityInfoDto, "create successfully", true);
    }

    //TODO
    /**
     * delete an activity
     * @param infoId
     * @return
     */
    @RequestMapping(value = "/{infoId}/deletion", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<ActivityInformation> deleteActivityInfo(@PathVariable int infoId){
        System.out.println(infoId);
        return activityService.deleteActivity(infoId) ? new ReturnJson<>(null, "delete successfully", true) : new ReturnJson<>(null, "fail to delete for the activity does not exist", false);
    }


}
