package com.anxin_hitsz_04.inputstreamreader;

import org.junit.Test;

import java.io.*;

/**
 * ClassName: InputStreamReaderTest
 * Package: com.anxin_hitsz_04.inputstreamreader
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/27 20:50
 * @Version 1.0
 */
public class InputStreamReaderTest {
    @Test
    public void test1() throws IOException {
        // 创建 File 对象
        File file1 = new File("dbcp_utf-8.txt");

        // 创建流对象
        FileInputStream fis = new FileInputStream(file1);
//        InputStreamReader isr = new InputStreamReader(fis); // 此时使用的是 IDEA 默认的 UTF-8 的字符集
        InputStreamReader isr1 = new InputStreamReader(fis, "utf-8"); // 显式地使用 UTF-8 的字符集

        // 读入操作
        char[] cBuffer = new char[1024];
        int len;
        while ((len = isr1.read(cBuffer)) != -1) {
            String str = new String(cBuffer, 0, len);
            System.out.print(str);
        }

        // 关闭资源
        isr1.close();
    }

    /*
    * 读取到的数据出现了乱码。
    * 因为 dbcp_utf-8.txt 文件使用的是 utf-8 的字符集进行的编码，所以在读取此文件时使用的解码集必须也是 utf-8，
    * 否则会出现乱码！
    * */
    @Test
    public void test2() throws IOException {
        // 创建 File 对象
        File file1 = new File("dbcp_utf-8.txt");

        // 创建流对象
        FileInputStream fis = new FileInputStream(file1);
        InputStreamReader isr1 = new InputStreamReader(fis, "gbk"); // 显式地使用 gbk 的字符集

        // 读入操作
        char[] cBuffer = new char[1024];
        int len;
        while ((len = isr1.read(cBuffer)) != -1) {
            String str = new String(cBuffer, 0, len);
            System.out.print(str);
        }

        // 关闭资源
        isr1.close();
    }

    @Test
    public void test3() throws IOException {
        // 创建 File 对象
        File file1 = new File("dbcp_gbk.txt");

        // 创建流对象
        FileInputStream fis = new FileInputStream(file1);
        InputStreamReader isr1 = new InputStreamReader(fis, "gbk"); // 显式地使用 gbk 的字符集

        // 读入操作
        char[] cBuffer = new char[1024];
        int len;
        while ((len = isr1.read(cBuffer)) != -1) {
            String str = new String(cBuffer, 0, len);
            System.out.print(str);
        }

        // 关闭资源
        isr1.close();
    }

    /*
    * 需求：将 gbk 格式的文件转换为 utf-8 格式的文件存储
    * */
    @Test
    public void test4() throws IOException {
        // 1. 造文件
        File file1 = new File("dbcp_gbk.txt");
        File file2 = new File("dbcp_gbk_to_utf8.txt");

        // 2. 造流
        FileInputStream fis = new FileInputStream(file1);
        // 参数 2 对应的是解码集，必须与 dbcp_gbk.txt 的解码集一致
        InputStreamReader isr = new InputStreamReader(fis, "GBK");

        FileOutputStream fos = new FileOutputStream(file2);
        // 参数 2 指明内存中的字符存储到文件中的字节过程中使用的编码集
        OutputStreamWriter osw = new OutputStreamWriter(fos, "utf8");

        // 3. 读写过程
        char[] cBuffer = new char[1024];
        int len;
        while ((len = isr.read(cBuffer)) != -1) {
            osw.write(cBuffer, 0, len);
        }

        System.out.println("操作完成");

        // 4. 关闭资源
        osw.close();
        isr.close();

    }

}
