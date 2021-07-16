package com.wetc.config;

import com.wetc.bean.AwareTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2021/3/16 16:50
 * @Desc
 */
@Configuration
public class MainAwareConfig {

    @Bean
    public AwareTest awareTest(){
        return new AwareTest();
    }
}
