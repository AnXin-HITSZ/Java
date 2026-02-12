package com.anxin_hitsz_03.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: ListTest
 * Package: com.anxin_hitsz_03.list
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 14:55
 * @Version 1.0
 */
public class ListTest {
    @Test
    public void test1() {
        List list = new ArrayList();

        // add(Object obj)
        list.add("AA");
        list.add("BB");
        list.add(123);  // 自动装箱
        list.add(new Person("Tom", 12));

        System.out.println(list.toString());

        // add(int index, Object ele)
        list.add(2, "CC");
        System.out.println(list);

        // addAll(int index, Collection eles)
        List list1 = Arrays.asList(1, 2, 3);

        list.addAll(1, list1);

//        list.add(1, list1); // 将 list1 整体作为一个元素，插入到索引 1 的位置
        System.out.println(list);

    }

    @Test
    public void test2() {
        List list = new ArrayList();
        // add(Object obj)
        list.add("AA");
        list.add("BB");
        list.add(123);  // 自动装箱
        list.add(2);    // 自动装箱
        list.add(new Person("Tom", 12));

        // 删除索引 2 的元素
//        list.remove(2);
//        System.out.println(list);
//        System.out.println(list.get(2));

        // 删除数据 2
        list.remove(Integer.valueOf(2));
        System.out.println(list);

    }

    @Test
    public void test3() {
        List list = new ArrayList();
        // add(Object obj)
        list.add("AA");
        list.add("BB");
        list.add(123);  // 自动装箱
        list.add(2);    // 自动装箱
        list.add(new Person("Tom", 12));

        // 遍历方式 1：使用迭代器
//        Iterator iterator = list.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

        // 遍历方式 2：增强 for 循环
//        for (Object obj : list) {
//            System.out.println(obj);
//        }

        // 遍历方式 3：一般的 for 循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

}
