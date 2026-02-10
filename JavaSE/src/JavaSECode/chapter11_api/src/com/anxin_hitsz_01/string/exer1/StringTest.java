package com.anxin_hitsz_01.string.exer1;

import org.junit.Test;

/**
 * ClassName: StringTest
 * Package: com.anxin_hitsz_01.string.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/10 14:54
 * @Version 1.0
 */
public class StringTest {

    /*
    * 题目 2：
    * */

    @Test
    public void test() {
        String s = "abcdefg";
        String s1 = reverse(s, 2, 5);
        String s2 = reverse1(s, 2, 5);
        System.out.println(s1);
        System.out.println(s2);

    }

    /*
    * 方式 1：将 String 转为 char[]，针对 char[] 进行相应位置的反转，反转以后将 char[] 转为 String
    * */
    public String reverse(String str, int fromIndex, int toIndex) {
        //
        char[] arr = str.toCharArray();

        //
        for (int i = fromIndex, j = toIndex; i < j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        //
        return new String(arr);

    }

    /*
    * 方式 2：将 str 分为三部分，将第 1 部分、第 2 部分的反转、第 3 部分依次拼接
    * */
    public String reverse1(String str, int fromIndex, int toIndex) {
        // 获取 str 的第 1 部分
        String finalStr = str.substring(0, fromIndex);  // ab

        // 拼接上第 2 部分
        for (int i = toIndex; i >= fromIndex; i--) {
            finalStr += str.charAt(i);
        }   // abfedc

        // 拼接上第 3 部分
        finalStr = finalStr + str.substring(toIndex + 1);

        return finalStr;

    }

    /*
    * 题目 3：
    * */

    /**
     * 判断 subStr 在 str 中出现的次数
     * @param str
     * @param subStr
     * @return 返回次数
     */
    public int getSubStringCount(String str, String subStr) {
        int count = 0;  // 记录出现的次数

        if (str.length() >= subStr.length()) {
            int index = str.indexOf(subStr);
            while (index >= 0) {
                count++;
                index = str.indexOf(subStr, index + subStr.length());
            }
        }

        return count;
    }

    @Test
    public void test2() {
        String subStr = "ab";
        String str = "abkkcadkabkebfkabkskab";

        int count = getSubStringCount(str, subStr);
        System.out.println(count);

    }

}
