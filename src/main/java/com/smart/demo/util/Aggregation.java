package com.smart.demo.util;

import com.smart.demo.domain.Point;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Description TODO  递归标记
 * Author Cloudr
 * Date 2020/3/25 22:04
 **/

public class Aggregation {


    /**
     * @Descriptuion TODO 按照差值递归输出相同一组的值
     **/
    private static void search(int i, int j, Point point, Point[][] points, int M, int N, BigDecimal intervalNum, Point pointZero, Map<Point, Point> pointMap) {

        if ((i >= 0 && i < M) && (j >= 0 && j < N)) {

            int compare = ((points[i][j].getNum().subtract(point.getNum())).abs()).compareTo(intervalNum); //差值

//            if (compare > 0) {
//                System.out.println("不满足,num=" + points[i][j].getNum() + " 差值=" + (points[i][j].getNum().subtract(point.getNum())).abs());
//            }

            if (!points[i][j].equals(pointZero) && (compare < 0 || compare == 0)) {

//                System.out.println("差值 " + (points[i][j].getNum().subtract(point.getNum())).abs());

                if(pointMap.get(point)!=null){  //如果map中不为空，则选出一个num值最大的point为value
                    if( pointMap.get(point).getNum().compareTo(points[i][j].getNum())<0 )
                        pointMap.put(point,points[i][j]);
                }else{ //为空则key value均为起始点point
                    pointMap.put(point,point);
                }

                if (!points[i][j].equals(point))
                    points[i][j] = pointZero;
                search(i, j + 1, point, points, M, N, intervalNum, pointZero, pointMap);
                search(i + 1, j, point, points, M, N, intervalNum, pointZero, pointMap);
                search(i, j - 1, point, points, M, N, intervalNum, pointZero, pointMap);
                search(i - 1, j, point, points, M, N, intervalNum, pointZero, pointMap);
                search(i - 1, j + 1, point, points, M, N, intervalNum, pointZero, pointMap);
                search(i + 1, j + 1, point, points, M, N, intervalNum, pointZero, pointMap);
                search(i + 1, j - 1, point, points, M, N, intervalNum, pointZero, pointMap);
                search(i - 1, j - 1, point, points, M, N, intervalNum, pointZero, pointMap);
            }
        }
    }

    /**
     * @Descriptuion TODO 标记后生成一个map,  point为同一组中的第一个点，Integer为个数
     **/
    public Map<Point, Point> markResult() {

        Point pointZero = new Point(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)); //判断符合在一组后 给该point赋此值
        ConversionToArrays conversionToArrays = new ConversionToArrays();
        Point[][] points = conversionToArrays.setArrays();
        long searchTime = System.currentTimeMillis();
        int M = points.length;
        int N = points[0].length;

//          为null的a[i][j]设默认值
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (points[i][j] == null)
                    points[i][j] = pointZero;
            }
        }

//        key为同一组的起始的point , value为一组中num值最大的point
        Map<Point, Point> pointMap = new HashMap<>();

//        设定判定为同一组的差值 正负差值为 intervalNum 即为一组
        BigDecimal intervalNum = new BigDecimal(2);

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (!points[i][j].equals(pointZero))
                    search(i, j, points[i][j], points, M, N, intervalNum, pointZero, pointMap);
            }
        }

        System.out.println("main Cost: " + (System.currentTimeMillis() - searchTime) + " ms");

        return pointMap;
    }

    /**
     * @Descriptuion TODO 测试markResult()方法
     **/
    public static void main(String[] args) {

        long currentTime = System.currentTimeMillis();

        Aggregation aggregation = new Aggregation();

        Map<Point, Point> test = aggregation.markResult();

        for(Map.Entry<Point,Point> entry:test.entrySet()){
            System.out.println(" num: "+entry.getValue().getNum());
        }
        System.out.println(test.size());

        System.out.println("All  cost: " + (System.currentTimeMillis() - currentTime) + " ms");


    }


}
