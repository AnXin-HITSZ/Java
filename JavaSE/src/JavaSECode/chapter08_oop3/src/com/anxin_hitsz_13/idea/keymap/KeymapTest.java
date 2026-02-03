package com.anxin_hitsz_13.idea.keymap;

import java.util.Date;
import java.util.HashMap;

/**
 * ClassName: KeymapTest
 * Package: com.anxin_hitsz_13.idea.keymap
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/3 16:45
 * @Version 1.0
 */
public class KeymapTest {
    final int NUM = 10;
    String s = new String();

    public static void main(String[] args) {

        System.out.println("hello");

        String str123 = null;
        for (int i = 0; i <=  10; i++) {

            str123 = new String("hello");
            str123 += "123";
            System.out.println(str123.toString());


        }

        method(str123);

        HashMap map1 = new HashMap();
        map1.put("Tom", 89);

        Integer i = new Integer(10);

        Date date = new Date();


    }

    private static void method(String str) {
        try {
            Object obj = str;
            String str1 = (String) obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void eat() {}
    public void sleep() {}

}

interface AA {
    void method1();
}

interface BB {
    void method2();
}

class Student extends Person implements AA, BB {
    @Override
    public void eat() {
        super.eat();
    }

    @Override
    public void sleep() {
        super.sleep();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }

}
