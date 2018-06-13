package com.dadagum.service;

public interface AdminService {

    /**
     * 通过一个活动
     * @param info_id
     * @return
     */
    public boolean passActivity(int info_id);

    /**
     * 否决一个活动
     * @param type_id
     * @return
     */
    public boolean denyActivity(int type_id);


    /**
     * 删除一个已经通过的活动
     * @param infoId
     * @return
     */
    public boolean deleteActivity(int infoId);

    /**
     * 删除一个评论
     * @param comment_id
     * @return
     */
    public boolean deleteComment(int comment_id);


}
