package com.dadagum.service;

import com.dadagum.bean.TeamRequest;
import com.dadagum.dao.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamDao teamDao;

    public void postTeamRequest(TeamRequest request){
        teamDao.addTeamRequest(request);
    }

}
