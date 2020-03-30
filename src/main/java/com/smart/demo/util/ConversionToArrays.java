package com.smart.demo.util;

import com.smart.demo.domain.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description TODO 原始数据转换为数学模型
 * Author Cloudr
 * Date 2020/3/27 0:17
 **/
public class ConversionToArrays {


    private static List<Point> listPoint = ReadData.readOriginalDate(); //获取原文件的数据

    /**
     * @Descriptuion TODO 按照Num值排序
     **/
    private static void sortByDifferenceOfNum() {

        Point[] pointArrays = new Point[listPoint.size()];
        for (int i = 0; i < listPoint.size(); ++i) {
            pointArrays[i] = listPoint.get(i);
        }

//      排序
        Point.SortByNum sortByNum = new Point.SortByNum();
        Arrays.sort(pointArrays, sortByNum);

//        输出按照num排序后的点
//        for (Point point : pointArrays) {
//            System.out.println(point);
//        }
        for(int i=0;i<30;++i){
            System.out.println(pointArrays[i]);

        }

//        将排序后的相邻Num值的差值及其个数放入HashMap数组中
        Map<BigDecimal, Integer> map = new HashMap<>();
        for (int i = 1; i < pointArrays.length; ++i) {
            BigDecimal differenceLatValue = new BigDecimal(String.valueOf(pointArrays[i].getNum().subtract(pointArrays[i - 1].getNum())));
            map.put(differenceLatValue, map.getOrDefault(differenceLatValue, 0) + 1);
        }

//        System.out.println();
//        System.out.println("sortByDifferenceOfNum---------------");
//        for (Map.Entry<BigDecimal, Integer> entry : map.entrySet()) {
//            System.out.println("difference: " + entry.getKey() + " num=" + entry.getValue());
//        }

    }

    /**
     * @Descriptuion TODO 按照Lng值排序
     **/
    private static Point[] sortByDifferenceOfLng() {


        Point[] pointArrays = new Point[listPoint.size()];
        for (int i = 0; i < listPoint.size(); ++i) {
            pointArrays[i] = listPoint.get(i);
        }

        Point.SortByLng sortByLng = new Point.SortByLng();
        Arrays.sort(pointArrays, sortByLng);

//        Map<BigDecimal, Integer> map = new HashMap<>();
//        for (int i = 1; i < pointArrays.length; ++i) {
//            BigDecimal differenceLatValue = new BigDecimal(String.valueOf(pointArrays[i].getLng().subtract(pointArrays[i - 1].getLng())));
//            map.put(differenceLatValue, map.getOrDefault(differenceLatValue, 0) + 1);
//        }

//        //查看Lng排序后值差异值及其个数
//        System.out.println("sortByDifferenceOfLng---------------");
//        for(Map.Entry<BigDecimal,Integer> entry:map.entrySet()){
//            System.out.println("difference: "+entry.getKey()+" num="+entry.getValue());
//        }
        return pointArrays;

    }

    /**
     * @Descriptuion TODO 按照Lat值排序
     **/
    private static Point[] sortByDifferenceOfLat() {
        Point[] pointArrays = new Point[listPoint.size()];
        for (int i = 0; i < listPoint.size(); ++i) {
            pointArrays[i] = listPoint.get(i);
        }

//      排序
        Point.SortByLat sortByLat = new Point.SortByLat();
        Arrays.sort(pointArrays, sortByLat);

//        将排序后的相邻Lat值的差值及其个数放入HashMap数组中
//        Map<BigDecimal, Integer> map = new HashMap<>();
//        for (int i = 1; i < pointArrays.length; ++i) {
//            BigDecimal differenceLatValue = new BigDecimal(String.valueOf(pointArrays[i].getLat().subtract(pointArrays[i - 1].getLat())));
//            map.put(differenceLatValue, map.getOrDefault(differenceLatValue, 0) + 1);
//        }

//        System.out.println("sortByDifferenceOfLat---------------");
//        for(Map.Entry<BigDecimal,Integer> entry:map.entrySet()){
//            System.out.println("difference: "+entry.getKey()+" num="+entry.getValue());
//        }

        return pointArrays;
    }

    /**
     * @Descriptuion TODO 生成处理好的二维数组模型
     **/
    public static Point[][] setArrays() {

//        按照0.000028的间隔将lat值从小到大分成M份
        Point[] sortByLat = sortByDifferenceOfLat();
        BigDecimal miniLat = sortByLat[0].getLat(); //最小Lat值
        BigDecimal intervalLat = new BigDecimal(String.valueOf(0.000028));
        BigDecimal bigDecimalM = (sortByLat[sortByLat.length - 1].getLat().subtract(sortByLat[0].getLat()))
                .divide(intervalLat, 5, RoundingMode.HALF_UP);
        double doubleM = bigDecimalM.doubleValue();
        int M = (int) (Math.round(doubleM)) + 1; //BigDecimal->long(round)->int 更加精确？ 感觉应该是


//        按照0.000034的间隔将lng值从小到大分成N份
        Point[] sortByLng = sortByDifferenceOfLng();
        BigDecimal miniLng = sortByLng[0].getLng();//最小Lng值
        BigDecimal intervalLng = new BigDecimal(String.valueOf(0.000034));
        BigDecimal bigDecimalN = (sortByLng[sortByLng.length - 1].getLng().subtract(sortByLng[0].getLng()))
                .divide(intervalLng, 5, RoundingMode.HALF_UP);
        double doubleN = bigDecimalN.doubleValue();
        int N = (int) (Math.ceil(doubleN)) + 1;


        Point[][] pointArrays = new Point[M][N];
        Point point = new Point();
        int size=listPoint.size();
//        不要使用foreach、Iterator、loop with size 效率会偏慢 
        for (int i = 0; i < size; ++i) {
            point = listPoint.get(i);
            BigDecimal bigDecimalI = (point.getLat().subtract(miniLat)).divide(intervalLat, 5, RoundingMode.HALF_UP);
            double doubleI = bigDecimalI.doubleValue();
            int i0 = (int) (Math.ceil(doubleI));
            BigDecimal bigDecimalJ = (point.getLng().subtract(miniLng)).divide(intervalLng, 5, RoundingMode.HALF_UP);
            double doubleJ = bigDecimalJ.doubleValue();
            int j0 = (int) (Math.ceil(doubleJ));
            pointArrays[i0][j0] = point;
        }

        System.out.println("数据条数"+listPoint.size());
        return pointArrays;
    }

    /**
     * @Descriptuion TODO 测试用
     **/
    public static void main(String[] args) {

        sortByDifferenceOfNum();



//        long zero = System.currentTimeMillis();
//
//        List<Point> list2= listPoint;
//        System.out.println("i/o cost "+(System.currentTimeMillis()-zero));
//
//        long currentTime1=System.currentTimeMillis();
//        sortByDifferenceOfLat();
//        System.out.println("sortByDifferenceOfLat cost: "+(System.currentTimeMillis()-currentTime1)+"ms");
//
//        long currentTime2=System.currentTimeMillis();
//        sortByDifferenceOfLng();
//        System.out.println("sortByDifferenceOfLng cost: "+(System.currentTimeMillis()-currentTime2)+"ms");
//        System.out.println("-------------------------------------------");


//        long setArraysTime = System.currentTimeMillis();
//
//        Point[][] point = setArrays();
//
//        System.out.println("sortByDifferenceOfLng cost: " + (System.currentTimeMillis() - setArraysTime) / 1000 + "s");
////
//        System.out.println("AllCost: " + (System.currentTimeMillis() - zero) + " ms");
    }
}
