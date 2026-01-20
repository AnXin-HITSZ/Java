package com.anxin_hitsz_08.constructor.exer3;

/**
 * ClassName: Customer
 * Package: com.anxin_hitsz_08.constructor.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 16:40
 * @Version 1.0
 */
public class Customer {

    private String firstName;
    private String lastName;
    private Account account;

    public Customer(String f, String l) {
        firstName = f;
        lastName = l;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setAccount(Account a) {
        account = a;
    }
    public Account getAccount() {
        return account;
    }

}
