package com.wetc.bean;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 10:39
 * @Desc
 */
public class Person {

    /**
     * 使用@value赋值
     *      1.使用基本数值
     *      2.使用SpEl,#{}
     *      3.使用${},取出配置文件中的值
     */
    @Value("wt")
    private String name;

    @Value("${person.age}")
    private Integer age;

    public Person() {

    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
