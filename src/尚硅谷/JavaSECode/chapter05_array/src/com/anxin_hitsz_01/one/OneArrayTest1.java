package com.anxin_hitsz_01.one;

/**
 * ClassName: OneArrayTest1
 * Package: com.anxin_hitsz_01.one
 * Description:
 *
 * @Author AnXin
 * @Create 2025/10/20 21:42
 * @Version 1.0
 */
public class OneArrayTest1 {
    public static void main(String[] args) {
        // 5. 数组元素的默认初始化值

        // 整型数组元素的默认初始化值：0
        int[] arr1 = new int[3];
        System.out.println(arr1[0]);

        short[] arr2 = new short[4];
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);
        }

        // 浮点型数组元素的默认初始化值：0.0
        double[] arr3 = new double[5];
        System.out.println(arr3[0]);

        // 字符型数组元素的默认初始化值：0（或理解为 '\u0000'）
        char[] arr4 = new char[4];
        System.out.println(arr4[0]);

        if (arr4[0] == 0) {
            System.out.println("1111");
        }

        if (arr4[0] == '0') {
            System.out.println("2222");
        }

        if (arr4[0] == '\u0000') {
            System.out.println("3333");
        }

        System.out.println(arr4[0] + 1);

        // boolean 型数组元素的默认初始化值：false
        boolean[] ar5 = new boolean[4];
        System.out.println(ar5[0]);

        // 引用数据类型数组元素的默认初始化值：null
        String[] arr6 = new String[5];
        for (int i = 0; i < arr6.length; i++) {
            System.out.println(arr6[i]);
        }

        if (arr6[0] == null) {
            System.out.println("aaaa");
        }

        if (arr6[0] == "null") {
            System.out.println("bbbb");
        }
    }
}
