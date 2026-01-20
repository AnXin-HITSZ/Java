package com.anxin_hitsz_08.constructor.exer2;

/**
 * ClassName: TriiAngleTest
 * Package: com.anxin_hitsz_08.constructor.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 16:25
 * @Version 1.0
 */
public class TriiAngleTest {
    public static void main(String[] args) {
        // 创建 TriAngle 的实例 1
        TriAngle t1 = new TriAngle();
        System.out.println("面积为：" + t1.findArea());

        t1.setHeight(2.3);
        t1.setBase(3.4);

        System.out.println("面积为：" + t1.findArea());

        // 创建 TriAngle 的实例 2
        TriAngle t2 = new TriAngle(2.4, 4.5);

        System.out.println("底边长为：" + t2.getBase());
        System.out.println("高为：" + t2.getHeight());
        System.out.println("面积为：" + t2.findArea());

    }
}
