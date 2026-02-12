package com.anxin_hitsz_02.iterator;

import com.anxin_hitsz_01.collection.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ClassName: ForTest
 * Package: com.anxin_hitsz_02.iterator
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 13:30
 * @Version 1.0
 */
public class ForTest {
    @Test
    public void test() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("BB");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);  // 自动装箱
        coll.add(new String("尚硅谷"));

        for (Object obj : coll) {
            System.out.println(obj);
        }

    }

    @Test
    public void test2() {
        int[] arr = new int[] { 1, 2, 3, 4 };

        for (int i : arr) {
            System.out.println(i);
        }

    }

    @Test
    public void test3() {
        String[] arr = new String[] { "AA", "BB", "CC", "DD" };

        for (String s : arr) {
            System.out.println(s);
        }

    }

}
