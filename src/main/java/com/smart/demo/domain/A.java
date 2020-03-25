package com.smart.demo.domain;

import org.apache.catalina.User;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/3/18 0:02
 **/
public class A{
    private BigDecimal num;
    private BigDecimal lng;
    private BigDecimal lat;

    public A(BigDecimal num, BigDecimal lng, BigDecimal lat) {
        this.num = num;
        this.lng = lng;
        this.lat = lat;
    }

    public A() {
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getNum() {
        return num;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return "A{" +
                "num=" + num +
                ", lng=" + lng +
                ", lat=" + lat +
                '}';
    }

    public static class SortByNum implements Comparator<A>{

        @Override
        public int compare(A a1, A a2) {
            return a1.getNum().compareTo(a2.getNum());
        }

    }
    public static class SortByLat implements Comparator<A>{

        @Override
        public int compare(A a1, A a2) {
            return a1.getLat().compareTo(a2.getLat());
        }
    }
    public static class SortByLng implements Comparator<A>{

        @Override
        public int compare(A a1, A a2) {
            return a1.getLng().compareTo(a2.getLng());
        }
    }
}




