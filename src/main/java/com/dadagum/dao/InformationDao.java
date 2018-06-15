package com.dadagum.dao;

public interface InformationDao {

    /**
     * 增加一个活动需求
     * @param type_id 类型id
     * @param info_id 信息id
     */
    public void addRequest(int type_id, int info_id);

    /**
     * 删除一个活动需求
     * @param type_id 类型id
     * @param info_id 信息id
     */
    public void deleteRequest(int type_id, int info_id);

    /**
     * 判断某一个信息是否存在
     * @param info_id 信息id
     * @param type_id 类型id
     * @return
     */
    public boolean hasInfo(int info_id, int type_id);
}
