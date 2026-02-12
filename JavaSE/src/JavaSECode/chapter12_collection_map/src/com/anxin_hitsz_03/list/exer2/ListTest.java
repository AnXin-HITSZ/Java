package com.anxin_hitsz_03.list.exer2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ClassName: ListTest
 * Package: com.anxin_hitsz_03.list.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 16:02
 * @Version 1.0
 */
public class ListTest {
    public static void main(String[] args) {

        // 需求 1：随机生成 30 个字符，存放在 ArrayList 中
        ArrayList list = new ArrayList();

        for (int i = 0; i < 30; i++) {
            // 'a' - 'z'：[97, 122]
            list.add((char)(Math.random() * (122 - 97 + 1) + 97) + "");
        }

        System.out.println(list);

        int aCount = listTest(list, "a");
        int bCount = listTest(list, "b");
        int cCount = listTest(list, "c");
        int xCount = listTest(list, "x");

        System.out.println("a：" + aCount);
        System.out.println("b：" + bCount);
        System.out.println("c：" + cCount);
        System.out.println("x：" + xCount);

    }

    // 需求 2：遍历 ArrayList，查找指定的元素出现的次数
    public static int listTest(Collection list, String s) {
        int count = 0;
        for (Object obj : list) {
            if (s.equals(obj)) {
                count++;
            }
        }

        return count;
    }
}
