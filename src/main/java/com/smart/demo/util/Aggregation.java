package com.smart.demo.util;

import com.smart.demo.domain.Point;
import com.smart.demo.domain.Points;
import com.smart.demo.test.saveJsonTest;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Description TODO  递归标记
 * Author Cloudr
 * Date 2020/3/25 22:04
 **/

public class Aggregation {


    /**
     * @Descriptuion TODO 按照差值递归输出相同一组的值
     **/

    private static void search(int i, int j, BigDecimal index, Point[][] points, int M, int N, BigDecimal intervalNum, Point pointZero, Map<BigDecimal, List<Point>> pointMap) {
        if ((i >= 0 && i < M) && (j >= 0 && j < N) && !(points[i][j].equals(pointZero))) {
            Point IndexVale = new Point();
            int compare = ((points[i][j].getNum().subtract(index)).abs()).compareTo(intervalNum); //差值
            if ((compare <= 0)) { //差值在预定范围内 可继续向下递归搜索/**/

                if (pointMap.containsKey(index)) { //如果pointMap中包含index的键值对 就将points[i][j]放入index对应的value的List中
                    pointMap.get(index).add(points[i][j]);
                } else { //不含有就创建一个放入pointMap
                    List<Point> list = new LinkedList<>();
                    list.add(points[i][j]);
                    pointMap.put(index, list);
                }

                points[i][j] = pointZero;//标记 代表已经遍历过

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

    public Map<BigDecimal, List<Point>> markResult2(BigDecimal intervalNum) {

        Point pointZero = new Point(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)); //判断符合在一组后 给该point赋此值 以表示已经遍历过
        long currentTimeIO = System.currentTimeMillis();
        ConversionToArrays conversionToArrays = new ConversionToArrays();

        System.out.println("原始数据条数=" + conversionToArrays.listPoint.size());

        Point[][] points = conversionToArrays.setArrays();
        System.out.println("IO  cost: " + (System.currentTimeMillis() - currentTimeIO) + " ms");

        long searchTime = System.currentTimeMillis();
        int M = points.length;
        int N = points[0].length;

//          为null的a[i][j]设默认值
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (points[i][j] == null || points[i][j].getNum().compareTo(new BigDecimal(0.1)) < 0) {
                    points[i][j] = pointZero;
                }
            }
        }

//      key为同一组的起始的point , value为一组中num值最大的point
        Map<BigDecimal, List<Point>> pointMap = new HashMap<>();

//        设定判定为同一组的差值 正负差值为 intervalNum 即为一组

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

        Scanner in = new Scanner(System.in);

        System.out.println("请输入同一组相邻栅格点落差");
        float intervalNum0 = in.nextFloat();
        BigDecimal intervalNum = new BigDecimal(intervalNum0);//同一组相邻栅格点落差

        System.out.println("请输入内涝等级划分的降水范围（5个数）");
        float level0down0 = in.nextFloat();
        BigDecimal level0down = new BigDecimal(level0down0);
        float level0up0 = in.nextFloat();
        BigDecimal level0up = new BigDecimal(level0up0);
        float level1down0 = in.nextFloat();
        BigDecimal level1down = new BigDecimal(level1down0);
        float level1up0 = in.nextFloat();
        BigDecimal level1up = new BigDecimal(level1up0);
        float level2down0 = in.nextFloat();
        BigDecimal level2down = new BigDecimal(level2down0);
        float level2up0 = in.nextFloat();
        BigDecimal level2up = new BigDecimal(level2up0);
        float level3down0 = in.nextFloat();
        BigDecimal level3down = new BigDecimal(level3down0);


        long currentTime = System.currentTimeMillis();

        Aggregation aggregation = new Aggregation();

//        map的key为同一组的起始搜索点，BigDecimal+-差值为这一组的num范围，map的value为这一组的所有点的List集合
        Map<BigDecimal, List<Point>> test2 = aggregation.markResult2(intervalNum);

//          存放最终结果 map的key为城市内涝等级，0：积水，1：轻度内涝，2：中度内涝，3：严重内涝
//          value为这一个等级所包含的所有点的map集合
        Map<String, List<Points>> saveMap = new HashMap<>();
        int level = 0;//降水等级


//      将同一组的数据进行计算 给定内涝等级
        for (Map.Entry entry : test2.entrySet()) {

//          提取出list对其进行排序，取得中心坐标
            List<Point> list = (List<Point>) entry.getValue();
            if (list.size() <= 2)
                continue;       //去除栅格面积小于2的数据

            list = Arrays.asList(ConversionToArrays.sortByDifferenceOfLat(list));
            BigDecimal miniLat = list.get(0).getLat();
            BigDecimal maxLat = list.get(list.size() - 1).getLat();
            BigDecimal middleLat = maxLat.add(miniLat).divide(new BigDecimal(2), 5, RoundingMode.HALF_UP);

            list = Arrays.asList(ConversionToArrays.sortByDifferenceOfLng(list));
            BigDecimal miniLng = list.get(0).getLng();
            BigDecimal maxLng = list.get(list.size() - 1).getLng();
            BigDecimal middleLng = maxLng.add(miniLng).divide(new BigDecimal(2), 5, RoundingMode.HALF_UP);

            Point savePoint0 = new Point((BigDecimal) entry.getKey(), middleLng, middleLat);
            Points savePoint = Point2PointsUtil.Point2Points(savePoint0);

//            划分内涝等级
            if (((BigDecimal) entry.getKey()).compareTo(level0up) <= 0 && ((BigDecimal) entry.getKey()).compareTo(level0down) >= 0)
                level = 0;
            else if (((BigDecimal) entry.getKey()).compareTo(level1up) <= 0 && ((BigDecimal) entry.getKey()).compareTo(level1down) > 0)
                level = 1;
            else if (((BigDecimal) entry.getKey()).compareTo(level2up) <= 0 && ((BigDecimal) entry.getKey()).compareTo(level2down) > 0)
                level = 2;
            else if (((BigDecimal) entry.getKey()).compareTo(level3down) > 0)
                level = 3;

            if (!saveMap.containsKey(Integer.toString(level))) {
                List<Points> list1 = new LinkedList<>();
                list1.add(savePoint);
                saveMap.put(Integer.toString(level), list1);
            } else {
                saveMap.get(Integer.toString(level)).add(savePoint);
            }
        }

        int saveNum = 0; //处理后的数据条数

        for (Map.Entry entry : saveMap.entrySet()) {
            List<Point> list2 = (List<Point>) entry.getValue();
            System.out.println("level=" + entry.getKey() + "  包含条数" + list2.size());
            saveNum += list2.size();
        }

        System.out.println("处理后的数据条数" + saveNum);


        JSONObject jsonFile = JSONObject.fromObject(saveMap);
////         存到本地
        saveJsonTest.createJsonFile(jsonFile, "Y:/stayCalm.json");

        System.out.println("All  cost: " + (System.currentTimeMillis() - currentTime) + " ms");
    }


}
