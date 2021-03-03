package com.xc.test.lock.readwrite;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示公平和非公平读写锁策略
 */
public class NonfairBargeDemo {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read () {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁, 正在读取");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void write () {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取写锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁, 正在写入");
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> write(), "thread1").start();
        new Thread(() -> read(), "thread2").start();
        new Thread(() -> read(), "thread3").start();
        new Thread(() -> write(), "thread4").start();
        new Thread(() -> read(), "thread5").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread thread[] = new Thread[1000];
                for (int i = 0; i < 1000; i++) {
                    thread[i] = new Thread(() -> read(), "子线程创建的Thread" + i);
                }
                for (int i = 0; i < 1000; i++) {
                    thread[i].start();
                }
            }
        }).start();
    }

}
