package com.dadagum.dao;

public interface AdminDao {

    /**
     * 审核通过一个活动
     * @param info_id
     * @return
     */
    public boolean passActivity(int info_id);

    /**
     * 拒绝一个活动
     * @param info_id 活动id
     * @return
     */
    public boolean denyActivity(int info_id);
}
