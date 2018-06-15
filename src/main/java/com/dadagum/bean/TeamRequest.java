package com.dadagum.bean;

import javax.validation.constraints.NotNull;

/**
 * 团队招募信息
 */
public class TeamRequest {

    @NotNull
    private String title;

    @NotNull
    private String introduction;

    private int user_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
