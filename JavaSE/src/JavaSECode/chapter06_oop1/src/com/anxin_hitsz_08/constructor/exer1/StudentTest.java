package com.anxin_hitsz_08.constructor.exer1;

/**
 * ClassName: StudentTest
 * Package: com.anxin_hitsz_08.constructor.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/20 16:18
 * @Version 1.0
 */
public class StudentTest {
    public static void main(String[] args) {

        Student s1 = new Student("An", 21, "哈尔滨工业大学", "计算机技术");
        System.out.println(s1.getInfo());

        Student s2 = new Student("Xin", 21, "哈尔滨工业大学");
        System.out.println(s2.getInfo());

    }
}
