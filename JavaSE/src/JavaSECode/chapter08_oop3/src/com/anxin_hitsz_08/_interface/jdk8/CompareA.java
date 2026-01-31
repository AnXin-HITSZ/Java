package com.anxin_hitsz_08._interface.jdk8;

/**
 * ClassName: CompareA
 * Package: com.anxin_hitsz_08._interface.jdk8
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 16:28
 * @Version 1.0
 */
public interface CompareA {

    // 属性：声明为 public static final
    // 方法：JDK 8 之前：只能声明抽象方法

    // 方法：JDK 8 中：静态方法
    public static void method1() {
        System.out.println("CompareA: 北京");
    }

    // 方法：JDK 8：默认方法
    public default void method2() {
        System.out.println("CompareA: 上海");
    }

    public default void method3() {
        System.out.println("CompareA: 广州");
    }

    public default void method4() {
        System.out.println("CompareA: 深圳");
    }

    // JDK 9 新特性：定义私有方法
    private void method5() {
        System.out.println("我是接口中定义的私有方法");
    }
}
