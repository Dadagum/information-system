package com.dadagum.dto;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.util.ConvertUtil;

public class ActivityInfoDto {

    private String org_name;
    private String introduction;
    private String name;
    private String start_time;
    private String end_time;
    private int info_id;
    private int type_id;

    public ActivityInfoDto(){

    }

    public ActivityInfoDto(ActivityInformation activity){
        org_name = activity.getOrg_name();
        introduction = activity.getIntroduction();
        name = activity.getName();
        start_time = ConvertUtil.DateToString(activity.getStart_time());
        end_time = ConvertUtil.DateToString(activity.getEnd_time());
        info_id = activity.getInfo_id();
        type_id = activity.getType_id();
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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "ActivityInfoDto{" +
                "org_name='" + org_name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", name='" + name + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", info_id=" + info_id +
                ", type_id=" + type_id +
                '}';
    }
}
