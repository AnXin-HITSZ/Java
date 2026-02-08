package com.anxin_hitsz_04.threadsafemore.singleton;

/**
 * ClassName: BankTest
 * Package: com.anxin_hitsz_04.threadsafemore.singleton
 * Description:
 *      实现线程安全的懒汉式
 * @Author AnXin
 * @Create 2026/2/8 16:15
 * @Version 1.0
 */
public class BankTest {

    static Bank b1 = null;
    static Bank b2 = null;

    public static void main(String[] args) {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                b1 = Bank.getInstance();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                b2 = Bank.getInstance();
            }
        };

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b1 == b2);

    }
}

class Bank {

    private Bank() {}

    private static volatile Bank instance = null;

    // 实现线程安全的方式 1
//    public static synchronized Bank getInstance() { // 同步监视器：默认为 Bank.class
//        if (instance == null) {
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            instance = new Bank();
//        }
//        return instance;
//    }

    // 实现线程安全的方式 2
//    public static Bank getInstance() { // 同步监视器：默认为 Bank.class
//        synchronized (Bank.class) {
//            if (instance == null) {
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                instance = new Bank();
//            }
//        }
//
//        return instance;
//    }

    // 实现线程安全的方式 3：相较于方式 1 和方式 2 来讲，效率更高；为了避免出现指令重排，需要讲 instance 声明为 volatile
    public static Bank getInstance() { // 同步监视器：默认为 Bank.class
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    instance = new Bank();
                }
            }
        }

        return instance;
    }

}
