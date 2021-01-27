package com.xc.test.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不适用volatile的场景
 */
public class NoValatile implements Runnable {
    volatile int a;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable r=  new NoValatile();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((NoValatile) r).a);
        System.out.println(((NoValatile) r).realA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }
}
