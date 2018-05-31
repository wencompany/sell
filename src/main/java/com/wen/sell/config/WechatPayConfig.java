package com.wen.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatPayConfig {

    @Autowired
    private WechatAccountConfig config;

    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wechatMpConfig());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wechatMpConfig() {

        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(config.getMpAppId());
        wxPayH5Config.setAppSecret(config.getMpAppSecret());
        wxPayH5Config.setMchId(config.getMchId());
        wxPayH5Config.setMchKey(config.getMchKey());
        wxPayH5Config.setKeyPath(config.getKeyPath());
        wxPayH5Config.setNotifyUrl(config.getNotifyUrl());

        return wxPayH5Config;
    }
}
