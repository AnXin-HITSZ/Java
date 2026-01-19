package com.anxin_hitsz_05.method_more._04recursion.exer1;

/**
 * ClassName: RecursionExer01
 * Package: com.anxin_hitsz_05.method_more._04recursion.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/19 12:51
 * @Version 1.0
 */
public class RecursionExer01 {
    public static void main(String[] args) {
        RecursionExer01 test = new RecursionExer01();

        System.out.println(test.f(10)); // -3771
        System.out.println(test.func(10));  // 243
    }

    public int f(int n) {
        if (n == 20) {
            return 1;
        } else if (n == 21) {
            return 4;
        } else {
            // 正确的
            return f(n + 2) - 2 * f(n + 1);

            // 错误的：
//            return 2 * f(n - 1) + f(n - 2);
        }
    }

    public int func(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 4;
        } else {
            // 错误的：
//            return func(n + 2) - 2 * func(n + 1);

            // 正确的：
            return 2 * func(n - 2) + func(n - 2);
        }
    }
}
