package com.anxin_hitsz_07.encapsulation.exer4.test2;

import com.anxin_hitsz_07.encapsulation.exer4.test1.Order;

/**
 * ClassName: OrderTest
 * Package: com.anxin_hitsz_07.encapsulation.exer4.test2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 14:50
 * @Version 1.0
 */
public class OrderTest {
    public static void main(String[] args) {
        Order order = new Order();

        // 调用属性
        order.orderPublic = 1;

        // 调用方法
        order.methodPublic();


        // 不能调用
//        order.oderPrivate = 1;
//        order.orderDefault = 2;

//        order.methodPrivate();
//        order.orderDefault();

    }
}
