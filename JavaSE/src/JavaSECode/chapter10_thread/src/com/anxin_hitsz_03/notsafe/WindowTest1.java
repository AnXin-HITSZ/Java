package com.anxin_hitsz_03.notsafe;

/**
 * ClassName: WindowTest1
 * Package: com.anxin_hitsz_03.notsafe
 * Description:
 *      使用继承 Thread 类的方式，实现卖票
 * @Author AnXin
 * @Create 2026/2/7 20:37
 * @Version 1.0
 */

class Window extends Thread {
    static int ticket = 100;
    @Override
    public void run() {
        while (true) {
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

public class WindowTest1 {
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
