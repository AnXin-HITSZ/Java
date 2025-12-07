package com.anxin_hitsz_04.example.exer3;

/**
 * ClassName: CircleTest
 * Package: com.anxin_hitsz_04.example.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/7 17:22
 * @Version 1.0
 */
public class CircleTest {
    public static void main(String[] args) {
        Circle c1 = new Circle();

        c1.radius = 2.3;
        c1.findArea();

        System.out.println("面积为：" + c1.findArea2());
    }
}
