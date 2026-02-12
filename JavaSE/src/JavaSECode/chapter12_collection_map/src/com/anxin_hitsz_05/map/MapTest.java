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

}
