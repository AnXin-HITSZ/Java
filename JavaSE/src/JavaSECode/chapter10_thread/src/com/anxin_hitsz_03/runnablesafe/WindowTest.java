package com.anxin_hitsz_03.runnablesafe;

/**
 * ClassName: WindowTest
 * Package: com.anxin_hitsz_03.notsafe
 * Description:
 *      使用实现 Runnable 接口的方式，实现卖票 -> 存在线程安全问题的
 *      使用同步代码块解决上述卖票中的线程安全问题
 * @Author AnXin
 * @Create 2026/2/7 20:31
 * @Version 1.0
 */

class SaleTicket implements Runnable {
    int ticket = 100;
    Object obj = new Object();
    Dog dog = new Dog();

    @Override
    public void run() {
//        synchronized (this) {
        while (true) {

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            synchronized (obj) {    // obj：是唯一的？yes
//            synchronized (dog) {    // dog：是唯一的？yes
            synchronized (this) {    // this：是唯一的？yes，就是题目中的 s
                if (ticket > 0) {

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }

        }

    }

}

public class WindowTest {
    public static void main(String[] args) {
        SaleTicket s = new SaleTicket();

        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        Thread t3 = new Thread(s);

        t1.setName("窗口 1");
        t2.setName("窗口 2");
        t3.setName("窗口 3");

        t1.start();
        t2.start();
        t3.start();

    }
}

class Dog {

}
