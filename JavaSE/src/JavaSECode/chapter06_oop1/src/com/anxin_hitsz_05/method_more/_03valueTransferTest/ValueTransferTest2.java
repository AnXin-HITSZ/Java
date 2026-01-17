package com.anxin_hitsz_05.method_more._03valueTransferTest;

/**
 * ClassName: ValueTransferTest2
 * Package: com.anxin_hitsz_05.method_more._03valueTransferTest
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/17 10:56
 * @Version 1.0
 */
public class ValueTransferTest2 {
    public static void main(String[] args) {
        ValueTransferTest2 test = new ValueTransferTest2();
        int m = 10;
        int n = 20;

        System.out.println("m = " + m + ", n = " + n);

        // 交换两个变量的值
//        int temp = m;
//        m = n;
//        n = temp;

        // 调用方法
        test.swap(m, n);

        System.out.println("m = " + m + ", n = " + n);
    }

    public void swap(int m, int n) {
        int temp = m;
        m = n;
        n = temp;
    }
}
