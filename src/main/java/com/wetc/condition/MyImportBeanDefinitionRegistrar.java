package com.wetc.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 17:12
 * @Desc
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param importingClassMetadata 当前类的注释信息
     * @param registry 注册类：调用registerBeanDefinition进行注册
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        if (registry.containsBeanDefinition("com.wetc.bean.Blue")) {
            RootBeanDefinition red = new RootBeanDefinition("com.wetc.bean.Red");
            registry.registerBeanDefinition("red",red);
        }
    }
}
