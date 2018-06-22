package com.qi.controllers;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class WebFluxController extends BaseController{

    @GetMapping("/mono/hello")
    @ResponseBody
    public Mono<String> webMonoTest(){
        return Mono.just("Welcome to reactive world ~");
    }

    @GetMapping("/flux/hello")
    @ResponseBody
    public Flux<String> webFluxTest(){
        return Flux.just("Welcome to reactive world ~");
    }
}
