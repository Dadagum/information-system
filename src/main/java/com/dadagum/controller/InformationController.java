package com.dadagum.controller;

import com.dadagum.dto.ReturnJson;
import com.dadagum.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    /**
     * view specific category info list
     * @param category
     * @return
     */
    @RequestMapping(value = "/{category}/view", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public  ReturnJson<?> viewInfo(@PathVariable("category") String category){
        List<?> result = informationService.getSpecificCategoryInfo(category);
        return result == null ? new ReturnJson<>(null, "no information was found", false) : new ReturnJson<>(result, "view successfully", true);
    }

}
