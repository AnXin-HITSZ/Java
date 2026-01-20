package com.anxin_hitsz_08.constructor;

import java.util.Scanner;

/**
 * ClassName: PersonTest
 * Package: com.anxin_hitsz_08.constructor
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 15:54
 * @Version 1.0
 */
public class PersonTest {
    public static void main(String[] args) {

        Person p1 = new Person();

        p1.eat();

        Person p2 = new Person(1);
        System.out.println(p2.age); // 1

        Scanner scan = new Scanner(System.in);

    }
}
