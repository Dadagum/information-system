package com.dadagum.controller;

import com.dadagum.api.ActivityInfoDto;
import com.dadagum.api.ReturnJson;
import com.dadagum.bean.ActivityInformation;
import com.dadagum.dao.CategoryDao;
import com.dadagum.service.CategoryService;
import com.dadagum.service.InformationService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    /**
     * add an activity
     * @param
     * @return
     */
    @RequestMapping(value = "/activity/addition", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> addActivityInfo(@Valid ActivityInfoDto activityInfoDto, BindingResult bindingResult){
        System.out.println(activityInfoDto);
        if (bindingResult.hasErrors()){
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list) System.out.println(fieldError.getField());
            return new ReturnJson<>(null, "fail to add a activity", false);
        }
        informationService.addActivity(activityInfoDto);
        return new ReturnJson<>(activityInfoDto, "create successfully", true);
    }

    //TODO
    /**
     * update an activity info
     * @param info
     * @return
     */
    @RequestMapping(value = "/update/execution", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson<ActivityInformation> updateActivityInfo(ActivityInformation info){
        System.out.println(info);
        return new ReturnJson<>(info, "update successfully", true);
    }

    //TODO
    /**
     * delete an activity
     * @param infoId
     * @return
     */
    @RequestMapping(value = "/deletion/execution", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson<ActivityInformation> deleteActivityInfo(int infoId){
        System.out.println(infoId);
        return new ReturnJson<>(null, "delete successfully", true);
    }

    /**
     * view specific information list
     * @param category
     * @return
     */
    @RequestMapping(value = "/{category}/view", method = RequestMethod.GET)
    @ResponseBody
    public  ReturnJson<?> viewInfo(@PathVariable("category") String category){
        List<?> result = informationService.getSpecificCategoryInfo(category);
        return result == null ? new ReturnJson<>(null, "no information was found", false) : new ReturnJson<>(result, "view successfully", true);
    }
}
