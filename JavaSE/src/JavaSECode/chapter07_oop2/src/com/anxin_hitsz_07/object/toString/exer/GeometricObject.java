package com.anxin_hitsz_07.object.toString.exer;

/**
 * ClassName: GeometricObject
 * Package: com.anxin_hitsz_07.object.toString.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 16:50
 * @Version 1.0
 */
public class GeometricObject {
    protected String color;
    protected double weight;

    public GeometricObject() {
        color = "white";
        weight = 1.0;
    }

    public GeometricObject(String color, double weight) {
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

}
