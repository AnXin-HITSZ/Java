package com.anxin_hitsz_07.object.apply;

import java.util.Objects;

/**
 * ClassName: Customer
 * Package: com.anxin_hitsz_07.object.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 15:18
 * @Version 1.0
 */
public class Customer {

    private String name;
    private int age;
    private Account acct;

    public Customer() {
    }

    public Customer(String name, int age, Account acct) {
        this.name = name;
        this.age = age;
        this.acct = acct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Account getAcct() {
        return acct;
    }

    public void setAcct(Account acct) {
        this.acct = acct;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;
        return age == customer.age && Objects.equals(name, customer.name) && Objects.equals(acct, customer.acct);
    }
    
}
