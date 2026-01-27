package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: Woman
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:29
 * @Version 1.0
 */
public class Woman extends Person {

    boolean isBeauty;

    public void eat() {
        System.out.println("女人吃饭");
    }

    public void walk() {
        System.out.println("女人走路");
    }

    public void goShopping() {
        System.out.println("逛街 ...");
    }

}
