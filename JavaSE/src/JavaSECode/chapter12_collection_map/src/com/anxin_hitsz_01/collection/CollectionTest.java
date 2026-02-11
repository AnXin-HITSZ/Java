package com.anxin_hitsz_01.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * ClassName: CollectionTest
 * Package: com.anxin_hitsz_01.collection
 * Description:
 *      测试 Collection 中方法的使用
 * @Author AnXin
 * @Create 2026/2/11 20:39
 * @Version 1.0
 */
public class CollectionTest {

    /*
    * 2.1 添加
    * */
    @Test
    public void test1() {
        Collection coll = new ArrayList();

        // add()
        coll.add("AA");
        coll.add(123);  // 自动装箱
        coll.add("尚硅谷");
        coll.add(new Object());
        coll.add(new Person("Tome", 12));

        System.out.println(coll);

        // addAll(Collection other)
        Collection coll1 = new ArrayList();
        coll1.add("BB");
        coll1.add(456);

        System.out.println(coll.size());    // 5

        coll.addAll(coll1);
//        coll.add(coll1);

        System.out.println(coll);

        // size()
        System.out.println(coll.size());    // 7

    }

    /*
    * 2.2 判断
    * */
    @Test
    public void test2() {
        Collection coll = new ArrayList();

        // add()
        coll.add("AA");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);  // 自动装箱
        coll.add(new String("尚硅谷"));

        // isEmpty()
        System.out.println(coll.isEmpty());

        // contains(Object obj)
        System.out.println(coll.contains("AA"));    // true
        System.out.println(coll.contains(128)); // true
        System.out.println(coll.contains(new String("尚硅谷")));   // true
        System.out.println(coll.contains(new Person("Tom", 12)));   // false -> true

        // containsAll(Collection coll)
        Collection coll1 = new ArrayList();

        // add()
        coll1.add("AA");
        coll1.add(128);
//        coll1.add("BB");

        System.out.println(coll.containsAll(coll1));

    }

    /*
    * 2.3 删除
    * */
    @Test
    public void test3() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("AA");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);  // 自动装箱
        coll.add(new String("尚硅谷"));
        System.out.println(coll);

//        coll.clear();
//        System.out.println(coll);
//        System.out.println(coll.size());    // 0

        // remove(Object obj)
        coll.remove(new Person("Tom", 12));

        coll.remove("AA");

        System.out.println(coll);

    }

    /*
    * 2.4 其它
    * */
    @Test
    public void test4() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("AA");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);  // 自动装箱
        coll.add(new String("尚硅谷"));

        // 集合 -> 数组
        Object[] arr = coll.toArray();
        System.out.println(Arrays.toString(arr));

        // hashCode()
        System.out.println(coll.hashCode());

    }

    @Test
    public void test5() {
        String[] arr = new String[] { "AA", "BB", "CC" };
        Collection list = Arrays.asList(arr);
        System.out.println(list);

        List list1 = Arrays.asList("AA", "BB", "CC", "DD");
        System.out.println(list1);

    }

    @Test
    public void test6() {
        Integer[] arr = new Integer[] { 1, 2, 3 };
        List list = Arrays.asList(arr);
        System.out.println(list.size());    // 3
        System.out.println(list);

        int[] arr1 = new int[] { 1, 2, 3 };
        List list1 = Arrays.asList(arr1);
        System.out.println(list1.size());   // 1
        System.out.println(list1);

    }

}
