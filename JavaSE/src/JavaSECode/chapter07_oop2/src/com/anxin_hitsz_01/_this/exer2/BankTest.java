package com.anxin_hitsz_01._this.exer2;

/**
 * ClassName: BankTest
 * Package: com.anxin_hitsz_01._this.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/23 16:52
 * @Version 1.0
 */
public class BankTest {
    public static void main(String[] args) {
        Bank bank = new Bank();

        bank.addCustomer("三", "张");
        bank.addCustomer("四", "李");

        bank.getCustomer(0).setAccount(new Account(1000));
        bank.getCustomer(0).getAccount().withdraw(50);

        System.out.println("账户余额为：" + bank.getCustomer(0).getAccount().getBalance());
    }
}
