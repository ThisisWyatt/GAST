package com.smart.demo.test;
//import com.alibaba.fastjson.JSONArray;

import com.smart.demo.domain.Student;
import net.sf.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/3/18 18:54
 **/
public class saveJsonTest {
    public static void createJsonFile(Object jsonData, String filePath) {

        String content = com.alibaba.fastjson.JSONArray.toJSONString(jsonData);
        // 生成json格式文件
        try {
            // 保证创建一个新文件
            File file = new File(filePath);
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();
            // 将格式化后的字符串写入文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            write.write(content);
            write.flush();
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Student student1 = new Student();
        student1.setName("cc1");
        student1.setAge(21);

        Student student2 = new Student();
        student2.setName("cc2");
        student2.setAge(21);

        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        Map<String, List> map2 = new HashMap<>();
        map2.put("testKey", list);
        JSONArray jsonArray1 = net.sf.json.JSONArray.fromObject(map2);

        createJsonFile(jsonArray1, "X:/Test.json");

        System.out.println(jsonArray1);



    }

}
