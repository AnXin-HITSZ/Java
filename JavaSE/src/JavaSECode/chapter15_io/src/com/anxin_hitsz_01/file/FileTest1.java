package com.anxin_hitsz_01.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * ClassName: FileTest1
 * Package: com.anxin_hitsz_01.file
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/26 20:56
 * @Version 1.0
 */
public class FileTest1 {

    /*
    * 获取文件和目录基本信息
    * */

    @Test
    public void test1() {
        File file1  = new File("hello.txt");
        System.out.println(file1.getName());
        System.out.println(file1.getPath());
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getParent());
        System.out.println(file1.getAbsoluteFile().getParent());
        System.out.println(file1.length());
        System.out.println(file1.lastModified());

    }

    @Test
    public void test2() {
        File file1  = new File("D:\\io\\io1");
        System.out.println(file1.getName());
        System.out.println(file1.getPath());
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getParent());
        System.out.println(file1.getAbsoluteFile().getParent());
        System.out.println(file1.length());
        System.out.println(file1.lastModified());

    }

    /*
    * 列出目录的下一级
    * */

    @Test
    public void test3() {
        File file1 = new File("D:\\Learn\\Java\\JavaSE\\doc\\chapter15_File类与IO流");
        String[] fileArr = file1.list();
        for (String s : fileArr) {
            System.out.println(s);
        }

        System.out.println();

        File[] files = file1.listFiles();
        for (File f : files) {
            System.out.println(f.getName());
        }

    }

    /*
    * File 类的重命名功能
    * */

    @Test
    public void test4() {
        File file1 = new File("hello.txt");

        File file2 = new File("d:\\io\\abc.txt");

        boolean renameSuccess = file1.renameTo(file2);
        System.out.println(renameSuccess ? "重命名成功" : "重命名失败");

    }

    /*
    * 判断功能的方法
    * */

    @Test
    public void test5() {
        File file1 = new File("d:\\io\\abc.txt");
        System.out.println(file1.exists());
        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());

        System.out.println();

        File file2 = new File("d:\\ioo");
        System.out.println(file2.exists());
        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());

    }

    /*
    * 创建、删除功能
    * */

    @Test
    public void test6() throws IOException {
        File file1 = new File("d:\\io\\hello.txt");
        // 测试文件的创建、删除
        if (!file1.exists()) {
            boolean isSuccessed = file1.createNewFile();
            if (isSuccessed) {
                System.out.println("创建成功");
            }
        } else {
            System.out.println("此文件已存在");

            System.out.println(file1.delete() ? "文件删除成功" : "文件删除失败");
        }

    }

    @Test
    public void test7() {
        // 前提：d:\\io 文件目录存在，io2 或 io3 目录是不存在的
        File file1 = new File("d:\\io\\io2");

        System.out.println(file1.mkdir());  // true

        File file2 = new File("d:\\io\\io3");

        System.out.println(file2.mkdirs()); // true

    }

    @Test
    public void test8() {
        // 前提：d:\\io 文件目录存在，io2 或 io3 目录是不存在的
        File file1 = new File("d:\\io\\io2\\io4");

        System.out.println(file1.mkdir());  // false

        File file2 = new File("d:\\io\\io3\\io5");

        System.out.println(file2.mkdirs()); // true

    }

    @Test
    public void test9() {
        File file1 = new File("d:\\io\\io3");

        System.out.println(file1.delete());
    }

}
