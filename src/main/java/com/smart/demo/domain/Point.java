package com.smart.demo.domain;

import sun.nio.cs.FastCharsetProvider;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj){
        // 这里使用==显示判断比较对象是否是同一对象
        if (this == obj) {
            return true;
        }
        // 对于任何非null的引用值x，x.equals(null)必须返回false
        if (obj == null) {
            return false;
        }
        // 通过 instanceof 判断比较对象类型是否合法
        if (!(obj instanceof Point)) {
            return false;
        }
        // 对象类型强制转换，如果核心域比较相等，则返回true，否则返回false
        Point other = (Point) obj;
        // 如果两者相等，返回true（含两者皆空的情形），否则比较两者值是否相等
        return Objects.equals(this.num, other.num)
                && Objects.equals(this.lat, other.lat)
                && Objects.equals(this.lng, other.lng);
    }




}
