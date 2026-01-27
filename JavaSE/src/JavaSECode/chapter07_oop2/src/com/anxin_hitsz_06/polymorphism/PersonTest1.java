package com.anxin_hitsz_06.polymorphism;

/**
 * ClassName: PersonTest1
 * Package: com.anxin_hitsz_06.polymorphism
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 17:17
 * @Version 1.0
 */
public class PersonTest1 {
    public static void main(String[] args) {
        Person p1 = new Man();

        // 不能直接调用子类特有的结构
//        p1.earnMoney();
//        System.out.println(p1.isSmoking);

        // 向下转型：使用强转符
        Man m1 = (Man)p1;
        m1.earnMoney();
        System.out.println(m1.isSmoking);

        System.out.println(p1 == m1);   // true，p1 和 m1 指向堆空间中的同一个对象

        /*
        * 向下转型可能会出现：类型转换异常（ClassCastException）
        * */
        Person p2 = new Woman();
//        Man m2 = (Man)p2;
//        m2.earnMoney();

        /*
        * 1. 建议在向下转型之前，使用 instanceof 进行判断，避免出现类型转换异常
        * 2. 格式：a instanceof A：判断对象 a 是否是类 A 的实例
        * 3. 如果 a instanceof A 返回 true，则：
        *       a instanceof superA 返回也是 true，其中 A 是 superA 的子类。
        * */
        if (p2 instanceof Man) {
            Man m2 = (Man)p2;
            m2.earnMoney();
        }

        if (p2 instanceof Woman) {
            System.out.println("Woman");
        }

        if (p2 instanceof Person) {
            System.out.println("Person");
        }

        if (p2 instanceof Object) {
            System.out.println("Object");
        }
    }
}
