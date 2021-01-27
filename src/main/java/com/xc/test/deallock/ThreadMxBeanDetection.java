package com.xc.test.deallock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 用ThreadMxBean检测死锁
 */
public class ThreadMxBeanDetection implements Runnable {
    int flag = 1;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadMxBeanDetection r1 = new ThreadMxBeanDetection();
        ThreadMxBeanDetection r2 = new ThreadMxBeanDetection();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] dealLockedThreads = threadMXBean.findDeadlockedThreads();
        if (dealLockedThreads != null && dealLockedThreads.length > 0) {
            for (int i = 0; i < dealLockedThreads.length; i++) {
                ThreadInfo threadInfo= threadMXBean.getThreadInfo(dealLockedThreads[i]);
                System.out.println("发现死锁啦" + threadInfo.getThreadName());
            }
        }
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
                    System.out.println("线程1成功拿到两把锁");
                }
            }
        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("线程2成功拿到两把锁");
                }
            }
        }
    }
}
