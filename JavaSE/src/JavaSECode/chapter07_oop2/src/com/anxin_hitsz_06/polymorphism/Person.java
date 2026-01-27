package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: Person
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:27
 * @Version 1.0
 */
public class Person {
    String name;
    int age;

    int id = 1001;

    public void eat() {
        System.out.println("人吃饭");
    }

    public void walk() {
        System.out.println("人走路");
    }
}
