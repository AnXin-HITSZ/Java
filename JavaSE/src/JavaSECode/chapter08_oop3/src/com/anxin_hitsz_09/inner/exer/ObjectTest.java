package com.anxin_hitsz_09.inner.exer;

/**
 * ClassName: ObjectTest
 * Package: com.anxin_hitsz_09.inner.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 18:46
 * @Version 1.0
 */
public class ObjectTest {
    public static void main(String[] args) {
//        SubObject sub1 = new SubObject();
//        sub1.test();


        // 提供一个继承于 Object 的匿名子类的匿名对象
        new Object() {
            public void test() {
                System.out.println("尚硅谷");
            }
        }.test();
        
    }
}

class SubObject extends Object {
    public void test() {
        System.out.println("尚硅谷");
    }
}
