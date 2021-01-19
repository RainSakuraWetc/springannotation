package com.wetc.bean;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/12/23 16:57
 * @Desc
 */
public class Color {

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Color{" +
                "car=" + car +
                '}';
    }
}
