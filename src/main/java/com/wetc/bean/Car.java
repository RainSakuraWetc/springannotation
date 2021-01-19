package com.wetc.bean;

import org.springframework.stereotype.Component;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 18:26
 * @Desc
 */
@Component
public class Car {

    public Car() {
        System.out.println("Car Con...");
    }

    public void init() {
        System.out.println("Car init...");
    }

    public void destory() {
        System.out.println("Car destory...");
    }
}
