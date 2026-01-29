package com.anxin_hitsz_01._static.exer1;

/**
 * ClassName: AccountTest
 * Package: com.anxin_hitsz_01._static.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/29 15:48
 * @Version 1.0
 */
public class AccountTest {
    public static void main(String[] args) {

        Account acct1 = new Account();
        System.out.println(acct1);

        Account acct2 = new Account("123456", 2000);
        System.out.println(acct2);

        Account.setInterestRate(0.0123);
        Account.setMinBalance(10);

        System.out.println("银行存款的利率为：" + Account.getInterestRate());
        System.out.println("银行最小存款额度为：" + Account.getMinBalance());
    }
}
