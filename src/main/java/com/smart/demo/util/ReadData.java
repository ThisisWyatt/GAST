package com.smart.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smart.demo.domain.Point;
import com.smart.demo.test.JsonTest;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description TODO 从原文件中读取数据
 * Author Cloudr
 * Date 2020/3/27 0:25
 **/
public class ReadData {
    //读取json文件
    private static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Point> readOriginalDate() {

        long IOCost = System.currentTimeMillis();

        String path = Objects.requireNonNull(JsonTest.class.getClassLoader().getResource("2019072613.json")).getPath();
        String s = readJsonFile(path);
        JSONObject jsonObject = JSON.parseObject(s);
        JSONArray heatData = jsonObject.getJSONArray("heatData");//构建JSONArray数组

//        将数据转换成一个Point数组
        List<Point> listPoint = new CopyOnWriteArrayList<>();
        for (Object user : heatData) {
            JSONObject key = (JSONObject) user;
            Point point = new Point((BigDecimal) key.get("num"), (BigDecimal) key.get("lng"), (BigDecimal) key.get("lat"));
            listPoint.add(point);
        }

        System.out.println("hello world");
//
        System.out.println("I/O  Cost: " + (System.currentTimeMillis() - IOCost) + "ms");

        return listPoint;
    }
}
