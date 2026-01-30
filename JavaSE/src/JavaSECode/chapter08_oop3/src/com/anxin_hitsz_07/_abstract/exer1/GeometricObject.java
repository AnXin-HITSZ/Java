package com.anxin_hitsz_07._abstract.exer1;

/**
 * ClassName: GeometricObject
 * Package: com.anxin_hitsz_06.polymorphism.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:31
 * @Version 1.0
 */
public abstract class GeometricObject {
    protected String color;
    protected double weight;

    protected GeometricObject(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public abstract double findArea();
}
