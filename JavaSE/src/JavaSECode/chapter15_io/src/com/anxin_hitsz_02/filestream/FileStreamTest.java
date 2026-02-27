package com.anxin_hitsz_02.filestream;

import org.junit.Test;

import java.io.*;

/**
 * ClassName: FileStreamTest
 * Package: com.anxin_hitsz_02.filestream
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/27 17:06
 * @Version 1.0
 */
public class FileStreamTest {
    /*
    * 需求：复制一份 Stream.jpg 文件，命名为 Stream_copy.jpg
    * */
    @Test
    public void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // 1. 创建相关的 File 类的对象
            File srcFile = new File("Stream.jpg");
            File destFile = new File("Stream_copy.jpg");

            // 2. 创建相关的字节流
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 3. 数据的读入和写出
            byte[] buffer = new byte[1024]; // 1 kb
            int len;    // 记录每次读入到 buffer 中字节的个数
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭资源
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * 需求：复制一份 hello.txt 文件，命名为 hello_copy1.txt
     *
     * 可以使用字节流实现文本文件的复制。
     * */
    @Test
    public void test2() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // 1. 创建相关的 File 类的对象
            File srcFile = new File("hello.txt");
            File destFile = new File("hello_copy1.txt");

            // 2. 创建相关的字节流
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 3. 数据的读入和写出
            byte[] buffer = new byte[5];
            int len;    // 记录每次读入到 buffer 中字节的个数
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭资源
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * 需求：读取 hello.txt 文件，将数据显示在控制台上
     *
     * 可能出现乱码。
     * */
    @Test
    public void test3() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // 1. 创建相关的 File 类的对象
            File srcFile = new File("hello.txt");

            // 2. 创建相关的字节流
            fis = new FileInputStream(srcFile);

            // 3. 数据的读入和写出
            byte[] buffer = new byte[5];
            int len;    // 记录每次读入到 buffer 中字节的个数
            while ((len = fis.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.print(str);
            }

            System.out.println();

            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭资源
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
