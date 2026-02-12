package com.anxin_hitsz_02.iterator;

import com.anxin_hitsz_01.collection.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * ClassName: IteratorTest
 * Package: com.anxin_hitsz_02.iterator
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 13:14
 * @Version 1.0
 */
public class IteratorTest {
    @Test
    public void test1() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("AA");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);  // 自动装箱
        coll.add(new String("尚硅谷"));

        // 获取迭代器对象
        Iterator iterator = coll.iterator();
//        System.out.println(iterator.getClass());

        // 方式 1：
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());

//        System.out.println(iterator.next());    // 如果超出了集合中元素的个数，会报 NoSuchElementException 异常

        // 方式 2：
//        for (int i = 0; i < coll.size(); i++) {
//            System.out.println(iterator.next());
//        }

        // 方式 3：推荐
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test2() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("BB");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);  // 自动装箱
        coll.add(new String("尚硅谷"));

        // 方式 1：错误的遍历
//        Iterator iterator = coll.iterator();
//
//        while ((iterator.next()) != null) {
//            System.out.println(iterator.next());
//        }

        // 方式 2：错误的遍历
        // 每次调用 coll.iterator()，都会得到一个新的迭代器对象
//        while (coll.iterator().hasNext()) {
//            System.out.println(coll.iterator().next());
//        }

    }

}
