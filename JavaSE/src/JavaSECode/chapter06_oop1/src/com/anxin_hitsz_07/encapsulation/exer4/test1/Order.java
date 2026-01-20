package com.anxin_hitsz_07.encapsulation.exer4.test1;

/**
 * ClassName: Order
 * Package: com.anxin_hitsz_07.encapsulation.exer4.test1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 14:44
 * @Version 1.0
 */
public class Order {
    // 声明不同权限的属性
    private int orderPrivate;
    int orderDefault;
    public int orderPublic;

    // 声明不同权限的方法
    private void methodPrivate() {

    }
    void methodDefault() {

    }
    public void methodPublic() {

    }

    public void test() {
        orderPrivate = 1;
        orderDefault = 2;
        orderPublic = 3;

        methodPrivate();
        methodDefault();
        methodPublic();
    }

}
