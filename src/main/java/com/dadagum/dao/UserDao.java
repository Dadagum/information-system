package com.dadagum.dao;

import com.dadagum.bean.User;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.dto.UserDto;

import java.util.List;

public interface UserDao {

    /**
     * 增加一个用户
     * @param user 用户信息
     */
    public void addUser(User user);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return
     */
    public boolean update(User user);

    /**
     * 获得个人信息
     * @param user_id
     * @return
     */
    public UserDto getPersonalInfo(int user_id);

    /**
     * 获得个人权限
     * @param user_id 用户id
     * @return
     */
    public String getPriority(int user_id);

    /**
     * 判断是否为普通用户
     * @param user_id 用户id
     * @return
     */
    public boolean isNormalUser(int user_id);

    /**
     * 获得个人所有收藏活动的信息列表
     * @param user_id
     * @return
     */
    public List<ActivityInfoDto> getFavorList(int user_id);

    /**
     * 判断某一个用户是否存在
     * @param user 唯一的用户名和邮箱
     * @return
     */
    public boolean hasUser(User user);

    /**
     * 根据用户名获得用户的id
     * @param username 用户名
     * @return 用户id
     */
    public int getUserIdByName(String username);

    /**
     * 判断用户名和密码是否匹配
     * @param user 用户名和密码
     * @return
     */
    public boolean isPwdMatchName(User user);

    /**
     * 根据用户名获得用户的所有信息
     * @param username 用户名
     * @return
     */
    public User getUserByName(String username);

    /**
     * 某一个用户收藏某一个活动
     * @param user_id 用户id
     * @param info_id 活动id
     */
    public void addFavor(int user_id, int info_id);


}
