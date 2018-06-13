package com.dadagum.service;

import com.dadagum.bean.User;
import com.dadagum.dto.ActivityInfoDto;
import com.dadagum.dto.UserDto;

import java.util.List;

public interface UserService {

    /**
     * 增加一个用户
     * @param user 用户信息
     * @param r_password 重复密码
     * @return
     */
    public List<String> addUser(User user, String r_password);

    /**
     * 用户登陆
     * @param user 用户名和密码
     * @return
     */
    public int loginCheck(User user);

    /**
     * 更新个人信息
     * @param user 新的用户信息
     * @return
     */
    public boolean update(User user);

    /**
     * 得到个人信息
     * @param user_id 用户id
     * @return
     */
    public UserDto getPersonalInfo(int user_id);

    /**
     * 得到个人收藏活动列表
     * @param user_id 用户id
     * @return
     */
    public List<ActivityInfoDto> getFavorList(int user_id);

    /**
     * 得到用户权限
     * @param user_id 用户id
     * @return
     */
    public String getUserPriority(int user_id);

    /**
     * 根据唯一的用户名得到整个用户对象
     * @param username 唯一的用户名
     * @return
     */
    public User getUser(String username);

    /**
     * 用户收藏某个活动
     * @param user_id 用户id
     * @param info_id 活动id
     * @return
     */
    public boolean addFavorActivity(int user_id, int info_id);
}
