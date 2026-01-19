package com.anxin_hitsz_05.method_more._04recursion.exer2;

/**
 * ClassName: RabbitExer
 * Package: com.anxin_hitsz_05.method_more._04recursion.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/19 12:59
 * @Version 1.0
 */
public class RabbitExer {
    public static void main(String[] args) {
        RabbitExer exer = new RabbitExer();
        int month = 24;
        System.out.println("兔子的对数为：" + exer.getRabbitNumber(month));    // 46368
    }

    public int getRabbitNumber(int month) {
        if (month == 1) {
            return 1;
        } else if (month == 2) {
            return 1;
        } else {
            return getRabbitNumber(month - 1) + getRabbitNumber(month - 2);
        }
    }
}
