package com.wetc.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 15:42
 * @Desc
 */
public class LunixCondition implements Condition {

    /**
     *
     * @param context 判断条件能使用的上下文
     * @param metadata 注释信息
     * @return
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 1.获取bean工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 2.获取类加载器
        ClassLoader classLoader = context.getClassLoader();

        // 3.获取当前环境信息
        Environment environment = context.getEnvironment();

        if (environment.getProperty("os.name").contains("Lunix")) {
            return true;
        }
        return false;
    }
}
