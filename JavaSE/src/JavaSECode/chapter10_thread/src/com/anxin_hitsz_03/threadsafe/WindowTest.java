package com.anxin_hitsz_03.threadsafe;

/**
 * ClassName: WindowTest
 * Package: com.anxin_hitsz_03.notsafe
 * Description:
 *      使用继承 Thread 类的方式，实现卖票
 *      使用同步代码块的方式解决线程安全问题
 * @Author AnXin
 * @Create 2026/2/7 20:37
 * @Version 1.0
 */

class Window extends Thread {
    static int ticket = 100;
    static Object obj = new Object();
    @Override
    public void run() {

        while (true) {
//            synchronized (this) {   // this：此时表示 w1、w2、w3，不能保证锁的唯一性
//            synchronized (obj) {   // obj：使用 static 修饰以后，就能保证其唯一性
            synchronized (Window.class) {   // 结构：Class clz = Window.class，是唯一的
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
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口 1");
        w2.setName("窗口 2");
        w3.setName("窗口 3");

        w1.start();
        w2.start();
        w3.start();

    }


}
