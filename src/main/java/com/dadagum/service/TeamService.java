package com.dadagum.service;

import com.dadagum.bean.TeamRequest;
import com.dadagum.dto.TeamRequestDto;

import java.util.List;

public interface TeamService {

    /**
     * 发布一个团队招募请求
     * @param request
     */
    public void postTeamRequest(TeamRequest request);

    /**
     * 获得全部团队招募请求
     * @return
     */
    public List<TeamRequestDto> getTeamRequestList();
}
