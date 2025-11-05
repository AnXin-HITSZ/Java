package com.anxin_hitsz_02.two;

/**
 * ClassName: TwoArrayTest1
 * Package: com.anxin_hitsz_02.two
 * Description:
 *
 * @Author AnXin
 * @Create 2025/11/5 15:15
 * @Version 1.0
 */
public class TwoArrayTest1 {
    public static void main(String[] args) {
        // 5. 数组元素的默认初始化值
        // 以动态初始化方式 2 说明：
        int[][] arr4 = new int[4][];
        // 外层元素默认值：
        System.out.println(arr4[0]);    // null
        // 内层元素默认值：
        System.out.println(arr4[0][0]); // 报错

        String[][] arr5 = new String[5][];
        // 外层元素默认值：
        System.out.println(arr5[0]);    // null
        // 内层元素默认值：
        System.out.println(arr5[0][0]); // 报错
    }
}
