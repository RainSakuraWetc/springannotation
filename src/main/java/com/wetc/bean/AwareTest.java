package com.wetc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2021/3/16 16:49
 * @Desc
 */

public class AwareTest implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware {

    private BeanFactory beanFactory;
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("Bean 加载器："+classLoader);
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("Bean 工厂："+beanFactory);
    }

    public void setBeanName(String name) {
        System.out.println("Bean 名称："+name);
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

}
