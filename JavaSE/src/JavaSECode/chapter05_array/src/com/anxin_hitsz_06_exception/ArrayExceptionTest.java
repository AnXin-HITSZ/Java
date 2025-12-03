package com.anxin_hitsz_06_exception;

/**
 * ClassName: ArrayExceptionTest
 * Package: com.anxin_hitsz_06_exception
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/3 18:04
 * @Version 1.0
 */
public class ArrayExceptionTest {
    public static void main(String[] args) {
        // 1. 数组角标越界的异常：
        int[] arr = new int[10];
        // 角标的有效范围：0、1、2、...、9
//        System.out.println(arr[10]);
//        System.out.println(arr[-1]);

        // 2. 空指针异常：
        // 情况 1：
//        int[] arr1 = new int[10];
//
//        arr1 = null;
//
//        System.out.println(arr1[0]);    // NullPointerException

        // 情况 2：
//        int[][] arr2 = new int[3][];
//
////        arr2[0] = new int[1]; // 此行代码不存在时，下一行代码出现 NullPointerException
//
//        System.out.println(arr2[0][1]); // NullPointerException

        // 情况 3：
        String[] arr3 = new String[4];
        System.out.println(arr3[0].toString()); // NullPointerException
    }
}
