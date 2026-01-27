package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: Man
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:28
 * @Version 1.0
 */
public class Man extends Person {

    boolean isSmoking;

    int id = 1002;

    public void eat() {
        System.out.println("男人吃饭");
    }

    public void walk() {
        System.out.println("男人走路");
    }

    public void earnMoney() {
        System.out.println("挣钱 ...");
    }

}
