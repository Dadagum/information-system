package com.dadagum.controller;

import com.dadagum.api.ReturnJson;
import com.dadagum.bean.ActivityInformation;
import com.dadagum.bean.Information;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/information")
public class InformationController {

    /**
     * add an activity
     * @param info
     * @return
     */
    @RequestMapping(value = "/addition", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson<ActivityInformation> addActivityInfo(ActivityInformation info){
        System.out.println(info);
        return new ReturnJson<>(info, "add an activity successfully", true);
    }

    /**
     * update an activity info
     * @param info
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson<ActivityInformation> updateActivityInfo(ActivityInformation info){
        System.out.println(info);
        return new ReturnJson<>(info, "update successfully", true);
    }

    /**
     * delete an activity
     * @param infoId
     * @return
     */
    @RequestMapping(value = "/deletion", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson<ActivityInformation> deleteActivityInfo(int infoId){
        System.out.println(infoId);
        return new ReturnJson<>(null, "delete successfully", true);
    }

    /**
     * view specific information list
     * @param model
     * @return
     */
    @RequestMapping(value = "/{model}", method = RequestMethod.GET)
    @ResponseBody
    public  ReturnJson<List<? extends Information>> viewInfo(@PathVariable("model") String model){
        List<ActivityInformation> result = new ArrayList<>();
        result.add(new ActivityInformation());
        return new ReturnJson<>(result, "successfully ", true);
    }
}
