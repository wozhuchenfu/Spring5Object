package com.qi.controllers;

import com.qi.test.PropertiesTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerDemo extends BaseController{

    @Autowired
    private PropertiesTest propertiesTest;

    @GetMapping("/test")
    public String test(){
        System.out.println("===========propertiesTest.getAppName()=============="+propertiesTest.getAppName());
        return "index";
    }

    @GetMapping("/error")
    public String error(){

        System.out.println("================");
        try {
            int a = 1/0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return "index";
    }
}
