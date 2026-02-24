package com.anxin_hitsz_02.selfdefine;

import java.util.ArrayList;

/**
 * ClassName: Order
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 17:00
 * @Version 1.0
 */
public class Order<T> {

    // 声明了类的泛型参数以后，就可以在类的内部使用此泛型参数

    T t;
    int orderId;

//    static T t1;

    public Order() {
    }

    public Order(T t, int orderId) {
        this.t = t;
        this.orderId = orderId;
    }

    // 如下的两个方法不是泛型方法
    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "t=" + t +
                ", orderId=" + orderId +
                '}';
    }

    // 不可以在静态方法中使用类的泛型
//    public static void method1() {
//        System.out.println("t: " + t);
//
//    }

    // 自定义泛型方法
    public <E> E method(E e) {
        return null;
    }

    // 定义泛型方法，将 E[] 数组元素添加到对应类型的 ArrayList 中，并返回
    public <E> ArrayList<E> copyFromArrayToList(E[] arr) {
        ArrayList<E> list = new ArrayList<>();
        for (E e : arr) {
            list.add(e);
        }

        return list;
    }

}
