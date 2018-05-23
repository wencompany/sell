package com.wen.sell;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test1() {

        String name = "王五";
        Integer id = 5;
        log.info("name:{}  id:{}",name,id);
        log.debug("debug");
        log.info("info");
        log.error("error");

    }
}
