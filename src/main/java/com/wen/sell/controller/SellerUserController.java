package com.wen.sell.controller;

import com.lly835.bestpay.rest.type.Get;
import com.wen.sell.config.ProjectUrlConfig;
import com.wen.sell.constant.CookieConstant;
import com.wen.sell.constant.RedisConstant;
import com.wen.sell.enums.ResultEnum;
import com.wen.sell.pojo.SellerInfo;
import com.wen.sell.service.SellerInfoService;
import com.wen.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {

        //1.根据openid去数据库比对
        SellerInfo sellerInfo = sellerInfoService.findSellerByOpenid(openid);
        if (null == sellerInfo) {
            log.error("【登录】登录参数不正确{}", openid);
            map.put("msg", ResultEnum.LOGIN_ERROR.getMsg());
            map.put("url", "/sell/seller/order/list");

            return new ModelAndView("common/error", map);
        }

        //2.设置token至redies
        String token = UUID.randomUUID().toString();
        Integer expire =RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN, token), openid, expire);

        //3.设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return new ModelAndView("redirect:" + projectUrlConfig.sell +"/sell/seller/order/list");
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpServletResponse response,
                               HttpServletRequest request,
                               Map<String, Object> map) {

        //1.从cookie中查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        //2.清楚redis
        redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN, cookie.getValue()));
        //3.清楚cookie
        CookieUtil.set(response, CookieConstant.TOKEN, null, 0);

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("url", "/sell/seller/order/list");

        return new ModelAndView("common/success", map);
    }
}
