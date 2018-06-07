package com.wen.sell.service.impl;

import com.wen.sell.dto.OrderDTO;
import com.wen.sell.service.OrderMasterService;
import com.wen.sell.service.PushMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageImplTest {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private PushMessage pushMessage;

    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderMasterService.findOne("123");
        pushMessage.orderStatus(orderDTO);
    }
}