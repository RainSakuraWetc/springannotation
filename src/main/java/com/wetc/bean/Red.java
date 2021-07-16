package com.wetc.bean;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 17:09
 * @Desc
 */
public class Red {

    public static void main(String[] args) throws Exception{
        Red red = new Red();

        System.out.println(red);
        System.out.println(Red.class);
        Class aClass = Class.forName("com.wetc.bean.Red");
        Red reds = (Red) aClass.newInstance();
        System.out.println(reds);

        ReentrantLock reentrantLock = new ReentrantLock();


    }
}
