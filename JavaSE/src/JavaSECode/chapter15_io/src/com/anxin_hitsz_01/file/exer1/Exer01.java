package com.anxin_hitsz_01.file.exer1;

import java.io.File;

/**
 * ClassName: Exer01
 * Package: com.anxin_hitsz_01.file.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/26 21:33
 * @Version 1.0
 */
public class Exer01 {
    public static void main(String[] args) {
        // 创建一个与 hello.txt 文件在相同文件目录下的另一个名为 abc.txt 文件
        File file1 = new File("hello.txt");

        System.out.println(file1.getAbsolutePath());

        // 获取 file1 的绝对路径，获取此路径的上层文件目录
//        System.out.println(file1.getAbsoluteFile().getParent());

        File file2 = new File(file1.getAbsoluteFile().getParent(), "abc.txt");

        System.out.println(file2.getAbsolutePath());

    }
}
