package com.anxin_hitsz_01.create.runnable;

/**
 * ClassName: EvenNumberTest
 * Package: com.anxin_hitsz_01.create.runnable
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/7 15:18
 * @Version 1.0
 */
// 1. 创建一个实现 Runnable 接口的类
class EvenNumberPrint implements Runnable {
//    private double money = 1000;
    // 2. 实现 接口中的 run() -> 将此线程要执行的操作，声明在此方法体中
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

}

public class EvenNumberTest {
    public static void main(String[] args) {
        // 3. 创建当前实现类的对象
        EvenNumberPrint p = new EvenNumberPrint();
        // 4. 将此对象作为参数传递到 Thread 类的构造器中，创建 Thread 类的实例
        Thread t1 = new Thread(p);
        // 5. Thread 类的实例调用 start()：1. 启动线程；2. 调用当前线程的 run()
        t1.start();

        // main() 方法对应的主线程执行的操作
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }

        /*
        * 拓展：再创建一个线程，用于遍历 100 以内的偶数
        * */
        Thread t2 = new Thread(p);
        t2.start();

    }
}
