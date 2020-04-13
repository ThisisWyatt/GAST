package com.smart.demo;

import com.smart.demo.domain.Point;
import com.smart.demo.domain.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.ls.LSInput;

import java.math.BigDecimal;
import java.util.*;

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


    @Test
    void TestChange(){
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
//        JSONArray jsonArray1 = net.sf.json.JSONArray.fromObject(map2);
        JSONObject jsonArray1 = JSONObject.fromObject(map2);


        System.out.println(jsonArray1);

    }


    @Test
    void Map2List(){
        Map<Student,Student> map=new HashMap<>();

        Student student1 = new Student();
        student1.setName("cc1");
        student1.setAge(21);

        Student student2 = new Student();
        student2.setName("cc2");
        student2.setAge(21);

        Student student3 = new Student();
        student3.setName("cc3");
        student3.setAge(21);

        map.put(student1,student1);
        map.put(student1,student2);
        map.put(student1,student3);


        System.out.println(map.size());


    }

    @Test
    void TestChangeBigDecimal(){
        BigDecimal bigDecimal=new BigDecimal(12.54).setScale(2,BigDecimal.ROUND_HALF_UP);
        String test=bigDecimal.toString();
        System.out.println(test);
    }


    @Test
    void ListInMap(){
        List<Student> list=new LinkedList<>();
        list.add(new Student("cc1",21));
        list.add(new Student("cc2",22));
        Map<Integer,List<Student>> map=new HashMap<>();
        map.put(1,list);
        List<Student> list1=map.get(1);
        Iterator iterator=list1.iterator();
        while(iterator.hasNext()){
            Student student= (Student) iterator.next();
            System.out.println(student);
        }

    }


    @Test
    void Array2List(){
        Student[] students=new Student[2];
        Student student1=new Student("cc1",21);
        Student student2=new Student("cc2",22);
        students[0]=student1;
        students[1]=student2;
        List<Student> list=new LinkedList<>();
        list=new ArrayList<>(Arrays.asList(students));
        list.add(new Student("cc3",23));
        for(Object o:list){
            System.out.println(o);
        }
    }





}
