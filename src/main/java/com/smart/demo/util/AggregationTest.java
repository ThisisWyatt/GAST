package com.smart.demo.util;

import com.smart.demo.domain.Point;

import java.math.BigDecimal;

/**
 * Description TODO 测试
 * Author Cloudr
 * Date 2020/3/26 23:55
 **/

public class AggregationTest {
    private static void search(int i, int j, BigDecimal value, Point[][] points, int M, int N, BigDecimal intervalNum, Point pointZero) {
        if ((i > 0 && i < M) && (j > 0 && j < N)) {
            if (!points[i][j].equals(pointZero) && (((points[i][j].getNum().subtract(value)).abs()).compareTo(intervalNum) < 0 || ((points[i][j].getNum().subtract(value)).abs()).compareTo(intervalNum) == 0)) {

                System.out.println(value+" "+points[i][j].getNum());
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


        Point[][] points = new Point[4][4];
        int M = 4;
        int N = 4;

        points[1][1] = new Point(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1));
        points[1][2] = new Point(new BigDecimal(2), new BigDecimal(1), new BigDecimal(2));
        points[1][3] = new Point(new BigDecimal(1), new BigDecimal(1), new BigDecimal(3));
        points[2][1] = new Point(new BigDecimal(2), new BigDecimal(2), new BigDecimal(1));
        points[2][2] = new Point(new BigDecimal(3), new BigDecimal(2), new BigDecimal(2));
        points[2][3] = new Point(new BigDecimal(3), new BigDecimal(2), new BigDecimal(3));
        points[3][1] = new Point(new BigDecimal(3), new BigDecimal(3), new BigDecimal(1));
        points[3][2] = new Point(new BigDecimal(4), new BigDecimal(3), new BigDecimal(2));
        points[3][3] = new Point(new BigDecimal(5), new BigDecimal(3), new BigDecimal(3));

        BigDecimal intervalNum = new BigDecimal(1);

        Point pointZero = new Point(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0));

        for (int i = 1; i < M; ++i) {
            for (int j = 1; j < N; ++j) {
                search(i, j, points[i][j].getNum(), points, M, N, intervalNum, pointZero);
            }
        }

        System.out.println("Time cost: " + (System.currentTimeMillis() - currentTime));
    }
}
