package com.anxin_hitsz_05.exer.exer2;

/**
 * ClassName: Exer2
 * Package: com.anxin_hitsz_05.exer.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 18:13
 * @Version 1.0
 */
public class Exer2 {
    public static void main(String[] args) {
        // 1. 使用有参的构造器
        try {
//            Person p1 = new Person("Tom", 10);
            Person p1 = new Person("Tom", -10);
            System.out.println(p1);

        } catch (NoLifeValueException e) {
            System.out.println(e.getMessage());
        }

        // 2. 使用空参的构造器
        Person p2 = new Person();
        p2.setName("Jerry");
        p2.setLifeValue(10);
//        p2.setLifeValue(-10);

        System.out.println(p2);
    }
}
