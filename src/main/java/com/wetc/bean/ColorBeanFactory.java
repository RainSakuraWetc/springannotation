package com.wetc.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 17:25
 * @Desc
 */
public class ColorBeanFactory implements FactoryBean<Color> {
    public Color getObject() throws Exception {
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    //控制是否单例
    public boolean isSingleton() {
        return true;
    }
}
