package com.dadagum.controller;

import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.dto.ReturnJson;
import com.dadagum.bean.ActivityInformation;
import com.dadagum.bean.TeamRequest;
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
    @RequestMapping(value = "/activity/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<ActivityInformation> updateActivityInfo(ActivityInformation info){
        System.out.println(info);
        return informationService.updateActivityInfo(info) ? new ReturnJson<>(info, "update successfully", true) : new ReturnJson<>(null, "activity does not exist", false);
    }

    //TODO
    /**
     * delete an activity
     * @param infoId
     * @return
     */
    @RequestMapping(value = "/activity/{infoId}/deletion", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<ActivityInformation> deleteActivityInfo(@PathVariable int infoId){
        System.out.println(infoId);
        return informationService.deleteActivity(infoId) ? new ReturnJson<>(null, "delete successfully", true) : new ReturnJson<>(null, "fail to delete for the activity does not exist", false);
    }

    /**
     * view specific category info list
     * @param category
     * @return
     */
    @RequestMapping(value = "/{category}/view", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public  ReturnJson<?> viewInfo(@PathVariable("category") String category){
        List<?> result = informationService.getSpecificCategoryInfo(category);
        return result.size() == 0 ? new ReturnJson<>(null, "no information was found", false) : new ReturnJson<>(result, "view successfully", true);
    }

    /**
     * add a team recruitment request
     * @param request
     * @return
     */
    @RequestMapping(value = "/team/addition", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> postTeamRequest(TeamRequest request){
        request.setUser_id(1); //TODO : it should be from session .
        System.out.println(request);
        informationService.postTeamRequest(request);
        return new ReturnJson<>(request, "successfully", true);
    }
}
