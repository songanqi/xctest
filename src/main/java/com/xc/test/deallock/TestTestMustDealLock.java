package com.xc.test.deallock;

public class TestTestMustDealLock implements Runnable {
    static Object o1 = new Object();
    static Object o2 = new Object();
    int flag = 1;

    public static void main(String[] args) {
        TestTestMustDealLock r1 = new TestTestMustDealLock();
        TestTestMustDealLock r2 = new TestTestMustDealLock();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }



    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("线程1成功获取两把锁");
                }
            }
        }
        if (flag == 0) {
            synchronized(o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(o1) {
                    System.out.println("线程2成功获取两把锁");
                }
            }
        }
    }
}
