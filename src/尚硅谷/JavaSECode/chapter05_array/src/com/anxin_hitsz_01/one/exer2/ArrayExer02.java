package com.anxin_hitsz_01.one.exer2;

import java.util.Scanner;

/**
 * ClassName: ArrayExer02
 * Package: com.anxin_hitsz_01.one.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2025/11/4 19:46
 * @Version 1.0
 */
public class ArrayExer02 {
    public static void main(String[] args) {
        // 定义包含 7 个单词的数组
        String[] weeks = { "Monday", "Tuesday",  "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

        // 从键盘获取指定的数值，使用 Scanner
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入数值（1 - 7）：");
        int day = scan.nextInt();

        // 针对获取的数据进行判断即可
        if (day < 1 || day > 7) {
            System.out.println("你输入的数据有误。");
        } else {
            System.out.println(weeks[day - 1]);
        }

        scan.close();
    }
}
