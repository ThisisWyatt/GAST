package com.smart.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import java.io.*;
import java.util.Objects;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/3/15 21:32
 **/
public class userJsonTest {
    //读取json文件
    public static String readJsonFile(String fileName) {
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


    public static void main(String[] args) {
        String path = Objects.requireNonNull(JsonTest.class.getClassLoader().getResource("user.json")).getPath();
        String s = readJsonFile(path);
        JSONObject jobj = JSON.parseObject(s);
        JSONArray users = jobj.getJSONArray("User");//构建JSONArray数组
        for (int i = 0 ; i < users.size();i++){
            JSONObject key = (JSONObject)users.get(i);
            String name = (String) key.get("name");
            int age = (int) key.get("age");
            System.out.println(name);
            System.out.println(age);
        }
    }
}
