package com.anxin_hitsz_10._enum.exer1;

/**
 * ClassName: BankTest1
 * Package: com.anxin_hitsz_10._enum.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/2 14:37
 * @Version 1.0
 */
public class BankTest1 {
    public static void main(String[] args) {
//        Bank1.instance = null;

        System.out.println(Person.PERSON);
    }
}

// jdk 5.0 之前的使用枚举类定义单例模式
class Bank1 {

    private Bank1() {}

    public static final Bank1 instance = new Bank1();



}

// jdk 5.0 使用 enum 关键字定义枚举类的方式定义单例模式
enum Bank2 {
    CPB;
}

enum Person {

    PERSON(20);

    private final int age;

    private Person(int age) {
        this.age = age;
    }

}
