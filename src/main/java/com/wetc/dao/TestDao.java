package com.wetc.dao;

import org.springframework.stereotype.Repository;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 12:04
 * @Desc
 */
@Repository
public class TestDao {

    private String label = "1";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "TestDao{" +
                "label='" + label + '\'' +
                '}';
    }
}
