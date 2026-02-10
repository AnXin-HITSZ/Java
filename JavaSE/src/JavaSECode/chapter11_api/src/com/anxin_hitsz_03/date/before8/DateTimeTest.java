package com.anxin_hitsz_03.date.before8;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName: DateTimeTest
 * Package: com.anxin_hitsz_03.date.before8
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/10 18:13
 * @Version 1.0
 */
public class DateTimeTest {

    /*
    * Date 类的使用
    *
    * |-- java.util.Date
    *   > 两个构造器的使用
    *   > 两个方法的使用：1. toString() 2. long getTime()
    *       |----java.sql.Date：对应着数据库中的 date 类型
    *
    * */
    @Test
    public void test1() {
        Date date1 = new Date();    // 创建一个基于当前系统时间的 Date 的实例
        System.out.println(date1.toString());   // Tue Feb 10 18:22:34 CST 2026

        long milliTimes = date1.getTime();
        System.out.println("对应的毫秒数为：" + milliTimes);    // 1770718580726

        Date date2 = new Date(1370708580726L);  // 创建一个基于指定时间戳的 Date 的实例
        System.out.println(date2.toString());

    }
    @Test
    public void test2() {
        java.sql.Date date1 = new java.sql.Date(1370708580726L);
        System.out.println(date1.toString());   // 2013-06-09

        System.out.println(date1.getTime());    // 1370708580726

    }

    /*
    * SimpleDateFormat 类：用于日期时间的格式化和解析
    *
    * 格式化：日期 -> 字符串
    * 解析：字符串 -> 日期
    *
    * */
    @Test
    public void test3() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat();
        // 格式化：日期 -> 字符串
        Date date1 = new Date();
        String strDate = sdf.format(date1);
        System.out.println(strDate);    // 2026/2/10 18:26

        // 字符串 -> 日期
        Date date2 = sdf.parse("2026/2/10 15:26");
        System.out.println(date2);

    }
    @Test
    public void test4() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 格式化：日期 -> 字符串
        Date date1 = new Date();
        String strDate = sdf.format(date1);
        System.out.println(strDate);    // 2026-02-10 18:32:40

        // 解析：字符串 -> 日期
        Date date2 = sdf.parse("2026-02-10 18:32:40");
        System.out.println(date2);

        // 解析失败，因为参数的字符串不满足 SimpleDateFormat 可以识别的格式
//        Date date3 = sdf.parse("2026/2/10 18:26");
//        System.out.println(date3);

    }

    /*
    * Calendar：日历类
    *   1. 实例化：由于 Calendar 是一个抽象类，所以我们需要创建其子类的实例；这里我们通过 Calendar 的静态方法
    *             getInstance() 即可获取
    *   2. 常用方法：get(int field) / set(int field, xx) / add(int field, xx) / getTime() / setTime()
    * */
    @Test
    public void test5() {
        Calendar calendar = Calendar.getInstance();

//        System.out.println(calendar.getClass());

        // 测试方法
        // get(int field)
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        // set(int field, xx)
        calendar.set(Calendar.DAY_OF_MONTH, 23);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        // add(int field, xx)
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        calendar.add(Calendar.DAY_OF_MONTH, -5);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        // getTime()：Calendar -> Date
        Date date = calendar.getTime();
        System.out.println(date);

        // setTime()：使用指定的 Date 重置 Calendar
        Date date1 = new Date();
        calendar.setTime(date1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

    }
}
