package com.anxin_hitsz_01.file;

import org.junit.Test;

import java.io.File;

/**
 * ClassName: FileTest
 * Package: com.anxin_hitsz_01.file
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/26 20:32
 * @Version 1.0
 */
public class FileTest {

    /*
    * 构造器
    *
    * 文件的路径表示方式：
    * * 方式 1 - 绝对路径：以 Windows 操作系统为例，包括盘符在内的文件或文件目录的完整路径。
    * * 方式 2 - 相对路径：相对于某一个文件目录来讲的相对的位置。
    *               在 IDEA 中，如果使用单元测试方法：相对于当前的 module 来讲
    *                          如果使用 main() 方法，相对于当前的 project 来讲
    * */

    @Test
    public void test1() {
        // public File(String pathname)
        File file1 = new File("d:/io\\hello.txt");

        File file2 = new File("ab");
        System.out.println(file2.getAbsolutePath());

    }

//    public static void main(String[] args) {
//        File file2 = new File("abc");
//        System.out.println(file2.getAbsolutePath());
//    }

    @Test
    public void test2() {
        // public File(String parent, String child)
        // 参数 1 - parent：一定是一个文件目录
        // 参数 2 - child：可以是一个文件，也可以是一个文件目录
        File file1 = new File("d:\\io", "abc.txt");
        File file2 = new File("abc", "a12");

        // public File(File parent, String child)
        // 参数 1 - parent：一定是一个文件目录
        // 参数 2 - child：可以是一个文件，也可以是一个文件目录
        File file3 = new File(file2, "ab.txt");

    }

}
