package com.smart.demo.util;

import com.smart.demo.domain.Point;

import java.math.BigDecimal;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/3/25 22:04
 **/
public class Aggregation {

    /**
     * @Descriptuion TODO 递归标记
     **/


    /**
     *@Descriptuion TODO 按照差值递归输出相同一组的值
    **/
    private static void search(int i, int j, BigDecimal value, Point[][] points, int M, int N, BigDecimal intervalNum, Point pointZero) {

        if ((i >= 0 && i < M) && (j >= 0 && j < N)) {
            int compare = ((points[i][j].getNum().subtract(value)).abs()).compareTo(intervalNum);
            if (!points[i][j].equals(pointZero) && (compare < 0 || compare == 0)) {
//                System.out.println(points[i][j]);
                points[i][j] = pointZero;
                search(i, j + 1, value, points, M, N, intervalNum, pointZero);
                search(i + 1, j, value, points, M, N, intervalNum, pointZero);
                search(i, j - 1, value, points, M, N, intervalNum, pointZero);
                search(i - 1, j, value, points, M, N, intervalNum, pointZero);
                search(i - 1, j + 1, value, points, M, N, intervalNum, pointZero);
                search(i + 1, j + 1, value, points, M, N, intervalNum, pointZero);
                search(i + 1, j - 1, value, points, M, N, intervalNum, pointZero);
                search(i - 1, j - 1, value, points, M, N, intervalNum, pointZero);
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

//        设定判定为同一组的差值 正负差值为 intervalNum 即为一组
        BigDecimal intervalNum = new BigDecimal(0.001);

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (points[i][j] != null)
                    search(i, j, points[i][j].getNum(), points, M, N, intervalNum, pointZero);
            }
        }

        System.out.println("main Cost: " + (System.currentTimeMillis() - searchTime) + " ms");

        System.out.println("All  cost: " + (System.currentTimeMillis() - currentTime) + " ms");
    }

}
