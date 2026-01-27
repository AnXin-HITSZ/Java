package com.anxin_hitsz_05._super.exer3;

/**
 * ClassName: CheckAccountTest
 * Package: com.anxin_hitsz_05._super.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/27 13:40
 * @Version 1.0
 */
public class CheckAccountTest {
    public static void main(String[] args) {

        CheckAccount checkAccount = new CheckAccount(1122, 20000, 0.045, 5000);

        checkAccount.withdraw(5000);
        System.out.println("您的账户余额：" + checkAccount.getBalance());
        System.out.println("您的可透支额：" + checkAccount.getOverdraft());

        checkAccount.withdraw(18000);
        System.out.println("您的账户余额：" + checkAccount.getBalance());
        System.out.println("您的可透支额：" + checkAccount.getOverdraft());

        checkAccount.withdraw(3000);
        System.out.println("您的账户余额：" + checkAccount.getBalance());
        System.out.println("您的可透支额：" + checkAccount.getOverdraft());

    }
}
