package com.anxin_hitsz_04.example.exer5_objarr1;

/**
 * ClassName: StudentTest
 * Package: com.anxin_hitsz_04.example.exer5_objarr
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/13 20:19
 * @Version 1.0
 */
public class StudentTest {
    public static void main(String[] args) {

        // 创建 Student[]
        Student[] students = new Student[20];    // String[] strs = new String[20];

        // 使用循环，给数组的元素赋值
        for  (int i = 0; i < students.length; i++) {
            students[i] = new Student();
            // 给每一个学生对象的 number、state、score 属性赋值
            students[i].number = i;
            students[i].state = (int)(Math.random() * 6 + 1);
            students[i].score = (int)(Math.random() * 101);
        }
        // 需求 1：打印出 3 年级（state 值为 3）的学生信息
        StudentUtil util = new StudentUtil();
        util.printStudentsWithIndex(students, 3);

        System.out.println("********************************************");

        // 需求 2 ：使用冒泡排序按学生成绩排序，并遍历所有学生信息
        // 排序前遍历
        util.printStudents(students);

        System.out.println("********************************************");
        util.sortStudents(students);

        // 排序后遍历
        util.printStudents(students);
    }
}
