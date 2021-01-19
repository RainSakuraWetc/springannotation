package com.wetc.config;

import com.wetc.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 10:49
 * @Desc 配置类等同于配置文件
 */
@Configuration //告诉spring这个是配置文件
@ComponentScan(value = "com.wetc", includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {
                MyTypeFilter.class
        })
} ,useDefaultFilters = false)
/*@ComponentScan(value = "com.wetc", excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
            Controller.class
    })
}, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
                Controller.class
        })
} ,useDefaultFilters = false)*/
//value：指定要扫描的包
//excludeFilters = Filter[] 按照排除规则指定排除哪些组件
//includeFilters = Filter[] 按照排除规则指定包含哪些组件
//FilterType.ANNOTATION 按照注解
//FilterType.ASSIGNABLE_TYPE 按照给定的类型
//FilterType.ASPECTJ 使用aspectj表达式
//FilterType.REGEX 按照正则表达式
//FilterType.CUSTOM 按照自定义规则
public class MainConfig {

    @Bean //给容器注册一个类,注解上 value指定的为该类注册再容器中的name
    public Person person() {
        return new Person("li",20);
    }
}
