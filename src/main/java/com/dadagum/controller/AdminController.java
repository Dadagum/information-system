package com.dadagum.controller;

import com.dadagum.dto.ReturnJson;
import com.dadagum.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/administration")
public class AdminController {

    @Autowired
    private ActivityService activityService;

    //TODO
    /**
     * delete an activity
     * @param info_id
     * @return
     */
    @RequestMapping(value = "/deletion/{info_id}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> deleteActivityInfo(@PathVariable int info_id){
        System.out.println(info_id);
        return activityService.deleteActivity(info_id) ? new ReturnJson<>(null, "delete successfully", true) : new ReturnJson<>(null, "fail to delete for the activity does not exist", false);
    }

    /**
     * accept an activity request
     * @param type_id
     * @param info_id
     * @return
     */
    @RequestMapping(value = "/accept/{type_id}/{info_id}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> passActicityRequest(@PathVariable int type_id, @PathVariable int info_id){
        return null;
    }

    @RequestMapping(value = "/rejection/{type_id}/{info_id}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> denyActivityRequest(@PathVariable int type_id, @PathVariable int info_id){
        return null;
    }
}
