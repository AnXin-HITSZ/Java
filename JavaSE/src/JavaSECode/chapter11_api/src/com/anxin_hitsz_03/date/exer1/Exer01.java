package com.anxin_hitsz_03.date.exer1;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: Exer01
 * Package: com.anxin_hitsz_03.date.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/10 18:50
 * @Version 1.0
 */
public class Exer01 {
    /*
    * 练习：
    * */
    @Test
    public void test1() {
        Date date1 = new Date();

        // 错误的：
//        java.sql.Date date2 = (java.sql.Date) date1;
//        System.out.println(date2);  // ClassCastException

        // 正确的：
        java.sql.Date date2 = new java.sql.Date(date1.getTime());
        System.out.println(date2);

    }

    /*
    * 拓展：
    *   字符串 -> java.util.Date -> java.sql.Date
    * */
    @Test
    public void test2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String pattern = "2026-02-10";

        // 得到 java.util.Date
        Date date1 = sdf.parse(pattern);
        // 转换为 java.sql.Date
        java.sql.Date date2 = new java.sql.Date(date1.getTime());
        System.out.println(date2);

    }
}
