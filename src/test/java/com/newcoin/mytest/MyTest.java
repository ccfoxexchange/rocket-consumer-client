package com.newcoin.mytest;

import com.newcoin.rocket.ConsumerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: Yang
 * @create: 2019-06-26 13:24
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerApplication.class)
public class MyTest {

    @Test
    public void testConsumer() throws Exception {
        Thread.sleep(2000 * 1000);
    }

}
