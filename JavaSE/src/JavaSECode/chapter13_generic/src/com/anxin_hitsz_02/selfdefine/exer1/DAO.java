package com.anxin_hitsz_02.selfdefine.exer1;

import java.util.*;

/**
 * ClassName: DAO
 * Package: com.anxin_hitsz_02.selfdefine.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/24 20:12
 * @Version 1.0
 */
public class DAO<T> {
    Map<String, T> map;

    {
        map = new HashMap<>();
    }

    public void save(String id, T entity) { // 保存 T 类型的对象到 Map 成员变量中
        if (!map.containsKey(id)) {
            map.put(id, entity);
        }
    }

    public T get(String id) {   // 从 Map 中获取 id 对应的对象
        return map.get(id);
    }

    public void update(String id, T entity) {   // 替换 Map 中 key 为 id 的内容，改为 entity 对象
        if (map.containsKey(id)) {
            map.put(id, entity);
        }
    }

    public List<T> list() { // 返回 Map 中存放的所有 T 对象
        // 错误的：
//        Collection<T> values = map.values();
//        System.out.println(values.getClass());
//        return (List<T>)values; // 报 ClassCastException 异常

        // 正确的 - 方式 1：
//        Collection<T> values = map.values();
//        ArrayList<T> list = new ArrayList<>();
//        list.addAll(values);
//
//        return list;

        // 正确的 - 方式 2：
        Collection<T> values = map.values();
        ArrayList<T> list = new ArrayList<>(values);

        return list;
    }

    public void delete(String id) { // 删除指定 id 对象
        map.remove(id);
    }

}
