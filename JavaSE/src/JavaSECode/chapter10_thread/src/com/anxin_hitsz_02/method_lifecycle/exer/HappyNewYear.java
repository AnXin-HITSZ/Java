package com.anxin_hitsz_02.method_lifecycle.exer;

/**
 * ClassName: HappyNewYear
 * Package: com.anxin_hitsz_02.method_lifecycle.exer
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/7 22:07
 * @Version 1.0
 */
public class HappyNewYear {
    public static void main(String[] args) {

        for (int i = 10; i >= 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i > 0) {
                System.out.println(i);
            } else {
                System.out.println("Happy New Year!");
            }

        }

    }
}
