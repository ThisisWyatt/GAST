package com.smart.demo;

import com.smart.demo.domain.Point;
import com.smart.demo.domain.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("hello world");
    }

    @Test
    void testJson() {

        Map<String, String> map = new HashMap<>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println("hashMap构造JSONObject：" + jsonObject);

        Student student1 = new Student();
        student1.setName("cc1");
        student1.setAge(21);

        Student student2 = new Student();
        student2.setName("cc2");
        student2.setAge(21);

        Map<String, Student> map1 = new HashMap<>();
        map1.put("first", student1);
        map1.put("second", student2);

        JSONArray jsonArray = JSONArray.fromObject(map1);
        System.out.println("用hashMap构造JSONArray:" + jsonArray);


        JSONObject jsonObject1 = JSONObject.fromObject(student1);
        System.out.println("javaBean构造JSONObject：" + jsonObject);

        String name1 = jsonObject1.getString("name");
        int age1 = jsonObject1.getInt("age");

        System.out.println("从jsonObject1解析json: " + name1 + "  " + age1);


    }

    @Test
    void testSort(){
        int[] a={1,3,4,2,6,9,8,7};
        Arrays.sort(a);
        for(int i:a){
            System.out.println(i);
        }
    }

    @Test
    void testMap(){
        Map<Point,Integer>map=new HashMap<>();
        Point point1=new Point(new BigDecimal(1),new BigDecimal(1),new BigDecimal(1));
        Point point2=new Point(new BigDecimal(1),new BigDecimal(1),new BigDecimal(1));
        map.put(point1,1);
        System.out.println(map.get(point2));
    }








}
