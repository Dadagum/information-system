package com.dadagum.dao;

public interface CategoryDao {

    /**
     * 判断某一个信息类型是否存在
     * @param type_id 类型id
     * @return
     */
    public boolean hasCategory(int type_id);
}
