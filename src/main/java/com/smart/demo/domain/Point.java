package com.smart.demo.domain;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Description TODO 每个点定义的JavaBean
 * Author Cloudr
 * Date 2020/3/26 23:59
 **/
public class Point {
    private BigDecimal num;
    private BigDecimal lng;
    private BigDecimal lat;

    public Point(BigDecimal num, BigDecimal lng, BigDecimal lat) {
        this.num = num;
        this.lng = lng;
        this.lat = lat;
    }

    public Point() {
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Point{" +
                "num=" + num +
                ", lng=" + lng +
                ", lat=" + lat +
                '}';
    }

    public static class SortByNum implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
            return p1.getNum().compareTo(p2.getNum());
        }
    }


    public static class SortByLat implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
            return p1.getLat().compareTo(p2.getLat());
        }
    }

    public static class SortByLng implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
            return p1.getLng().compareTo(p2.getLng());
        }
    }

}
