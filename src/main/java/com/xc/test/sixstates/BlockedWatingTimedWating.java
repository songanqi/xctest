package com.xc.test.sixstates;

/**
 * 展示Blocked   Wating    TimedWating状态
 */
public class BlockedWatingTimedWating implements Runnable{
    public static void main(String[] args) {
        BlockedWatingTimedWating runnable = new BlockedWatingTimedWating();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        // 打印出 timedWating状态,因为正在执行Thread.sleep(1000)
        System.out.println(thread1.getState());
        // 打印出 Blocked状态,因为thread2想拿到syn()的锁却拿不到
        System.out.println(thread2.getState());
        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印出 Wating状态,因为已经执行了wait()方法
        System.out.println(thread1.getState());
    }
    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
