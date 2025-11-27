package com.anxin_hitsz_02.two.exer2;

/**
 * ClassName: ArrarExer02
 * Package: com.anxin_hitsz_02.two.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2025/11/27 11:49
 * @Version 1.0
 */
public class ArrarExer02 {
    public static void main(String[] args) {
        // =：赋值符号
        int i = 10;
        int j = i;
        byte b = (byte)i;   // 强制类型转换

        long l = i; // 自动类型转换


        // 举例：数组
        int[] arr1 = new int[10];
        byte[] arr2 = new byte[20];
//        arr1 = arr2;    // 编译不通过。原因：int[]、byte[] 是两种不同类型的引用变量

        System.out.println(arr1);
        System.out.println(arr2);

        int[][] arr3 = new int[3][2];

//        arr3 = arr1;    // 编译不通过。

        arr3[0] = arr1;
        System.out.println(arr3[0]);
        System.out.println(arr1);

        System.out.println(arr3);
    }
}
