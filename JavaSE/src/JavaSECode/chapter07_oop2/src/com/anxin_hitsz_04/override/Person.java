package com.anxin_hitsz_04.override;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_03._extends
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 14:52
 * @Version 1.0
 */
public class Person {
    // 属性
    String name;
    private int age;

    // 方法
    public void eat() {
        System.out.println("人吃饭");
    }
    public void sleep() {
        System.out.println("人睡觉");
    }

    public void show() {
        System.out.println("age：" + age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int info() {
        return 1;
    }

    public Person info1() {
        return null;
    }
}
