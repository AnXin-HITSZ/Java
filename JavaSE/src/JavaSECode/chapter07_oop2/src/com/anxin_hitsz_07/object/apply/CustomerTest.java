package com.anxin_hitsz_07.object.apply;

/**
 * ClassName: CustomerTest
 * Package: com.anxin_hitsz_07.object.apply
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/28 15:19
 * @Version 1.0
 */
public class CustomerTest {
    public static void main(String[] args) {
        Customer c1 = new Customer("Tom", 12, new Account(2000));
        Customer c2 = new Customer("Tom", 12, new Account(2000));

        System.out.println(c1.equals(c2));  // false -> true

    }
}
