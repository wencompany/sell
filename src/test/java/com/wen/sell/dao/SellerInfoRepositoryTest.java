package com.wen.sell.dao;

import com.wen.sell.pojo.SellerInfo;
import com.wen.sell.utils.DateFormater;
import com.wen.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");
        sellerInfo.setCreatedTime(DateFormater.nowTimeStr());
        sellerInfo.setUpdatedTime(DateFormater.nowTimeStr());

        SellerInfo sellerInfo1 = sellerInfoRepository.save(sellerInfo);

        Assert.assertNotNull(sellerInfo);
    }
    @Test
    public void findByOpenid() {

        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("abc");
        Assert.assertNotNull(sellerInfo);
    }
}