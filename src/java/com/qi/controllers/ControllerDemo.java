package com.qi.controllers;

import com.qi.exceptionUtils.BizError;
import com.qi.exceptionUtils.ObjectException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerDemo extends BaseController{

    @GetMapping("/test")
    public String test(){
        System.out.println("=========================");
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
