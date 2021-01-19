package com.wetc.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 14:05
 * @Desc 自定义包扫描规则
 */
public class MyTypeFilter implements TypeFilter {

    /**
     *
     * @param metadataReader 读取到的当前正在扫描的类信息
     * @param metadataReaderFactory 可以获取其他任何类的信息
     * @return
     * @throws IOException
     */
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        // 获取当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        // 获取当前扫描类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();

        // 当前类的类资源(类路径...)
        Resource resource = metadataReader.getResource();

        if (classMetadata.getClassName().contains("er")) {
            return true;
        }
        return false;
    }
}
