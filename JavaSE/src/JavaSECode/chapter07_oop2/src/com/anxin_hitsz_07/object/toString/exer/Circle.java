package com.anxin_hitsz_07.object.toString.exer;

/**
 * ClassName: Circle
 * Package: com.anxin_hitsz_07.object.toString.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 16:52
 * @Version 1.0
 */
public class Circle extends GeometricObject {
    private double radius;

    public Circle() {
//        color = "white";
//        weight = 1.0;
        radius = 1.0;
    }

    public Circle(double radius) {
//        color = "white";
//        weight = 1.0;
        this.radius = radius;
    }

    public Circle(String color, double weight, double radius) {
        super(color, weight);
        this.radius = radius;
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

    // 重写 equals()
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Circle) {
            Circle c = (Circle) obj;
            return this.radius == c.radius;
        }
        return false;
    }

    // 重写 toString()

    @Override
    public String toString() {
        return "Circle[radius = " + radius + "]";
    }
}
