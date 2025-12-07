package com.anxin_hitsz_03.field_method.method.exer;

/**
 * ClassName: Employee
 * Package: com.anxin_hitsz_03.field_method.field.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/5 20:36
 * @Version 1.0
 */
public class Employee {

    // 属性（或成员变量）
    int id; // 编号
    String name;    // 姓名
    int age;    // 年龄
    double salary;  // 薪资

    // 定义一个方法，用于显示员工的信息
    public void show(){
        System.out.println("id = " + id + "，name = " + name +
                "，age = " + age + "，salary = " + salary);
    }

    public String show1(){
        return "id = " + id + "，name = " + name +
                "，age = " + age + "，salary = " + salary;
    }
}
