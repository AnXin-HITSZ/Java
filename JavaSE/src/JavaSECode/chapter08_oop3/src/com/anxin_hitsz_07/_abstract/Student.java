package com.anxin_hitsz_07._abstract;

/**
 * ClassName: Student
 * Package: com.anxin_hitsz_07._abstract
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/30 16:05
 * @Version 1.0
 */
public class Student extends Person {
    String school;

    public Student() {
    }

    public Student(String name, int age, String school) {
        super(name, age);
        this.school = school;
    }

    public void eat() {
        System.out.println("学生吃饭");
    }

    public void sleep() {
        System.out.println("学生睡觉");
    }

    @Override
    public void breath() {
        System.out.println("学生应该多呼吸新鲜空气");
    }
}
