package com.anxin_hitsz_01.file.exer2;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;

/**
 * ClassName: Exer02
 * Package: com.anxin_hitsz_01.file.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/26 21:36
 * @Version 1.0
 */
public class Exer02 {
    /*
    * 判断指定目录下是否有后缀名为 .jpg 的文件；如果有，就输出该文件名称
    * */
    @Test
    public void test1() {
        File dir =  new File("D:\\Learn\\Java\\JavaSE\\doc\\chapter15_File类与IO流\\images");

        // 方式 1：
//        String[] listFiles = dir.list();
//        for (String s : listFiles) {
//            if (s.endsWith(".jpg")) {
//                System.out.println(s);
//            }
//        }

        // 方式 2：
        // public String[] list(FilenameFilter filter)
        String[] listFiles = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {  // name：即为子文件或子文件目录的名称
//                if (name.endsWith(".jpg")) {
//                    return true;
//                } else {
//                    return false;
//                }

                return name.endsWith(".jpg");
            }
        });

        for (String s : listFiles) {
            System.out.println(s);
        }

    }
}
