package com.wetc.controller;

import com.wetc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 12:03
 * @Desc
 */
@Controller
public class TestController {

    @Autowired
    private TestService testService;
}
