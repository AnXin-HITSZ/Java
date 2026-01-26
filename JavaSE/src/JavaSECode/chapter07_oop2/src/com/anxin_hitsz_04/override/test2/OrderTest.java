package com.anxin_hitsz_04.override.test2;

import com.anxin_hitsz_04.override.test1.Order;

/**
 * ClassName: OrderTest
 * Package: com.anxin_hitsz_04.override.test2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:24
 * @Version 1.0
 */
public class OrderTest {

    public void method() {

        Order order = new Order();

        order.orderPublic = 1;
        order.methodPublic();


        // 不能访问
//        order.orderPrivate = 1;
//        order.orderDefault = 1;
//        order.orderProtected = 1;
//
//        order.methodPrivate();
//        order.methodDefault();
//        order.methodProtected();

    }

}
