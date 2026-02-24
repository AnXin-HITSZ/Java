package com.anxin_hitsz_02.selfdefine;

/**
 * ClassName: SubOrder4
 * Package: com.anxin_hitsz_02.selfdefine
 * Description:
 *      SubOrder4 是泛型类！
 * @Author AnXin
 * @Create 2026/2/24 17:18
 * @Version 1.0
 */
public class SubOrder4<E> extends Order<Integer> {

    E e;

    public SubOrder4() {
    }

    public SubOrder4(E e) {
        this.e = e;
    }

    public SubOrder4(Integer integer, int orderId, E e) {
        super(integer, orderId);
        this.e = e;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

}
