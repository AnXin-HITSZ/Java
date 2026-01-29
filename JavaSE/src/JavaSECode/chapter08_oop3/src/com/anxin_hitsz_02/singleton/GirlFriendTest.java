package com.anxin_hitsz_02.singleton;

/**
 * ClassName: GirlFriendTest
 * Package: com.anxin_hitsz_02.singleton
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 17:24
 * @Version 1.0
 */
public class GirlFriendTest {
    public static void main(String[] args) {

    }
}

// 懒汉式
class GirlFriend {

    // 1. 类的构造器私有化
    private GirlFriend() {

    }

    // 2. 声明当前类的实例
    // 4. 此属性也必须声明为 static 的
    private static GirlFriend instance = null;

    // 3. 通过 getXxx() 获取当前类的实例，如果未创建对象，则在方法内部进行创建
    public static GirlFriend getInstance() {
        if (instance == null) {
            instance = new GirlFriend();
        }

        return instance;

    }
}
