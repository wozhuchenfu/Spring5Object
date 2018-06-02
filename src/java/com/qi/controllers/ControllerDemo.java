package com.qi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerDemo {

    @GetMapping("/test")
    public String test(){
        System.out.println("=========================");
        return "index";
    }
}
