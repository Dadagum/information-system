package com.dadagum.controller;

import com.dadagum.bean.TeamRequest;
import com.dadagum.bean.User;
import com.dadagum.dto.ReturnJson;
import com.dadagum.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
    @RequestMapping(value = "/request", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> postTeamRequest(TeamRequest request, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) return new ReturnJson<>(null, "请先登陆", false);
        System.out.println(request);
        teamService.postTeamRequest(request);
        return new ReturnJson<>(request, "团队招募信息发布成功", true);
    }
}
