package com.anxin_hitsz_09.bean_uml;

/**
 * ClassName: UserTest
 * Package: com.anxin_hitsz_09.bean_uml
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 17:43
 * @Version 1.0
 */
public class UserTest {
    public static void main(String[] args) {
        User u1 = new User();

        System.out.println(u1.age);

        User u2 = new User(2);
        System.out.println(u2.age);
        u2.age = 3;
        u2.age = 4;
    }
}

class User {
    // 属性（或实例变量）
    String name;
    int age = 10;

    public User() {

    }

    public User(int a) {
        age = a;
    }

    // 静态变量（或类变量，非实例变量）
//    static int age;

}
