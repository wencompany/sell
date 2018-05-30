package com.wen.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/weixin")
@Slf4j
public class WeixinController {

    @RequestMapping(value = "auth")
    public void auth(@RequestParam("code") String code) {

        log.info("进入auth方法");
        log.info("code={}", code);

        String url = "";

        RestTemplate restTemplate = new RestTemplate();
        String object = restTemplate.getForObject(url, String.class);
        log.info("access_token={}",object);

    }
}
