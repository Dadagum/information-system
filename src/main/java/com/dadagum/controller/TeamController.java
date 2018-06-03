package com.dadagum.controller;

import com.dadagum.bean.TeamRequest;
import com.dadagum.dto.ReturnJson;
import com.dadagum.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

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
        teamService.postTeamRequest(request);
        return new ReturnJson<>(request, "successfully", true);
    }
}
