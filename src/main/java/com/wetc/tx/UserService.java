package com.wetc.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 12:03
 * @Desc
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void insertUser() {
        userDao.insert();
        System.out.println("插入完成...");

        // 出现错误，回滚
        int i = 10/0;
    }

}
