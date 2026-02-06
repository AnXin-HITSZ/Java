package com.anxin_hitsz_01.create.exer1;

/**
 * ClassName: PrintNumberTest
 * Package: com.anxin_hitsz_01.create.exer1
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/6 18:39
 * @Version 1.0
 */
class EvenNumberPrint  extends Thread { // 用于打印偶数
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }
}

class OddNumberPrint  extends Thread { // 用于打印奇数
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
            }
        }
    }
}

public class PrintNumberTest {
    public static void main(String[] args) {
        // 方式 1：
//        EvenNumberPrint t1 = new EvenNumberPrint();
//        OddNumberPrint t2 = new OddNumberPrint();
//
//        t1.start();
//        t2.start();

        // 方式 2：创建 Thread 类的匿名子类的匿名对象
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + i);
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + " -> " + i);
                    }
                }
            }
        }.start();

    }
}
