package com.anxin_hitsz_01.file.exer3;

import org.junit.Test;

import java.io.File;

/**
 * ClassName: Exer03
 * Package: com.anxin_hitsz_01.file.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/26 21:43
 * @Version 1.0
 */
public class Exer03 {

    /*
    * 遍历指定文件目录下的所有文件的名称，包括子文件目录中的文件
    * */

    // public void printFileName(File file); // file 可能是文件，也可能是文件目录
    @Test
    public void test1() {
        File file = new File("D:\\Learn\\Java\\JavaSE\\doc\\chapter15_File类与IO流");
        printFileName(file);

    }

    public void printFileName(File file) {
        if (file.isFile()) {
            System.out.println(file.getName());
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                printFileName(f);
            }
        }

    }

}
