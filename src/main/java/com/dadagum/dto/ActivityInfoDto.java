package com.dadagum.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class ActivityInfoDto {

    @Length(min = 3, max = 30)
    private String org_name;

    @Length(min = 3, max = 1000)
    private String introduction;

    @Length(min = 3, max = 30)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date start_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date end_time;

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
}
