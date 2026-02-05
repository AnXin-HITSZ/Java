package com.anxin_hitsz_03._throws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ClassName: ThrowsTest
 * Package: com.anxin_hitsz_03._throws
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 15:44
 * @Version 1.0
 */
public class ThrowsTest {

    public static void main(String[] args) {
        method3();

        try {
            method2();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void method3() {
        try {
            method2();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void method2() throws FileNotFoundException, IOException {

        method1();

    }

    public static void method1() throws FileNotFoundException, IOException {
        File file = new File("D:\\hello.txt");

        FileInputStream fis = new FileInputStream(file);    // 可能报 FileNotFoundException

        int data = fis.read();  // 可能报 IOException
        while (data != -1) {
            System.out.println((char)data);
            data = fis.read();  // 可能报 IOException
        }

        fis.close();    // 可能报 IOException

    }
}
