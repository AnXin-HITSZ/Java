package com.anxin_hitsz_07.encapsulation.exer4.test1;

/**
 * ClassName: OrderTest
 * Package: com.anxin_hitsz_07.encapsulation.exer4.test1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 14:47
 * @Version 1.0
 */
public class OrderTest {
    public static void main(String[] args) {

        Order order = new Order();

        // 调用属性
        order.orderDefault = 1;
        order.orderPublic = 2;
        // 调用方法
        order.methodDefault();
        order.methodPublic();


        // 不能调用
//        order.orderPrivate = 3;
//        order.methodPrivate();

    }
}
