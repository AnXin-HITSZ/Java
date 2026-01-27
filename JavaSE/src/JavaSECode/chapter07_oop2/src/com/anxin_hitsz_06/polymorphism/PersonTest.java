package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: PersonTest
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 15:33
 * @Version 1.0
 */
public class PersonTest {
    public static void main(String[] args) {

        // 多态性之前的场景：
        Person p1 = new Person();
        Man m1 = new Man();

        // 多态性：子类对象的多态性
        Person p2 = new Man();

        /*
        * 多态性的应用：虚拟方法调用（虚方法调用）
        *
        * 在多态的场景下，调用方法时。
        *   编译时，认为方法是左边声明的父类的类型的方法（即被重写的方法）。
        *   执行时，实际执行的是子类重写父类的方法。
        * 简称为：编译看左边，运行看右边。
        *
        * */
        p2.eat();
        p2.walk();

        // 测试属性是否满足多态性？不满足！
        System.out.println(p2.id);  // 1001

        /*
        * 多态的弊端？
        * 问题：Person p2 = new Man();
        *   针对于创建的对象，在内存中是否加载了 Man 类中声明的特有的属性和方法？加载了！
        * 问题：能不能直接调用 Man 类中加载的特有的属性和方法？不能！
        * */
//        p2.earnMoney();
//        System.out.println(p2.isSmoking);
//        Man m2 = new Man();

    }
}
