package com.anxin_hitsz_08._interface.exer2;

/**
 * ClassName: CompareObject
 * Package: com.anxin_hitsz_08._interface.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/31 15:01
 * @Version 1.0
 */
public interface CompareObject {
    // 若返回值是 0，代表相等；若为正数，代表当前对象大；若为负数，代表当前对象小
    public int compareTo(Object o);
}
