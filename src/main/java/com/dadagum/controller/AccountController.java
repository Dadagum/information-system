package com.dadagum.controller;

import com.dadagum.api.UserDto;
import com.dadagum.api.ReturnJson;
import com.dadagum.dao.UserDao;
import com.dadagum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    /**
     * user register
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/registration/execution", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> register(@Valid UserDto userDto, BindingResult bindingResult){
        System.out.println(userDto);
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getErrorCount());
            return new ReturnJson<>(null, "check the information that you have typed", false);
        }
        userService.addUser(userDto);
        userDto.setPassword(null);
        return new ReturnJson<>(userDto, "create successfully", true);
    }

    /**
     * user login
     * @param loginDto
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/login/execution", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> login(@Valid UserDto loginDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getErrorCount());
            return new ReturnJson<>(null, "check your username and password", false);
        }
        System.out.println(loginDto);
        return new ReturnJson<>(loginDto, "login successfully", true);
    }

}
