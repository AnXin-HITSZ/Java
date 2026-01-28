package com.anxin_hitsz_07.object.toString.exer;

/**
 * ClassName: CircleTest
 * Package: com.anxin_hitsz_07.object.toString.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 16:57
 * @Version 1.0
 */
public class CircleTest {
    public static void main(String[] args) {

        Circle c1 = new Circle(2.3);

        Circle c2 = new Circle("red", 2.0, 3.4);

        System.out.println("颜色是否相等？" + c1.getColor().equals(c2.getColor()));

        System.out.println("半径是否相等？" + c1.equals(c2));

        System.out.println(c1);
        System.out.println(c1.toString());

    }
}
