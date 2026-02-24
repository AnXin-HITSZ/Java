package com.anxin_hitsz_01.use.exer2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * ClassName: Exer02
 * Package: com.anxin_hitsz_01.use.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 15:42
 * @Version 1.0
 */
public class Exer02 {
    public static void main(String[] args) {
        // 1. 创建一个 ArrayList 集合对象，并指定泛型为 <Integer>
        ArrayList<Integer> list = new ArrayList<>();

        // 2. 添加 5 个 [0, 100) 以内的随机整数到集合中
        for (int i = 0; i < 5; i++) {
            int random = (int) (Math.random() * (99 - 0 + 1));
            list.add(random);
        }

        // 3. 使用 foreach 遍历输出 5 个整数
        for (Integer value : list) {
            System.out.println(value);
        }

        // 4. 使用集合的 removeIf 方法删除偶数，为 Predicate 接口指定泛型 <Integer>
        list.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer value) {
                return value % 2 == 0;
            }
        });

        System.out.println();

        // 5. 再使用 Iterator 迭代器输出剩下的元素，为 Iterator 接口指定泛型 <Integer>
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            System.out.println(value);
        }

    }

}
