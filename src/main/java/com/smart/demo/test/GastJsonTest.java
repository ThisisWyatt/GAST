package com.smart.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smart.demo.domain.A;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/3/16 17:57
 **/
public class GastJsonTest {
    //读取json文件
    private static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
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


    public static List<A> readOriginalDate() {
        String path = Objects.requireNonNull(JsonTest.class.getClassLoader().getResource("2019072613.json")).getPath();
        String s = readJsonFile(path);
        JSONObject jsonObject = JSON.parseObject(s);
        JSONArray heatData = jsonObject.getJSONArray("heatData");//构建JSONArray数组

        List<A> listA=new CopyOnWriteArrayList<>();
        for (Object user : heatData) {
            JSONObject key = (JSONObject) user;
            A a = new A((BigDecimal) key.get("num"), (BigDecimal) key.get("lng"), (BigDecimal) key.get("lat"));
            listA.add(a);
        }
        return listA;
    }
}
