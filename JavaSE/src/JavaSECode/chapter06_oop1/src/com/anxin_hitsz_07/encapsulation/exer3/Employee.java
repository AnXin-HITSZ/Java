package com.anxin_hitsz_07.encapsulation.exer3;

/**
 * ClassName: Employee
 * Package: com.anxin_hitsz_07.encapsulation.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 13:19
 * @Version 1.0
 */
public class Employee {
    // 属性
    private String name;
    private char gender;
    private int age;
    private String phoneNumber;

    // 提供属性的 get 和 set 方法
    public void setName(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }

    public void setGender(char g) {
        gender = g;
    }
    public char getGender() {
        return gender;
    }

    public void setAge(int a) {
        age = a;
    }
    public int getAge() {
        return age;
    }

    public void setPhoneNumber(String p) {
        phoneNumber = p;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getinfo() {
//        return name + "\t" + gender + "\t" + age + "\t" + phoneNumber;
        return getName() + "\t" + getGender() + "\t" + getAge() + "\t" + getPhoneNumber();
    }

}
