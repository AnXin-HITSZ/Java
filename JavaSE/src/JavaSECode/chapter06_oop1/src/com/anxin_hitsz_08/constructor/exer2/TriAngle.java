package com.anxin_hitsz_08.constructor.exer2;

/**
 * ClassName: TriAngle
 * Package: com.anxin_hitsz_08.constructor.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 16:22
 * @Version 1.0
 */
public class TriAngle {
    // 属性
    private double base;    // 底边长
    private double height;  // 高

    public double getBase() {
        return base;
    }
    public void setBase(double b) {
        base = b;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double h) {
        height = h;
    }

    public TriAngle() {

    }
    public TriAngle(double b, double h) {
        base = b;
        height = h;
    }
    // ...

    // 求面积的方法
    public double findArea() {
        return base * height / 2;
    }

}
