package com.dadagum.util;

import com.dadagum.bean.Comment;
import com.dadagum.bean.User;

import java.util.ArrayList;
import java.util.List;

public class DateValidator {

    private DateValidator(){

    }

    public static List<String> validateAllRegister(User user, String rpassword){
        List<String> result = new ArrayList<>();
        if (!user.getPassword().equals(rpassword)) {
            result.add("输入的两次密码不一致");
            return result;
        }
        return null;
    }

    public static List<String> validateComment(Comment comment){
        return null;
    }


}
