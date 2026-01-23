package com.anxin_hitsz_01._this;

/**
 * ClassName: UserTest
 * Package: com.anxin_hitsz_01._this
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/23 15:30
 * @Version 1.0
 */
public class UserTest {
    public static void main(String[] args) {

        // 只创建了 User 类的 1 个对象
        User u1 = new User("Tom", 12);
    }
}

class User {
    String name;
    int age;

    public User() {
        // 模拟对象创建时，需要初始化 50 行代码
    }
    public User(String name) {
        this();
        this.name = name;
    }
    public User(String name, int age) {
        this(name);
//        this.name = name;
        this.age = age;
    }

//    private void init() {
//        // 模拟对象创建时，需要初始化 50 行代码
//    }

}
