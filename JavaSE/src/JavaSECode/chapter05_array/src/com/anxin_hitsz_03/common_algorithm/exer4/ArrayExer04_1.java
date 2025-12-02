package com.anxin_hitsz_03.common_algorithm.exer4;

/**
 * ClassName: ArrayExer04_1
 * Package: com.anxin_hitsz_03.common_algorithm.exer4
 * Description:
 *
 * @Author AnXin
 * @Create 2025/12/2 11:15
 * @Version 1.0
 */
public class ArrayExer04_1 {
    public static void main(String[] args) {
        // 1. 创建一个名为 `ArrayExer04` 的类，在 `main()` 方法中声明 `array1` 和 `array2` 两个变量，它们是 `int[]` 类型的数组
        int[] array1, array2;
        // 2. 使用大括号 `{}`，把 `array1` 初始化为 8 个素数：`2, 3, 5, 7, 11, 13, 17, 19`
        array1 = new int[]{2, 3, 5, 7, 11, 13, 17, 19};
        // 3. 显示 `array1` 的内容
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + "\t");
        }
        // 4. 复制 `array1` 数组给 `array2`，修改 `array2` 中的偶索引元素，使其等于索引值（如 `array[0] = 0`、`array[2] = 2`）
        array2 = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array2[i] = array1[i];
        }

        System.out.println();
        System.out.println(array1);
        System.out.println(array2);

        for (int i = 0; i < array2.length; i++) {
            if (i % 2 == 0) {
                array2[i] = i;
            }
        }

        System.out.println();   // 换行
        // 5. 打印出 `array1`
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + "\t");
        }
    }
}
