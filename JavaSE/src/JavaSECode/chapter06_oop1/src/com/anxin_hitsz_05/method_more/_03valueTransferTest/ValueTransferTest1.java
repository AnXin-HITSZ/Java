package com.anxin_hitsz_05.method_more._03valueTransferTest;

/**
 * ClassName: ValueTransferTest1
 * Package: com.anxin_hitsz_05.method_more._03valueTransferTest
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/17 10:46
 * @Version 1.0
 */
public class ValueTransferTest1 {
    public static void main(String[] args) {
        ValueTransferTest1 test = new ValueTransferTest1();
        // 1. 对于基本数据类型的变量来说
        int m = 10;
        test.method1(m);
        System.out.println("m = " + m); // 10

        // 2. 对于引用数据类型的变量来说
        Person p = new Person();
        p.age = 10;
        test.method2(p);
        System.out.println("p.age = " + p.age); // 11
    }

    public void method1(int m) {
        m++;
    }

    public void method2(Person p) {
        p.age++;
    }
}

class Person {
    int age;
}
