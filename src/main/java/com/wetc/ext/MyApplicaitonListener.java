package com.wetc.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/29 23:13
 * @Desc
 */
@Component
public class MyApplicaitonListener implements ApplicationListener<ApplicationEvent> {

    // 容器中发布事件，会触发
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event);
    }
}
