package com.anxin_hitsz_05.field;

/**
 * ClassName: FieldTest
 * Package: com.anxin_hitsz_05.field
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 20:42
 * @Version 1.0
 */
public class FieldTest {
    public static void main(String[] args) {
        Order o1 = new Order();
        System.out.println(o1.orderId); // 1
    }
}

class Order {

    int orderId = 1;

    {
        orderId = 2;
    }


    public Order() {
        super();
//        orderId = 3;
    }

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public void eat() {
        sleep();
    }

    public void sleep() {

    }
}
