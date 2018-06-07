package com.wen.sell.service.impl;

import com.wen.sell.config.ProjectUrlConfig;
import com.wen.sell.config.WechatAccountConfig;
import com.wen.sell.dto.OrderDTO;
import com.wen.sell.service.PushMessage;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushMessageImpl implements PushMessage {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {

        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("sname","**专卖店"),
                new WxMpTemplateData("price","￥ "+orderDTO.getOrderAmount()),
                new WxMpTemplateData("status",orderDTO.getPayStatusEnum().getMsg()),
                new WxMpTemplateData("name",orderDTO.getBuyerName()),
                new WxMpTemplateData("phone",orderDTO.getBuyerPhone()),
                new WxMpTemplateData("add",orderDTO.getBuyerAddress()));

        wxMpTemplateMessage.setData(data);

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            log.error("【订单状态发送消息】 失败{}", e.getMessage());
        }
    }
}
