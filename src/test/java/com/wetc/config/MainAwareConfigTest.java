package com.wetc.config;

import com.wetc.bean.AwareTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2021/3/16 16:51
 * @Desc
 */
public class MainAwareConfigTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext act = new AnnotationConfigApplicationContext(MainAwareConfig.class);
        AwareTest awareTest = (AwareTest) act.getBean("awareTest");
        System.out.println(awareTest.getBeanFactory());
    }

}