package com.anxin_hitsz_07._abstract.exer2;

import java.util.Scanner;

/**
 * ClassName: PayrollSystem
 * Package: com.anxin_hitsz_07._abstract.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/30 17:55
 * @Version 1.0
 */
public class PayrollSystem {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Employee[] emps = new Employee[2];

        emps[0] = new SalariedEmployee("Tom", 1001,
                new MyDate(1992, 12, 21), 18000);

        emps[1] = new HourlyEmployee("Jerry", 1002, new MyDate(1997, 11, 12),
                240, 100);

        System.out.println("请输入当前的月份：");
        int month = scan.nextInt();

        for (int i = 0; i < emps.length; i++) {
            System.out.println(emps[i].toString());
            System.out.println("工资为：" + emps[i].earnings());

            if (month == emps[i].getBirthday().getMonth()) {
                System.out.println("生日快乐！加薪 100！");
            }
        }

        scan.close();

    }
}
