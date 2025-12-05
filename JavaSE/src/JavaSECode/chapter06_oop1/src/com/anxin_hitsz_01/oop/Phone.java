package com.anxin_hitsz_01.oop;

/**
 * ClassName: Phone
 * Package: com.anxin_hitsz_01.oop
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/3 21:20
 * @Version 1.0
 */
public class Phone {

    // 属性
    String name;    // 品牌
    double price;   // 价格


    // 方法
    public void call(){
        System.out.println("手机能够拨打电话");
    }

    public void sendMessage(String message){
        System.out.println("发送信息：" + message);
    }

    public void playGame(){
        System.out.println("手机可以玩游戏");
    }
}
