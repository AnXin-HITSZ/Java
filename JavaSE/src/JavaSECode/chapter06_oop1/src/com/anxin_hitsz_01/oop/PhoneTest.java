package com.anxin_hitsz_01.oop;

/**
 * ClassName: PhoneTest
 * Package: com.anxin_hitsz_01.oop
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/5 11:08
 * @Version 1.0
 */
public class PhoneTest {    // 是 Phone 类的测试类
    public static void main(String[] args) {

        // 复习：数据类型 变量名 = 变量值
//        Scanner scann = new Scanner(System.in);

        // 创建 Phone 的对象
        Phone p1 = new Phone();

        // 通过 Phone 的对象，调用其内部声明的属性或方法
        // 格式："对象.属性" 或 "对象.方法"
        p1.name = "huawei mate50";
        p1.price = 6999;

        System.out.println("name = " + p1.name + "，price = " + p1.price);

        // 调用方法
        p1.call();
        p1.sendMessage("有内鬼，终止交易");
        p1.playGame();
    }
}
