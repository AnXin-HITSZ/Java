package com.anxin_hitsz_03.field_method.field.exer1;

/**
 * ClassName: EmployeeTest
 * Package: com.anxin_hitsz_03.field_method.field.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/5 20:39
 * @Version 1.0
 */
public class EmployeeTest {
    public static void main(String[] args) {
        // 创建类的实例（或 创建类的对象、类的实例化）
        Employee emp1 = new Employee();

        System.out.println(emp1);   // 类型@地址值

        emp1.id = 1001;
        emp1.name = "Tom";
        emp1.age = 24;
        emp1.salary = 7800;

        System.out.println("id = " + emp1.id + "，name = " + emp1.name +
                "，age = " + emp1.age + "，salary = " + emp1.salary);

        // 创建 Employee 的第 2 个对象
//        Employee emp3 = emp1;   // 错误写法
        Employee emp2 = new Employee();

        System.out.println("id = " + emp2.id + "，name = " + emp2.name +
                "，age = " + emp2.age + "，salary = " + emp2.salary);

    }
}
