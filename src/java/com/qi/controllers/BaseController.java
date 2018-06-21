package com.qi.controllers;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class BaseController {
    Logger logger = LoggerFactory.getLogger(BaseController.class);

    //controller异常处理
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,String> handlerControllerException(RuntimeException runTimeException){
        logger.error(runTimeException.getMessage());
        Map<String,String> map = Maps.newHashMap();
        map.put("runTimeException", runTimeException.getMessage());
        return map;
    }
}
