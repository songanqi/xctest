package com.xc.test.threadobjectclasscommonmethods;

/**
 * 两个线程交替打印0-100的奇偶数,用wait和notify
 */
public class WaitNotifyPrintOddEveWait {
    private static int count;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // 第一种方法 -- 防止线程开始不是0
//        new Thread(new TurningRunner(), "偶数").start();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(new TurningRunner(), "奇数").start();

        // 第二种方法  -- 防止线程开始不是0
        new Thread(new TurningRunner()).start();
        new Thread(new TurningRunner()).start();
    }
    // 1.拿到锁,就打印
    // 2.打印完,唤醒其他线程,自己就休眠
    static class  TurningRunner implements Runnable {
        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                    if (count <= 100) {
                        try {
                            // 如果任务还没结束,就让出当前的锁,并自己休眠
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
