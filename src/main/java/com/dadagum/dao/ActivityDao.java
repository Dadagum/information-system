package com.dadagum.dao;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.dto.ActivityInfoDto;

import java.util.List;

public interface ActivityDao {

    /**
     * 增加一个活动
     * @param activity 活动信息
     * @Return 自增键值
     */
    public int addActivity(ActivityInformation activity);

    /**
     * 更新一个活动信息
     * @param info 新的活动信息
     * @return
     */
    public boolean updateActivity(ActivityInformation info);

    /**
     * 删除一个原本审核通过的活动
     * @param info_id 活动id
     * @return
     */
    public boolean deleteActivity(int info_id);

    /**
     * 得到所有通过审核的活动
     * @return
     */
    public List<ActivityInfoDto> getPassInfoList();

    /**
     * 得到所有的活动
     * @return
     */
    public List<ActivityInfoDto> getAllInfoList();

    /**
     * 得到某个活动的详细信息
     * @param info_id 活动id
     * @return
     */
    public ActivityInfoDto getSpecificInfo(int info_id);

    /**
     * 某个活动是否存在
     * @param info_id 活动id
     * @return
     */
    public boolean hasActivivy(int info_id);


}
