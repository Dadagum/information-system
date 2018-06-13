package com.dadagum.service.impl;

import com.dadagum.bean.TeamRequest;
import com.dadagum.dao.TeamDao;
import com.dadagum.dto.TeamRequestDto;
import com.dadagum.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    public void postTeamRequest(TeamRequest request){
        teamDao.addTeamRequest(request);
    }

    @Override
    public List<TeamRequestDto> getTeamRequestList() {
        return teamDao.getTeamRequestList();
    }

}
