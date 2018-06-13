package com.dadagum.service;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.dto.ActivityInfoDto;

import java.util.List;

public interface ActivityService {

    /**
     * 增加一个活动
     * @param activity 活动信息
     * @return
     */
    public void addActivity(ActivityInformation activity);

    /**
     * 更新一个活动
     * @param activity 活动信息
     * @param user_id  组织用户
     * @return
     */
    public boolean updateActivity(ActivityInformation activity, int user_id);

    /**
     * 获得所有已经通过审核的活动
     * @return
     */
    public List<ActivityInfoDto> getPassInfoList();

    /**
     * 获得某一个活动的详细信息
     * @param info_id 活动id
     * @return
     */
    public ActivityInfoDto getSpecificInfo(int info_id);

}
