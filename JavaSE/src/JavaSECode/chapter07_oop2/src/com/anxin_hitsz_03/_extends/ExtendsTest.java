package com.anxin_hitsz_03._extends;

/**
 * ClassName: ExtendsTest
 * Package: com.anxin_hitsz_03._extends
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 14:58
 * @Version 1.0
 */
public class ExtendsTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.name = "Tony";
//        p1.age = 12;
        p1.eat();

        System.out.println(p1.toString());

        Student s1 = new Student();
        s1.name = "Tom";
//        s1.age = 12;
        s1.eat();

        // 超纲：获取 s1 所属类的父类
        System.out.println(s1.getClass().getSuperclass());  // Person
        // 超纲：获取 p1 所属类的父类
        System.out.println(p1.getClass().getSuperclass());  // java.lang.Object

    }
}
