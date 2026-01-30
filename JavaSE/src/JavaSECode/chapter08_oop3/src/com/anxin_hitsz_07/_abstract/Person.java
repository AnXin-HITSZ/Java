package com.anxin_hitsz_07._abstract;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_07._abstract
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/30 16:04
 * @Version 1.0
 */
public abstract class Person extends Creature {  // 抽象类
    String name;
    int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract void eat(); // 抽象方法

    public abstract void sleep();   // 抽象方法
}
