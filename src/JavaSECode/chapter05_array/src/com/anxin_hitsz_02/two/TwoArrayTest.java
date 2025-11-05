package com.anxin_hitsz_02.two;

/**
 * ClassName: TwoArrayTest
 * Package: com.anxin_hitsz_02.two
 * Description:
 *
 * @Author AnXin
 * @Create 2025/11/5 15:09
 * @Version 1.0
 */
public class TwoArrayTest {
    public static void main(String[] args) {
        // 1. 数组的声明与初始化
        // 方式 1 - 静态初始化：数组变量的赋值和数组元素的赋值同时进行
        int[][] arr2 = new int[][] { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} };

        // 方式 2 - 动态初始化 1：数组变量的赋值和数组元素的赋值分开进行
        String[][] arr3 = new String[3][4];
        // 方式 2 - 动态初始化 2
        double[][] arr4 = new double[2][];

        // 其他正确的写法
        int arr5[][] = new int[][] { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} };
        int[] arr6[] = new int[][] { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} };
        int arr7[][] = { {1, 2, 3}, {4, 5}, {6, 7, 8, 9} }; // 类型推断
        String arr8[][] = new String[3][4];

        // 错误的写法
//        int[][] arr9 = new int[3][3] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
//        int[3][3] arr10 = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
//        int[][] arr11 = new int[][10];

        // 2. 数组元素的调用
        // 针对 arr2 来说，外层元素 {1, 2, 3}、{4, 5}、{6, 7, 8, 9}，内层元素：1、2、3、4、5、6、7、8、9
        // 调用内层元素
        System.out.println(arr2[0][0]); // 1
        System.out.println(arr2[2][1]); // 7

        // 调用外层元素
        System.out.println(arr2[0]);    // [I@27716f4

        // 测试 arr3、arr4
        arr3[0][1] = "Tom";
        System.out.println(arr3[0][1]);
        System.out.println(arr3[0]);    // [Ljava.lang.String;@8efb846

        arr4[0] = new double[4];
        arr4[0][0] = 1.0;

        // 3. 数组的长度
        System.out.println(arr2.length);    // 3
        System.out.println(arr2[0].length); // 3
        System.out.println(arr2[1].length); // 2
        System.out.println(arr2[2].length); // 4
    }
}
