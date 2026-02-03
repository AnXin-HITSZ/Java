package com.anxin_hitsz_12.wrapper.exer1;

import java.util.Scanner;
import java.util.Vector;

/**
 * ClassName: ScoreTest
 * Package: com.anxin_hitsz_12.wrapper.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/3 15:26
 * @Version 1.0
 */
public class ScoreTest {
    public static void main(String[] args) {

        // 1. 创建 Vector 对象：Vector v = new Vector();
        Vector v = new Vector();

        // 2. 从键盘获取多个学生成绩，存放到 v 中（以负数代表输入结束）

        Scanner scanner = new Scanner(System.in);

        int maxScore = 0;   // 记录最高分

        while (true) {  // for (;;)
            System.out.print("请输入学生成绩（以负数代表输入结束）：");
            int intScore =  scanner.nextInt();

            if (intScore < 0) {
                break;
            }

//            // 装箱：int -> Integer
//            Integer score = Integer.valueOf(intScore);
//            // 添加学生成绩到容器 v 中
//            v.addElement(score);

            // jdk 5.0 之后：自动装箱
            v.addElement(intScore);

            // 3. 获取学生成绩的最大值
            if (maxScore < intScore) {
                maxScore = intScore;
            }

        }

        System.out.println("最高分：" + maxScore);

        // 4. 依次获取 v 中的每个学生成绩，与最高分进行比较，获取学生等级，并输出
        for (int i = 0; i < v.size(); i++) {
            Object objScore = v.elementAt(i);
            // 方式 1：
//            Integer integerScore = (Integer)objScore;
//            // 拆箱
//            int score = integerScore.intValue();

            // 方式 2：自动拆箱
            int score = (Integer)objScore;
            char grade = ' ';
            if (maxScore - score <= 10) {
                grade = 'A';
            } else if (maxScore - score <= 20) {
                grade = 'B';
            } else if (maxScore - score <= 30) {
                grade = 'C';
            } else {
                grade = 'D';
            }

            System.out.println("student " + i + " score is " + score + " grade is " + grade);

        }

        scanner.close();
    }
}
