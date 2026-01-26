package com.anxin_hitsz_03._extends.exer2;

/**
 * ClassName: Cylinder
 * Package: com.anxin_hitsz_03._extends.exer2
 * Description:
 *      圆柱类
 * @Author AnXin
 * @Create 2026/1/26 16:07
 * @Version 1.0
 */
public class Cylinder extends Circle {

    private double length;  // 高

    public Cylinder() {
        length = 1;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    // 求圆柱的体积
    public double findVolume() {
//        return 3.14 * getRadius() * getRadius() * getLength();

        return findArea() * getLength();
    }
}
