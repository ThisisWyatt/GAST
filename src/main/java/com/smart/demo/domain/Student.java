package com.smart.demo.domain;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/3/15 17:37
 **/
public class Student {
        private String name;
        private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

}
