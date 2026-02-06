package com.anxin_hitsz_05.exer.exer1;

import com.anxin_hitsz_08._interface.exer2.ComparableCircle;

/**
 * ClassName: InterfaceTest
 * Package: com.anxin_hitsz_08._interface.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 15:10
 * @Version 1.0
 */
public class InterfaceTest {
    public static void main(String[] args) {

        ComparableCircle c1 = new ComparableCircle(2.3);
        ComparableCircle c2 = new ComparableCircle(5.3);

        try {
            int compareValue = c1.compareTo(c2);
            if (compareValue > 0) {
                System.out.println("c1 对象大");
            } else if (compareValue < 0) {
                System.out.println("c2 对象大");
            } else {
                System.out.println("c1 和 c2 一样大");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
