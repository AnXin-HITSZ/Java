package com.anxin_hitsz_09.bean_uml;

/**
 * ClassName: Customer
 * Package: com.anxin_hitsz_09.bean_uml
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 18:08
 * @Version 1.0
 */
public class Customer {

    public Customer() {

    }

    private int id;
    private String name;

    public void setId(int i) {
        id = i;
    }
    public int getId() {
        return id;
    }

    public void setName(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }

}
