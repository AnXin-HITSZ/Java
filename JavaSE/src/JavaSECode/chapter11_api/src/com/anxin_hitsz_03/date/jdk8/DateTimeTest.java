package com.anxin_hitsz_03.date.jdk8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName: DateTimeTest
 * Package: com.anxin_hitsz_03.date.jdk8
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/10 19:54
 * @Version 1.0
 */
public class DateTimeTest {
    @Test
    public void test1() {
        String s1 = "hello";
        String s2 = s1.replace('l', 'w');   // String 的不可变性
        System.out.println(s1); // hello

        // 体会 Calendar 的可变性
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 23);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

    }

    @Test
    public void test2() {
        // 偏移性：Date 中的年份是从 1900 开始的，而月份都从 0 开始
        Date date = new Date(2026, 2, 10);
        System.out.println(date);

    }

    /*
    * JDK 8 的 API：LocalDate / LocalTime / LocalDateTime
    * */
    @Test
    public void test3() {
        // now()：获取当前日期和时间对应的实例
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);  // 2026-02-10
        System.out.println(localTime);  // 20:04:17.835684400
        System.out.println(localDateTime);  // 2026-02-10T20:04:17.835684400

        // of()：获取指定的日期、时间对应的实例
        LocalDate localDate1 = LocalDate.of(2021, 5, 23);
        LocalDateTime localDateTime1 = LocalDateTime.of(2022, 12, 5, 11, 23, 45);
        System.out.println(localDate1);
        System.out.println(localDateTime1);

        // getXxx()
        LocalDateTime localDateTime2 = LocalDateTime.now();
        System.out.println(localDateTime2.getDayOfMonth());
        // 体现了不可变性
        // withXxx()
        LocalDateTime localDateTime3 = localDateTime2.withDayOfMonth(15);
        System.out.println(localDateTime2); // 2026-02-10T20:10:40.026545600
        System.out.println(localDateTime3); // 2026-02-15T20:10:40.026545600
        // plusXxx()
        LocalDateTime localDateTime4 = localDateTime2.plusDays(5);
        System.out.println(localDateTime2); // 2026-02-10T20:12:12.149302300
        System.out.println(localDateTime4); // 2026-02-15T20:12:12.149302300

    }

    /*
    * JDK 8 的 API：Instant
    * */
    @Test
    public void test4() {
        Instant instant = Instant.now();
        System.out.println(instant);    // 2026-02-10T12:18:22.922455100Z
        // 了解：
        OffsetDateTime instant1 = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(instant1);

        Instant instant2 = Instant.ofEpochMilli(24123123312L);
        System.out.println(instant2);   // 1970-10-07T04:52:03.312Z

        long milliTime = instant.toEpochMilli();
        System.out.println(milliTime);

        System.out.println(new Date().getTime());

    }

    /*
    * JDK 8 的 API：DateTimeFormatter
    * */
    @Test
    public void test5() {
        // 自定义的格式：如 ofPattern("yyyy-MM-dd hh:mm:ss")
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        //  格式化：日期、时间 -> 字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String strDateTime = dateTimeFormatter.format(localDateTime);
        System.out.println(strDateTime);    // 2026/02/10 20:27:30

        // 解析：字符串 -> 日期、时间
        TemporalAccessor temporalAccessor = dateTimeFormatter.parse("2026/02/10 15:27:30");
        LocalDateTime localDateTime1 = LocalDateTime.from(temporalAccessor);
        System.out.println(localDateTime1); // 2026-02-10T15:27:30

    }
}
