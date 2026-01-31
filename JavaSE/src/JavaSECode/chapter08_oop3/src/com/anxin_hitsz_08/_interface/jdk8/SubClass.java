package com.anxin_hitsz_08._interface.jdk8;

/**
 * ClassName: SubClass
 * Package: com.anxin_hitsz_08._interface.jdk8
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 16:29
 * @Version 1.0
 */
public class SubClass extends SuperClass implements  CompareA, CompareB {

    @Override
    public void method2() {
        System.out.println("SubClass: 上海");
    }

    public void method3() {
        System.out.println("SubClass: 广州");
    }

    public void method4() {
        System.out.println("SubClass: 深圳");
    }

    public void method() {
        // 知识点 5：如果在子类（或实现类）中调用父类或接口中被重写的方法
        method4();  // 调用自己类中的方法

        super.method4();    // 调用父类中的方法

        method3();  // 调用自己类中的方法

        CompareA.super.method3();   // 调用接口 CompareA 中的默认方法
        CompareB.super.method3();   // 调用接口 CompareB 中的默认方法

    }
}
