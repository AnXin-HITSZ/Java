package com.anxin_hitsz_05._super.exer2;

/**
 * ClassName: Circle
 * Package: com.anxin_hitsz_03._extends.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:06
 * @Version 1.0
 */
public class Circle {
    private double radius;  // 半径

    public Circle() {
        this.radius = 1;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    // 求圆的面积
    public double findArea() {
        return 3.14 * radius * radius;
    }
}
