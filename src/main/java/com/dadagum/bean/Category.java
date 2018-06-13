package com.dadagum.bean;

import javax.validation.constraints.Pattern;

/**
 * 表示信息的类型，暂时只有活动和团队招募
 */
public class Category {

    @Pattern(regexp = "\\w{2,30}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
