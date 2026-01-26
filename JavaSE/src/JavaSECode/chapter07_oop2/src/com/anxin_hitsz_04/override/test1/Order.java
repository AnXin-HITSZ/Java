package com.anxin_hitsz_04.override.test1;

/**
 * ClassName: Order
 * Package: com.anxin_hitsz_04.override.test1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:15
 * @Version 1.0
 */
public class Order {
    private int orderPrivate;
    int orderDefault;
    protected int orderProtected;
    public int orderPublic;

    private void methodProivate() {}
    void methodDefault() {}
    protected void methodProtected() {}
    public void methodPublic() {}

    // 类内部
    public void show() {
        orderPrivate = 1;
        orderDefault = 1;
        orderProtected = 1;
        orderPublic = 1;

        methodProivate();
        methodDefault();
        methodProtected();
        methodPublic();
    }
}
