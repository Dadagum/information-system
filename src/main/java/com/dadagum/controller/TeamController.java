package com.dadagum.controller;

import com.dadagum.bean.TeamRequest;
import com.dadagum.bean.User;
import com.dadagum.dto.ReturnJson;
import com.dadagum.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    /**
     * 增加一个团队招募请求
     * @param request 团队招募信息
     * @return
     */
    @RequestMapping(value = "/request", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> postTeamRequest(@Valid TeamRequest request, BindingResult bindingResult,  HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) return new ReturnJson<>(null, "请先登陆", false);
        if (bindingResult.hasErrors()){
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for (FieldError fieldError : errorList){
                System.out.println(fieldError.getField());
            }
            return new ReturnJson<>(null, "请检查输入内容", false);
        }
        System.out.println(request);
        request.setUser_id(user.getUser_id());
        teamService.postTeamRequest(request);
        request.setUser_id(-1);
        return new ReturnJson<>(request, "团队招募信息发布成功", true);
    }

    /**
     * 得到所有团队招募信息
     * @return
     */
    @RequestMapping(value = "/requests", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> getTeamRequestList(){
        return new ReturnJson<>(teamService.getTeamRequestList(), "成功", true);
    }
}
