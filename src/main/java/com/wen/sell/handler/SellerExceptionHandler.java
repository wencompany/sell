package com.wen.sell.handler;

import com.wen.sell.config.ProjectUrlConfig;
import com.wen.sell.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("login");
//      return new ModelAndView("redirect:".
//                concat(projectUrlConfig.wechatOpenAuthorize).
//                concat("/sell/wechat/qrAuthorize").
//                concat("?returnUrl=").
//                concat(projectUrlConfig.wechatOpenAuthorize).
//                concat("/sell/seller/login"));
    }
}
