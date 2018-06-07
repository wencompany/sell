package com.wen.sell.aspect;

import com.wen.sell.constant.CookieConstant;
import com.wen.sell.constant.RedisConstant;
import com.wen.sell.exception.SellerAuthorizeException;
import com.wen.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.wen.sell.controller.Sell*.*(..))" + "&& !execution(public * com.wen.sell.controller.SellerUserController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify() {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //1.查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (null == cookie) {
            log.warn("【登录】Cookie为空，查不到cookie");
            throw new SellerAuthorizeException();
        }

        //2.去redies中查询
        String redisValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN, cookie.getValue()));

        if (StringUtils.isEmpty(redisValue)) {
            log.warn("【登录】redis查询不到结果");
            throw new SellerAuthorizeException();
        }
    }
}
