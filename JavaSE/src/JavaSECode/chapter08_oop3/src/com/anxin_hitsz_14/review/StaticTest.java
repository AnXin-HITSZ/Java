package com.anxin_hitsz_14.review;

/**
 * ClassName: StaticTest
 * Package: com.anxin_hitsz_14.review
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/3 20:44
 * @Version 1.0
 */
public class StaticTest {
    public static void main(String[] args) {
        Person  p1 = new Student();
        p1.eat();

        Person p2  = null;
        p2.eat();

    }
}

class Person {
    public static void eat() {
        System.out.println("人吃饭");
    }

    public static void eat(String food) {
        System.out.println("人喜欢吃" + food);
    }
}

class Student extends Person {
    public static void eat() {
        System.out.println("学生吃饭");
    }
}
