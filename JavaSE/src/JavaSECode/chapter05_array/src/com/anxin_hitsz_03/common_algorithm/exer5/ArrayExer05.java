package com.anxin_hitsz_03.common_algorithm.exer5;

/**
 * ClassName: ArrayExer05
 * Package: com.anxin_hitsz_03.common_algorithm.exer5
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/2 11:51
 * @Version 1.0
 */
public class ArrayExer05 {
    public static void main(String[] args) {
        int[] arr = new int[]{34, 54, 3, 2, 65, 7, 34, 5, 76, 34, 67};

        // 遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();

        //反转操作
        // 方式 1：
//        for (int i = 0; i < arr.length / 2; i++) {
//            // 交互 arr[i] 与 arr[arr.length - 1 - i] 位置的元素
//            int temp = arr[i];
//            arr[i] = arr[arr.length - i - 1];
//            arr[arr.length - i - 1] = temp;
//        }
        // 方式 2：
        for (int i = 0, j = arr.length - 1 /* 尾索引 */; i < j; i++, j--) {
            // 交互 arr[i] 与 arr[j] 位置的元素
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        // 方式 3（不推荐）：
//        int[] newArr = new int[arr.length];
//        for (int i = arr.length - 1; i >= 0; i--) {
//            newArr[arr.length - 1 - i] = arr[i];
//        }
//
//        arr = newArr;

        // 遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }
}
