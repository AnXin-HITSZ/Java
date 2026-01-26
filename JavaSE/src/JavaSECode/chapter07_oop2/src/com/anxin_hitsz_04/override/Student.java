package com.anxin_hitsz_04.override;

/**
 * ClassName: Student
 * Package: com.anxin_hitsz_03._extends
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 14:57
 * @Version 1.0
 */
public class Student extends Person {

    String school;


    public void study() {
        System.out.println("学生学习");
    }

    public void eat() {
        System.out.println("学生应该多吃有营养的食物");
    }

    public void show1() {
        System.out.println("age：" + getAge());
    }

    // 重写的针对于返回值的测试
    public int info() {
        return 1;
    }

    public Student info1() {
        return null;
    }

//    public void sleep() {
//        System.out.println("学生应该多睡，有利于大脑发育");
//    }


    @Override
    public void sleep() {
        System.out.println("学生应该多睡，有利于大脑发育");

    }
}
