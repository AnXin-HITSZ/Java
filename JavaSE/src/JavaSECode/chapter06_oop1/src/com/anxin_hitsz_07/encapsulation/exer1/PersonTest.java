package com.anxin_hitsz_07.encapsulation.exer1;

/**
 * ClassName: PersonTest
 * Package: com.anxin_hitsz_07.encapsulation.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 13:07
 * @Version 1.0
 */
public class PersonTest {
    public static void main(String[] args) {
        // 创建 Person 实例 1
        Person p1 =  new Person();
//        p1.age = 10;  // 编译不通过
//        System.out.println(p1.age());

        p1.setAge(10);
        System.out.println(p1.getAge());

    }
}
