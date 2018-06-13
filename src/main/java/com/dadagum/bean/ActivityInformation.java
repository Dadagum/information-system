package com.dadagum.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 组织或者社团发布的一个活动
 */
public class ActivityInformation {

    private int type_id;
    private int info_id;
    private String org_name;
    private int user_id;
    private String introduction;
    private String name;

    @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss")
    private Date start_time;

    @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss")
    private Date end_time;

    public int getInfo_id() {
        return info_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setInfo_id(int info_id) {

        this.info_id = info_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "ActivityInformation{" +
                "info_id=" + info_id +
                ", org_name='" + org_name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", name='" + name + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }


}
