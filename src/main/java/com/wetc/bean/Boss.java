package com.wetc.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 18:26
 * @Desc
 */
//默认加在ioc容器中的组件，容器启动会优先调用无参构造器创建 对象，再进行初始化赋值操作
@Component
public class Boss {

    //构造器要用的组件，都是从容器中获取
    @Autowired
    public Boss(Car car){
        this.car = car;
        System.out.println("Boss...有参构造器");
    }

    private Car car;

    public Car getCar() {
        return car;
    }

    //标注在方法上，spring容器创建当前对象，就会调用方法完成赋值
    //方法使用的参数，自定义类型从的值从IOC容器中获取

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
