package com.anxin_hitsz_05.map;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: MapTest
 * Package: com.anxin_hitsz_05.map
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/12 19:52
 * @Version 1.0
 */
public class MapTest {

    /*
    * 测试 Map 中的实现类
    * */

    @Test
    public void test1() {
        Map map = new HashMap();

        map.put(null, null);
        map.put("Tom", 23);
        map.put("CC", new Date());
        map.put(34, "AA");

        System.out.println(map);

    }

    @Test
    public void test2() {
        Map map = new Hashtable();

//        map.put(null, 123);

        map.put("AA", null);

        System.out.println(map);

    }

    @Test
    public void test3() {
        LinkedHashMap map = new LinkedHashMap();

        map.put("Tom", 23);
        map.put("CC", new Date());
        map.put(34, "AA");

        System.out.println(map);

    }

    /*
    * 测试 Map 中的常用方法
    * */

    @Test
    public void test4() {
        HashMap map = new HashMap();

        // 添加：Object put(Object key, Object value)
        map.put("AA", 56);
        map.put(67, "Tom");
        map.put("BB", 78);
        map.put(new Person("Kerry", 12), 56);

        System.out.println(map);
        // int size()
        System.out.println(map.size());

        // Object remove(Object key)
        Object value = map.remove("AA");
        System.out.println(value);
        System.out.println(map);

        // 修改：Object put(Object key, Object value)
        Object oldValue =  map.put("BB", 99);
        System.out.println(oldValue);   // 78
        System.out.println(map);

        // Object get(Object key)
        Object value1 = map.get(67);
        System.out.println(value1);

    }

    // Map 的遍历操作
    @Test
    public void test5() {
        HashMap map = new HashMap();

        map.put("AA", 56);
        map.put(67, "Tom");
        map.put("BB", 78);
        map.put(new Person("Kerry", 12), 56);

        // 遍历 key 集：Set keySet()
        Set keySet = map.keySet();
        // 使用迭代器
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            System.out.println(key);
        }

        // 遍历 value 集：Collection values()
        // 方式 1：推荐
//        Collection values = map.values();
//        // 使用增强 for
//        for (Object obj : values) {
//            System.out.println(obj);
//        }
        // 方式 2：
//        Set keySet1 = map.keySet();
//        for (Object key : keySet1) {
//            Object value = map.get(key);
//            System.out.println(value);
//        }

    }

    @Test
    public void test6() {
        HashMap map = new HashMap();

        map.put("AA", 56);
        map.put(67, "Tom");
        map.put("BB", 78);
        map.put(new Person("Kerry", 12), 56);

        // 方式 1：遍历 entry 集：Set entrySet()
        Set entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            // 方法 1：
//            System.out.println(iterator.next());
            // 方法 2：
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 方式 2：遍历 entry 集：keySet()、get(key)
//        Set keySet = map.keySet();
//        for (Object key : keySet) {
//            System.out.println(key + " -> " + map.get(key));
//        }

    }

}
