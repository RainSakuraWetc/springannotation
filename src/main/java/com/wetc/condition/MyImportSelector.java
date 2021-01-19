package com.wetc.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 17:06
 * @Desc
 */
public class MyImportSelector implements ImportSelector {

    /**
     *
     * @param importingClassMetadata 当前标注Import注解的类所有信息
     * @return 返回组件全类名
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.wetc.bean.Blue"};
    }
}
