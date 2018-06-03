package com.dadagum.dto;

import com.dadagum.bean.ActivityInformation;
import com.dadagum.util.ConvertUtil;

public class ActivityInfoDto {

    private String org_name;
    private String introduction;
    private String name;
    private String start_time;
    private String end_time;

    @Override
    public String toString() {
        return "ActivityInfoDto{" +
                "org_name='" + org_name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", name='" + name + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }

    public ActivityInfoDto(){

    }

    public ActivityInfoDto(ActivityInformation activityInformation){
        org_name = activityInformation.getOrg_name();
        introduction = activityInformation.getIntroduction();
        name = activityInformation.getName();
        start_time = ConvertUtil.DateToString(activityInformation.getStart_time());
        end_time = ConvertUtil.DateToString(activityInformation.getEnd_time());
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
}
