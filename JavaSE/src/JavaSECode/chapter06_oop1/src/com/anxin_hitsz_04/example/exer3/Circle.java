package com.anxin_hitsz_04.example.exer3;

/**
 * ClassName: Circle
 * Package: com.anxin_hitsz_04.example.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/7 17:19
 * @Version 1.0
 */
public class Circle {
    // 属性
    double radius;  // 半径

    // 方法
    public void findArea() {
        System.out.println("面积为：" + 3.14 * radius * radius);
    }
    // 或：
    public double findArea2() {
        return 3.14 * radius * radius;
    }

    // 错误的：
//    public void findArea1(double r) {
//        System.out.println("面积为：" + 3.14 * r * r);
//    }

}
