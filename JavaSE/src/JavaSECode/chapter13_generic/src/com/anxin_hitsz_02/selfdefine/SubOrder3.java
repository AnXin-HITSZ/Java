package com.anxin_hitsz_02.selfdefine;

/**
 * ClassName: SubOrder3
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *      SubOrder3 是泛型类！
 * @Author AnXin
 * @Create 2026/2/24 17:16
 * @Version 1.0
 */
public class SubOrder3<T> extends Order<T> {

    public void show(T t) {
        System.out.println(t);
    }

}
