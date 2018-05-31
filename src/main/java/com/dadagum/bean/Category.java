package com.dadagum.bean;

import javax.validation.constraints.Pattern;

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
