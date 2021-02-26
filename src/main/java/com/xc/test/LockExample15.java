package com.xc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 展示lock的方法
 */
public class LockExample15 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
        lock.tryLock();
    }
}
