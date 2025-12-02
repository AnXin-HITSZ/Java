package com.anxin_hitsz_04.search_sort.exer1;

/**
 * ClassName: ArrayExer01_2
 * Package: com.anxin_hitsz_04.search_sort.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/2 12:16
 * @Version 1.0
 */
public class ArrayExer01_2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        int deleteIndex = 4;

        // 方式 1：不新建数组
//        for (int i = deleteIndex; i < arr.length - 1; i++) {
//            arr[i] = arr[i + 1];
//        }
//
//        // 修改最后元素，设置为默认值
//        arr[arr.length - 1] = 0;

        // 方式 2：新建数组，新的数组的长度比原有数组的长度少 1 个
        int[] newArr = new int[arr.length - 1];
        for (int i = 0; i < deleteIndex; i++) {
            newArr[i] = arr[i];
        }

        for (int i = deleteIndex; i < arr.length - 1; i++) {
            newArr[i] = arr[i + 1];
        }

        arr = newArr;

        // 遍历 arr 数组
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }
}
