package com.anxin_hitsz_04.override.test2;

import com.anxin_hitsz_04.override.test1.Order;

/**
 * ClassName: SubOrder
 * Package: com.anxin_hitsz_04.override.test2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/1/26 16:21
 * @Version 1.0
 */
public class SubOrder extends Order {

    public void method() {

        orderProtected = 1;
        orderPublic = 1;

        methodProtected();
        methodPublic();



        // 不能访问
//        orderPrivate = 1;
//        orderDefault = 1;

//        methodPrivate();
//        methodDefault();

    }

}
