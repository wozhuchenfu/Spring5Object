package com.qi.exceptionUtils;

import com.alibaba.fastjson.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class GlobalExceptionInterceptor implements HandlerExceptionResolver {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionInterceptor.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        logger.info("全局异常拦截");
        if (e instanceof ObjectException){
            returnJsonToResp(httpServletResponse,((ObjectException)e).getErrorCode());
        } else {
            returnJsonToResp(httpServletResponse,BizError.SYSTEM_ERROR);
        }
        modelAndView.addObject("exception", ((ObjectException) e).getErrorMessage());
        return modelAndView;
    }

    private void returnJsonToResp(HttpServletResponse response,BizError errorCode){
        try {
            PrintWriter writer = response.getWriter();
            switch (errorCode.getErrorCode()){
                case "001":
                    writer.write(JSONObject.toJSONString(errorCode.PARAMETER_INVALID_ERROR));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
