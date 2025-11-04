package com.anxin_hitsz_01.one.exer1;

/**
 * ClassName: ArrayExer
 * Package: com.anxin_hitsz_01.one.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2025/11/4 19:41
 * @Version 1.0
 */
public class ArrayExer {
    public static void main(String[] args) {
        int[] arr = new int[] { 8, 2, 1, 0, 3 };
        int[] index = new int[] { 2, 0, 3, 2, 4, 0, 1, 3, 2, 3, 3 };

        String tel = "";

        for (int i = 0; i < index.length; i++) {
            int value = index[i];
            tel += arr[value];
        }
        System.out.println("联系方式：" + tel);  // 18013820100

    }
}
