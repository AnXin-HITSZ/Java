package com.anxin_hitsz_01._static.interview;

/**
 * ClassName: StaticTest
 * Package: com.anxin_hitsz_01._static.interview
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 16:01
 * @Version 1.0
 */
public class StaticTest {
    public static void main(String[] args) {
        Order order = null;
        order.hello();  // hello!
        System.out.println(order.count);    // 1
    }
}

class Order {
    public static int count = 1;

    public static void hello() {
        System.out.println("hello!");
    }
}
