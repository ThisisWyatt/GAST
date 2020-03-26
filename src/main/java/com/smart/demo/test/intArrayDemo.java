package com.smart.demo.test;

/**
 * Description TODO 二维数组
 * Author Cloudr
 * Date 2020/3/18 20:29
 **/
public class intArrayDemo {
    public static void main(String[] args) {
        int a[][] = {{1, 1, 1, 1, 1}, {1, 2, 2, 2, 1}, {1, 2, 2, 2, 1}, {1, 2, 2, 2, 1}, {1, 1, 1, 1, 1}};

        for (int i = 0; i < a.length; ++i) {
            if (a[i][0] == a[i][1])
                System.out.print("a[" + i + "][0]=" + a[i][0] + " ");
            for (int j = 1; j < a[i].length; ++j) {
                if (j == a[i].length - 1) {
                    if (a[i][j] == a[i][j - 1]) {
                        System.out.print("a[" + i + "][" + j + "]=" + a[i][j] + " ");
                    }
                } else {
                    if (a[i][j] == a[i][j + 1] || a[i][j] == a[i][j - 1])
                        System.out.print("a[" + i + "][" + j + "]=" + a[i][j] + " ");
                }
            }
            System.out.println();
        }

    }
}
