package com.dadagum.controller;

import com.dadagum.api.ReturnJson;
import com.dadagum.api.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    /**
     * register
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/registration/execution", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<UserDto> register(UserDto userDto){
        System.out.println(userDto);
        userDto.setPassword(null);
        return new ReturnJson<UserDto>(userDto, "create successfully", true);
    }

    @RequestMapping(value = "/login/execution", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<String> login(@RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println(username + " " + password);
        return new ReturnJson<String>(username, "login successfully", true);
    }

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    @ResponseBody
//    public ReturnJson<String> index(){
//        return new ReturnJson<String>("ok", "yes", true);
//    }
}
