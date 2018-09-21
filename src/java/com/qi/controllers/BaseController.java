package com.qi.controllers;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

@Controller
public class BaseController {
    Logger logger = LoggerFactory.getLogger(BaseController.class);

    public void handleRequest(HttpServletRequest request,HttpServletResponse response) {
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }
    }

    //controller异常处理
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,String> handlerControllerException(RuntimeException runTimeException){
        logger.error(runTimeException.getMessage());
        Map<String,String> map = Maps.newHashMap();
        map.put("runTimeException", "出错了！");
        return map;
    }
}
