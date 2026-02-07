package com.anxin_hitsz_01.create.thread;

/**
 * ClassName: EvenNumberTest
 * Package: com.anxin_hitsz_01.create.thread
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/6 18:22
 * @Version 1.0
 */

// 1. 创建一个继承于 Thread 类的子类
class PrintNumber extends Thread {
//    private double money = 1000;
    // 2. 重写 Thread 类的 run() -> 将此线程要执行的操作，声明在此方法体中
    @Override
    public void run() {

        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

public class EvenNumberTest {
    public static void main(String[] args) {
        // 3. 创建当前 Thread 的子类的对象
        PrintNumber t1 = new PrintNumber();

        // 4. 通过对象调用 start()
        t1.start();

        /*
        * 问题 1：能否使用 t1.run() 替换 t1.start() 的调用，实现分线程的创建和调用？
        *   不能！
        * */
//        t1.run();

        /*
        * 问题 2：再提供一个分线程，用于 100 以内偶数的遍历。
        *
        * 注意：不能让已经 start() 的线程再次执行 start() 操作，否则报异常 IllegalThreadStateException
        * */
//        t1.start();
        PrintNumber t2 = new PrintNumber();
        t2.start();


        // main() 所在的线程执行的操作：
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i + " **********");
            }
        }

    }
}
