package com.anxin_hitsz_01.one;

/**
 * ClassName: OneArrayTest
 * Package: com.anxin_hitsz_01.one
 * Description:
 *
 * @Author AnXin
 * @Create 2025/10/20 21:42
 * @Version 1.0
 */
public class OneArrayTest {
    public static void main(String[] args) {
        double[] prices;
        prices = new double[]{20.32, 43.21, 43.22};

        String[] foods = new String[4];
        foods[0] = "拌海蜇";
        foods[1] = "龙须菜";
        foods[2] = "炝冬笋";
        foods[3] = "玉兰片";

        // 4. 如何遍历数组？
        for (int i = 0; i < foods.length; i++) {
            System.out.println(foods[i]);
        }

        for (int i = 0; i < prices.length; i++) {
            System.out.println(prices[i]);
        }
    }
}
