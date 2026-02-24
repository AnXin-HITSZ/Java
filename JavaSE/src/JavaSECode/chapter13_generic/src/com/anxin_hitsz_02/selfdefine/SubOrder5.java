package com.anxin_hitsz_02.selfdefine;

/**
 * ClassName: SubOrder5
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *      SubOrder5 是泛型类！
 * @Author AnXin
 * @Create 2026/2/24 17:22
 * @Version 1.0
 */
public class SubOrder5<T, E> extends Order<T> {
    E e;

    public SubOrder5() {
    }

    public SubOrder5(T t, int orderId, E e) {
        super(t, orderId);
        this.e = e;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

}
