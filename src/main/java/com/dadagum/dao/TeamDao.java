package com.dadagum.dao;

import com.dadagum.bean.TeamRequest;
import com.dadagum.dto.TeamRequestDto;

import java.util.List;

public interface TeamDao {

    /**
     * 发布团队招募信息
     * @param request 团队招募信息
     */
    public void addTeamRequest(TeamRequest request);

    /**
     * 获取所有团队招募信息
     * @return 团队招募信息列表
     */
    public List<TeamRequestDto> getTeamRequestList();
}
