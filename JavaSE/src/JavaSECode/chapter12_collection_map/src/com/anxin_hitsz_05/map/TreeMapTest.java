package com.anxin_hitsz_05.map;

import org.junit.Test;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeMap;

/**
 * ClassName: TreeMapTest
 * Package: com.anxin_hitsz_05.map
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/13 16:24
 * @Version 1.0
 */
public class TreeMapTest {

    /*
    * 自然排序
    * */
    @Test
    public void test1() {
        TreeMap map = new TreeMap();

        map.put("AA", 89);
        map.put("BB", 78);
        map.put("CC", new Date());
        map.put("DD", 78);

//        map.put(67, 78);  // 报错，因为 key 的类型（Integer）与之前的 key 的类型（String）不一致

        Set entrySet = map.entrySet();
        for (Object entry : entrySet) {
            System.out.println(entry);
        }

    }

    @Test
    public void test2() {
        TreeMap map = new TreeMap();

        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 43);
        User u3 = new User("Rose", 13);
        User u4 = new User("Jack", 23);
        User u5 = new User("Tony", 33);

        map.put(u1, 78);
        map.put(u2, 76);
        map.put(u3, 88);
        map.put(u4, 45);
        map.put(u5, 99);

//        Set entrySet = map.entrySet();
//        for (Object entry : entrySet) {
//            System.out.println(entry);
//        }

//        System.out.println(map.containsKey(new User("Maria", 33)));

    }

    /*
    * 定制排序
    * */
    @Test
    public void test3() {
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;

                    int value = u1.getName().compareTo(u2.getName());
                    if (value != 0) {
                        return value;
                    }
                    return u1.getAge() - u2.getAge();
                }

                throw new RuntimeException("类型不匹配");
            }
        };

        TreeMap map = new TreeMap(comparator);

        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 43);
        User u3 = new User("Rose", 13);
        User u4 = new User("Jack", 23);
        User u5 = new User("Tony", 33);

        map.put(u1, 78);
        map.put(u2, 76);
        map.put(u3, 88);
        map.put(u4, 45);
        map.put(u5, 99);

        Set entrySet = map.entrySet();
        for (Object entry : entrySet) {
            System.out.println(entry);
        }

    }

}
