package com.dadagum.controller;

import com.dadagum.dto.ReturnJson;
import com.dadagum.bean.Category;
import com.dadagum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/type")
public class InfoTypeController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/addition", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> addType(@Valid Category category, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ReturnJson<>(null, "addition failure", false);
        }
        categoryService.addType(category);
        return new ReturnJson<>(category, "create successfully", true);
    }
}
