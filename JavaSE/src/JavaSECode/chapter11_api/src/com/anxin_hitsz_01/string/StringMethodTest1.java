package com.anxin_hitsz_01.string;

import org.junit.Test;

/**
 * ClassName: StringMethodTest1
 * Package: com.anxin_hitsz_01.string
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/9 21:17
 * @Version 1.0
 */
public class StringMethodTest1 {

    /*
    * 系列 1：常用方法
    * */

    @Test
    public void test1() {
        String s1 = "";
        String s2 = new String();
        String s3 = new String("");

        System.out.println(s1.isEmpty());
        System.out.println(s2.isEmpty());
        System.out.println(s3.isEmpty());

        String s4 = null;
        System.out.println(s4.isEmpty());   // 报空指针异常

        String s5 = "hello";
        System.out.println(s5.length());    // 5

    }

    @Test
    public void test2() {
        String s1 = "hello";
        String s2 = "HellO";
        System.out.println(s1.equals(s2));
        System.out.println(s1.equalsIgnoreCase(s2));

        String s3 = "abcd";
        String s4 = "adef";
        System.out.println(s3.compareTo(s4));

        String s5 = "abcd";
        String s6 = "aBcd";
        System.out.println(s5.compareTo(s6));
        System.out.println(s5.compareToIgnoreCase(s6));

        String s7 = "张ab";
        String s8 = "李cd";
        System.out.println(s7.compareTo(s8));

        String s9 = " he  llo   ";
        System.out.println("*****" + s9.trim() + "*****");

    }

    /*
    * 系列 2：查找
    * */

    @Test
    public void test3() {
        String s1 = "教育尚硅谷教育";
        System.out.println(s1.contains("硅谷"));

        System.out.println(s1.indexOf("教育"));
        System.out.println(s1.indexOf("教育", 1));

        System.out.println(s1.lastIndexOf("教育"));
        System.out.println(s1.lastIndexOf("教育", 4));

    }

    /*
    * 系列 3：字符串截取
    * */

    @Test
    public void test4() {
        String s1 = "教育尚硅谷教育";
        System.out.println(s1.substring(2));
        System.out.println(s1.substring(2, 5)); // [2, 5)

    }

    /*
    * 系列 4：和字符 / 字符数组相关
    *
    * 系列 5：开头与结尾
    * */

    @Test
    public void test5() {
        String s1 = "教育尚硅谷教育";
        System.out.println(s1.charAt(2));

        String s2 = String.valueOf(new char[] { 'a', 'b', 'c' });
        String s3 = String.copyValueOf(new char[] { 'a', 'b', 'c' });
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s2 == s3);

        System.out.println(s1.startsWith("教育a"));
        System.out.println(s1.startsWith("教育", 5));

    }

    /*
    * 系列 6：替换
    * */

    @Test
    public void test6() {
        String s1 = "hello";
        String s2 = s1.replace('l', 'w');
        System.out.println(s1);
        System.out.println(s2);

        String s3 = s1.replace("ll", "wwww");
        System.out.println(s3);

    }

}
