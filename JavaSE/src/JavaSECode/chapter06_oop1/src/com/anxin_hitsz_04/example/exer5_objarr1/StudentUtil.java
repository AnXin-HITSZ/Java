package com.anxin_hitsz_04.example.exer5_objarr1;

/**
 * ClassName: StudentUtil
 * Package: com.anxin_hitsz_04.example.exer5_objarr1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/13 20:39
 * @Version 1.0
 */
public class StudentUtil {
    /*
    * 打印出指定年级的学生信息
    * */
    public void printStudentsWithIndex(Student[] students, int state) {
        for (int i = 0; i< students.length; i++) {
            if (state == students[i].state) {
                Student stu = students[i];
                System.out.println(stu.show());
            }
        }
    }

    /*
    * 遍历指定多个学生数组
    * */
    public void printStudents(Student[] students) {
        for (int i = 0; i< students.length; i++) {
            System.out.println(students[i].show());
        }
    }

    /*
    * 针对于学生数组，按照 score属性从低到高排列
    * */
    public void sortStudents(Student[] students) {
        for (int i = 0; i< students.length; i++) {
            for (int j = 0; j< students.length - 1 - i; j++) {
                if (students[j].score > students[j + 1].score) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }
}
