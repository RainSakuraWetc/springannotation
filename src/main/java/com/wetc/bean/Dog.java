package com.wetc.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 18:26
 * @Desc
 */
@Component
public class Dog implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Dog() {
        System.out.println("Dog Con...");
    }

    @PostConstruct
    public void init() {
        System.out.println("Dog init...");
    }

    @PreDestroy
    public void destory() {
        System.out.println("Dog destory...");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
