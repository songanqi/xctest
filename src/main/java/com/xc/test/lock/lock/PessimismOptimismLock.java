package com.xc.test.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 悲观锁
 */
public class PessimismOptimismLock {

    public static void main(String[] args) {
        // 乐观锁
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }

    // 悲观锁
    public synchronized void testMethod() {

    }
}
