package com.anxin_hitsz_02.singleton;

/**
 * ClassName: BankTest
 * Package: com.anxin_hitsz_02.singleton
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 17:18
 * @Version 1.0
 */
public class BankTest {
    public static void main(String[] args) {
//        Bank bank1 = new Bank();
//        Bank bank2 = new Bank();

        Bank bank1 = Bank.getInstance();
        Bank bank2 = Bank.getInstance();

        System.out.println(bank1 == bank2);
    }
}

// 饿汉式
class Bank {

    // 1. 类的构造器私有化
    private Bank() {

    }

    // 2. 在类的内部创建当前类的实例
    // 4. 此属性也必须声明为 static 的
    private static Bank instance = new Bank();

    // 3. 使用 getXxx() 方法获取当前类的实例，必须声明为 static 的
    public static Bank getInstance() {
        return instance;
    }

}
