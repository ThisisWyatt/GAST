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
    private static void search(int i, int j, Point point, Point[][] points, int M, int N, BigDecimal intervalNum, Point pointZero, Map<Point, Integer> pointMap) {

        if ((i >= 0 && i < M) && (j >= 0 && j < N)) {

            int compare = ((points[i][j].getNum().subtract(point.getNum())).abs()).compareTo(intervalNum); //差值

//            if (compare > 0) {
//                System.out.println("不满足,num=" + points[i][j].getNum() + " 差值=" + (points[i][j].getNum().subtract(point.getNum())).abs());
//            }

            if (!points[i][j].equals(pointZero) && (compare < 0 || compare == 0)) {

//                System.out.println("差值 " + (points[i][j].getNum().subtract(point.getNum())).abs());

                pointMap.put(point, pointMap.getOrDefault(point, 0) + 1);
                if(!points[i][j].equals(point))
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

    public static void main(String[] args) {

        long currentTime = System.currentTimeMillis();

        Point pointZero = new Point(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)); //判断符合在一组后 给该point赋此值
        Point[][] points = ConversionToArrays.setArrays();
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

        Map<Point, Integer> pointMap = new HashMap<>();

//        设定判定为同一组的差值 正负差值为 intervalNum 即为一组
        BigDecimal intervalNum = new BigDecimal(0.2);

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (!points[i][j].equals(pointZero))
                    search(i, j, points[i][j], points, M, N, intervalNum, pointZero, pointMap);
            }
        }

        for(Map.Entry<Point,Integer> entry:pointMap.entrySet()){
            System.out.println("value: "+entry.getKey()+" num: "+entry.getValue());
        }
        System.out.println(pointMap.size());


        System.out.println("main Cost: " + (System.currentTimeMillis() - searchTime) + " ms");

        System.out.println("All  cost: " + (System.currentTimeMillis() - currentTime) + " ms");
    }

}
