package com.anxin_hitsz_08.constructor;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_08.constructor
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 15:53
 * @Version 1.0
 */
public class Person {
    // 属性
    String name;
    int age;

    // 构造器
    public Person() {
        System.out.println("Person() ...");
    }

    // 声明其它的构造器
    public Person(int a) {
        age = a;
    }
    public Person(String n) {
        name = n;
    }
    public Person(String n, int a) {
        name = n;
        age = a;
    }


    // 方法
    public void eat() {
        System.out.println("人吃饭");
    }

    public void sleep(int hour) {
        System.out.println("每天睡眠 " + hour + " 小时");
    }

}
