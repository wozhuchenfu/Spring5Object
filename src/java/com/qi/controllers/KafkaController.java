package com.qi.controllers;

import com.qi.kafka.KafkaProcucer;
import com.qi.kafka.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: Qijingyu
 * @Date: 2019/9/26 18:24
 * @Description:
 */
@Controller
public class KafkaController {

    @Autowired
    private KafkaProcucer kafkaProcucer;

    @RequestMapping("/kafka")
    public void test(){
        kafkaProcucer.send("hello");
    }
}
