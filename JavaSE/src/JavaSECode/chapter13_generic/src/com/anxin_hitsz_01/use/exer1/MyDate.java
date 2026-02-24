package com.anxin_hitsz_01.use.exer1;

/**
 * ClassName: MyDate
 * Package: com.anxin_hitsz_01.use.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 15:01
 * @Version 1.0
 */
public class MyDate implements Comparable<MyDate> {
    private int year;
    private int month;
    private int day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "年" + month + "月" + day + "日";
    }

    // 按照生日从小到大排列
    @Override
    public int compareTo(MyDate o) {
        int yearDistinct = this.getYear() - o.getYear();
        if (yearDistinct != 0) {
            return yearDistinct;
        }

        int monthDistinct = this.getMonth() - o.getMonth();
        if (monthDistinct != 0) {
            return monthDistinct;
        }

        return this.getDay() - o.getDay();

    }

}
