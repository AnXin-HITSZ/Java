package com.anxin_hitsz_01.method_lifecycle;

/**
 * ClassName: EvenNumberTest
 * Package: com.anxin_hitsz_01.method_lifecycle
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/7 17:38
 * @Version 1.0
 */
class PrintNumber extends Thread {

    public PrintNumber() {

    }

    public PrintNumber(String name) {
        super(name);
    }

    @Override
    public void run() {

        for (int i = 0; i <= 100; i++) {

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " +
                        Thread.currentThread().getPriority() + ": " + i);
            }

//            if (i % 20 == 0) {
//                Thread.yield();
//            }
        }
    }
}

public class EvenNumberTest {
    public static void main(String[] args) {

        PrintNumber t1 = new PrintNumber("线程 1");
        t1.setName("子线程 1");
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();

        Thread.currentThread().setName("主线程");
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);


        // main() 所在的线程执行的操作：
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " +
                        Thread.currentThread().getPriority() + ": " + i);
            }

//            if (i == 20) {
//                try {
//                    t1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        }

//        System.out.println("子线程 1 是否存活？" + t1.isAlive());

    }
}
