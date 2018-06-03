package com.dadagum.controller;

import com.dadagum.dto.UserDto;
import com.dadagum.dto.ReturnJson;
import com.dadagum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * user register
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
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
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> login(@Valid UserDto loginDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getErrorCount());
            return new ReturnJson<>(null, "check your username and password", false);
        }
        System.out.println(loginDto);
        return new ReturnJson<>(loginDto, "login successfully", true);
    }

    /**
     * update personal profile
     * @param userDto
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/profile/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> update(@Valid UserDto userDto, BindingResult bindingResult){
        System.out.println(userDto);
        if(bindingResult.hasErrors() ){
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list) System.out.println(fieldError.getField());
            return new ReturnJson<>(null, "fail to update", false);
        }
        // default user
        int user_id = 1; // TODO
        userService.update(userDto, user_id);
        userDto.setPassword(null);
        return new ReturnJson<>(userDto, "update person information successfully", true);
    }

    /**
     * get personal profile info
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> getPersonalInfo(){
        // default user
        int user_id = 1; // TODO
        UserDto result = userService.getPersonalInfo(user_id);
        return new ReturnJson<>(result, "successfully", true);
    }

}
