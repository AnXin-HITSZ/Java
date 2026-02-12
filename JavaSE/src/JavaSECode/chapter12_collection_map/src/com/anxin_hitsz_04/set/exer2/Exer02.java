package com.anxin_hitsz_04.set.exer2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * ClassName: Exer02
 * Package: com.anxin_hitsz_04.set.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 18:58
 * @Version 1.0
 */
public class Exer02 {
    public static void main(String[] args) {

        Set set = new HashSet();

        while (set.size() < 10) {
            int random = (int)(Math.random() * (20 - 1 + 1) + 1);
            set.add(random);
        }

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}
