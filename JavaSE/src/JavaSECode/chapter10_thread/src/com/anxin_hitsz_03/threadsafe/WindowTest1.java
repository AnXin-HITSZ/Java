package com.anxin_hitsz_03.threadsafe;

/**
 * ClassName: WindowTest1
 * Package: com.anxin_hitsz_03.threadsafe
 * Description:
 *      使用同步方法解决继承 Thread 类中的线程安全问题
 * @Author AnXin
 * @Create 2026/2/7 21:46
 * @Version 1.0
 */

class Window1 extends Thread {
    static int ticket = 100;
    static Object obj = new Object();
    static boolean isFlag = true;

    @Override
    public void run() {

        while (isFlag) {
            show();
        }

    }

//    public synchronized void show() {    // 此时同步监视器：this；此题目中 this 为 w1、w2、w3，仍然是线程不安全的
    public static synchronized void show() {    // 此时同步监视器：当前类本身，即为 Window1.class，是唯一的
        if (ticket > 0) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket);
            ticket--;
        } else {
            isFlag = false;
        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w1 = new Window1();
        Window1 w2 = new Window1();
        Window1 w3 = new Window1();

        w1.setName("窗口 1");
        w2.setName("窗口 2");
        w3.setName("窗口 3");

        w1.start();
        w2.start();
        w3.start();

    }


}
