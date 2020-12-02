package com.xc.test.threadobjectclasscommonmethods;

/**
 * 3个线程,线程1和线程2首先呗阻塞,线程3唤醒它们,notify(),notifyAll()
 * start先执行不代表线程先启动
 */
public class WaitNotifyAll implements Runnable {
    private static final Object resourceA = new Object();
    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "got resoutceA lock");
            try {
                System.out.println(Thread.currentThread().getName() + "waits to start");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "is waiting to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyAll();
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    resourceA.notifyAll();
//                    resourceA.notify();
                    System.out.println("ThreadC notified");
                }
            }
        });

        threadA.start();
        threadB.start();
        Thread.sleep(200);
        threadC.start();
    }
}
