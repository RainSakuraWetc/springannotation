package com.wetc.service;

import com.wetc.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 12:03
 * @Desc
 */
@Service
public class TestService {

    @Qualifier("testDao")
    @Autowired(required = false)
    private TestDao testDao;

    public void print() {
        System.out.println(testDao);
    }

    @Override
    public String toString() {
        return "TestService{" +
                "testDao=" + testDao +
                '}';
    }
}
