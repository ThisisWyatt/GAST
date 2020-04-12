package com.smart.demo.util;

import com.smart.demo.domain.Point;
import com.smart.demo.domain.Points;
import com.smart.demo.test.saveJsonTest;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
    private static void search(int i, int j, BigDecimal index, Point[][] points, int M, int N, BigDecimal intervalNum, Point pointZero, Map<BigDecimal, Point> pointMap) {
        if ((i >= 0 && i < M) && (j >= 0 && j < N) &&! (points[i][j].equals(pointZero))) {
            Point IndexVale=new Point();
            int compare = ((points[i][j].getNum().subtract(index)).abs()).compareTo(intervalNum); //差值
            if ( (compare <= 0)) { //差值在预定范围内 可继续向下递归搜索/**/
                if(pointMap.containsKey(index)&&pointMap.get(index).getNum()!=null){
                    if(pointMap.get(index).getNum().compareTo(points[i][j].getNum())<=0)
                        IndexVale=points[i][j];
                        pointMap.put(index, IndexVale);
                }
                else{
                    pointMap.put(index,points[i][j]);
                }

                points[i][j]=pointZero;//标记 代表已经遍历过

                search(i, j + 1, index, points, M, N, intervalNum, pointZero, pointMap);
                search(i + 1, j, index, points, M, N, intervalNum, pointZero, pointMap);
                search(i, j - 1, index, points, M, N, intervalNum, pointZero, pointMap);
                search(i - 1, j, index, points, M, N, intervalNum, pointZero, pointMap);
                search(i - 1, j + 1, index, points, M, N, intervalNum, pointZero, pointMap);
                search(i + 1, j + 1, index, points, M, N, intervalNum, pointZero, pointMap);
                search(i + 1, j - 1, index, points, M, N, intervalNum, pointZero, pointMap);
                search(i - 1, j - 1, index, points, M, N, intervalNum, pointZero, pointMap);
            }
        }
    }

    /**
     * @Descriptuion TODO 标记后生成一个map,  key为同一组的起始的point的num值 , value为一组中num值最大的point
     **/
    public Map<BigDecimal, Point> markResult() {

        Point pointZero = new Point(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)); //判断符合在一组后 给该point赋此值

        ConversionToArrays conversionToArrays = new ConversionToArrays();
        Point[][] points = conversionToArrays.setArrays();
        long searchTime = System.currentTimeMillis();
        int M = points.length;
        int N = points[0].length;

//          为null的a[i][j]设默认值
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (points[i][j] == null||points[i][j].getNum().compareTo(new BigDecimal(0.1))<0) {
                    points[i][j] = pointZero;
                }
            }
        }

//      key为同一组的起始的point , value为一组中num值最大的point
        Map<BigDecimal, Point> pointMap = new HashMap<>();

//        设定判定为同一组的差值 正负差值为 intervalNum 即为一组
        BigDecimal intervalNum = new BigDecimal(1);
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (!points[i][j].equals(pointZero)) {
                    BigDecimal index = points[i][j].getNum();
                    search(i, j, index, points, M, N, intervalNum, pointZero, pointMap);
                }
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

//        聚合后得到的map key代表和这个点为起始搜索点的num值 value代表这一组num值高的点
        Map<BigDecimal, Point> test = aggregation.markResult();
//
//        将Map转为List
        List<Points> list = Map2ListUtil.Map2List(test);

        Map<String, List> result = new HashMap<>();

        result.put("cc", list);
//        生成JSONObject
        JSONObject jsonFile = JSONObject.fromObject(result);
//         存到本地
        saveJsonTest.createJsonFile(jsonFile, "Y:/ests.json");
//

        System.out.println("生成map大小为：" + test.size());

        System.out.println("All  cost: " + (System.currentTimeMillis() - currentTime) + " ms");

    }


}
