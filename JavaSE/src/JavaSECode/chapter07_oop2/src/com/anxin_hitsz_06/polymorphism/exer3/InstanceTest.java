package com.anxin_hitsz_06.polymorphism.exer3;

/**
 * ClassName: InstanceTest
 * Package: com.anxin_hitsz_06.polymorphism.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:56
 * @Version 1.0
 */
public class InstanceTest {

    public static void main(String[] args) {

        InstanceTest test = new InstanceTest();

        test.method(new Student());

    }

    public void method(Person e) {

        System.out.println(e.getInfo());

        // 方式 1：
//        if (e instanceof Graduate) {
//            System.out.println("a graduated student");
//            System.out.println("a student");
//            System.out.println("a person");
//        } else if (e instanceof Student) {
//            System.out.println("a student");
//            System.out.println("a person");
//        } else {
//            System.out.println("a person");
//        }
        // 方式 2：
        if (e instanceof Graduate) {
            System.out.println("a graduated student");
        }

        if (e instanceof Student) {
            System.out.println("a student");
        }

        if (e instanceof Person) {
            System.out.println("a person");
        }
    }
}
