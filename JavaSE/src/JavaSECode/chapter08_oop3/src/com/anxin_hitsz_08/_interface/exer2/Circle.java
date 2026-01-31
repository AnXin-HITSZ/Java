package com.anxin_hitsz_08._interface.exer2;

/**
 * ClassName: Circle
 * Package: com.anxin_hitsz_08._interface.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 15:02
 * @Version 1.0
 */
public class Circle {
    private double radius;  // 半径

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }

}
