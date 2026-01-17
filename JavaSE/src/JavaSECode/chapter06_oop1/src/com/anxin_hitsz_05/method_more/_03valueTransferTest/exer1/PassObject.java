package com.anxin_hitsz_05.method_more._03valueTransferTest.exer1;

/**
 * ClassName: PassObject
 * Package: com.anxin_hitsz_05.method_more._03valueTransferTest.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/17 11:16
 * @Version 1.0
 */
public class PassObject {
    public static void main(String[] args) {
        PassObject obj = new PassObject();
        Circle c = new Circle();
        obj.printAreas(c, 5);

        System.out.println("now radius is: " + c.radius);
    }
    public void printAreas(Circle c, int time) {
        System.out.println("Radius\t\tArea");

        int i = 1;
        for (i = 1; i <= time; i++) {
            c.radius = i;
            System.out.println(c.radius + "\t\t\t" + c.findArea());
        }
        System.out.println();

        c.radius = i;
    }
}
