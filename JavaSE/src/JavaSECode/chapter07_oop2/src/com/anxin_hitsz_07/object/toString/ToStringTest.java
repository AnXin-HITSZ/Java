package com.anxin_hitsz_07.object.toString;

import java.io.File;
import java.util.Date;

/**
 * ClassName: ToStringTest
 * Package: com.anxin_hitsz_07.object.toString
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 16:31
 * @Version 1.0
 */
public class ToStringTest {
    public static void main(String[] args) {
        User u1 = new User("Tom", 12);
        System.out.println(u1.toString());  // com.anxin_hitsz_07.object.toString.User@8efb846
        System.out.println(u1); // com.anxin_hitsz_07.object.toString.User@8efb846

        String s1 = new String("hello");
        System.out.println(s1.toString());  // hello

        File file = new File("d:\\abc.txt");
        System.out.println(file);   // d:\abc.txt

        Date date = new Date();
        System.out.println(date);   // Wed Jan 28 16:42:38 CST 2026

    }
}

class User {

    String name;
    int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 手动实现 toString()：
//    @Override
//    public String toString() {
//        return "User{ name = " + name + ", age = " + age + " }";
//    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
