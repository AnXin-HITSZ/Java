package com.anxin_hitsz_04.example.exer1;

/**
 * ClassName: PersonTest
 * Package: com.anxin_hitsz_04.example.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/7 17:04
 * @Version 1.0
 */
public class PersonTest {
    public static void main(String[] args) {
        Person p1 = new Person();

        // 调用属性
        p1.name = "Tom";
        p1.age = 12;
        p1.gender = '男';

        // 调用方法
        p1.study();

        p1.addAge(2);

        int age = p1.showAge();
        System.out.println("age = " + age); // 12 -> 14


        Person p2 = new Person();

        System.out.println(p2.age); // 0

        p2.addAge(10);

        System.out.println(p2.showAge());   // 10

        System.out.println(p1.showAge());   // 14
    }
}
