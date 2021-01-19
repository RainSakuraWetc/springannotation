package com.wetc.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 18:26
 * @Desc
 */
@Component
public class Cat implements InitializingBean, DisposableBean {

    public Cat() {
        System.out.println("Cat Con...");
    }

    public void destroy() throws Exception {
        System.out.println("Cat destory...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat afterPropertiesSet...");
    }
}
