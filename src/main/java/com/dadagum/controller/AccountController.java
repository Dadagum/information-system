package com.dadagum.controller;

import com.dadagum.api.ReturnJson;
import com.dadagum.api.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    @RequestMapping(value = "/registration/execution", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<UserDto> register(UserDto userDto){
        System.out.println(userDto);
        userDto.setPassword(null);
        return new ReturnJson<UserDto>(userDto, "create successfully", true);
    }
}
