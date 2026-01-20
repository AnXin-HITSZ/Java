package com.anxin_hitsz_08.constructor.exer1;

/**
 * ClassName: Student
 * Package: com.anxin_hitsz_08.constructor.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 16:15
 * @Version 1.0
 */
public class Student {

    String name;
    int age;
    String school;
    String major;   // 专业


    public Student(String n, int a) {
        name = n;
        age = a;
    }

    public Student(String n, int a, String s) {
        name = n;
        age = a;
        school = s;
    }

    Student(String n, int a, String s, String m) {
        name = n;
        age = a;
        school = s;
        major = m;
    }

    public String getInfo() {
        return "name = " + name + ", age = " + age +
                ", school = " + school + ", major = " + major;
    }
}
