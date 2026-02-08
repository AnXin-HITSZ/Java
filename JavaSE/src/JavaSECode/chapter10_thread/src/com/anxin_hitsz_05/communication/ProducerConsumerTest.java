package com.anxin_hitsz_05.communication;

/**
 * ClassName: ProducerConsumerTest
 * Package: com.anxin_hitsz_05.communication
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/8 18:50
 * @Version 1.0
 */

class Clerk {   // 店员
    private int productNum = 0; // 产品的数量

    // 增加产品数量的方法
    public synchronized void addProduct() {

        if (productNum >= 20) {
            // 等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            productNum++;
            System.out.println(Thread.currentThread().getName() + "生产了第 " + productNum + " 个产品");

            // 唤醒
            notifyAll();
        }

    }

    // 减少产品数量的方法
    public synchronized void minusProduct() {

        if (productNum <= 0) {
            // 等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "消费了第 " + productNum + " 个产品");
            productNum--;

            // 唤醒
            notifyAll();
        }

    }
}

class Producer extends Thread {    // 生产者

    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("生产者开始生产产品 ……");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.addProduct();
        }
    }
}

class Consumer extends Thread {    // 消费者

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("消费者开始消费产品 ……");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.minusProduct();
        }
    }
}


public class ProducerConsumerTest {
    public static void main(String[] args) {

        Clerk clerk = new Clerk();

        Producer pro1 = new Producer(clerk);
        Consumer con1 = new Consumer(clerk);
        Consumer con2 = new Consumer(clerk);

        pro1.setName("生产者 1");
        con1.setName("消费者 1");
        con2.setName("消费者 2");

        pro1.start();
        con1.start();
        con2.start();


    }
}
