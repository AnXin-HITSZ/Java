package com.anxin_hitsz_07.encapsulation.exer1;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_07.encapsulation.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 13:06
 * @Version 1.0
 */
public class Person {

    private int age;

    // 设置 age 属性
    public void setAge(int a) {
        if (a >= 0 && a <= 130) {
            age = a;
        } else {
            System.out.println("你输入的数据非法");
        }
    }

    // 获取 age 属性
    public int getAge() {
        return age;
    }

    // 错误的
//    public int doAge(int a) {
//        if (a >= 0 && a <= 130) {
//            age = a;
//            return age;
//        } else {
//            System.out.println("你输入的数据非法");
//            return -1;
//        }
//    }

}
