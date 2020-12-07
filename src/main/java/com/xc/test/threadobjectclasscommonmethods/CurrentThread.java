package com.xc.test.threadobjectclasscommonmethods;

/**
 * 演示打印main,thread-0,Thread-1
 */
public class CurrentThread implements Runnable {
    public static void main(String[] args) {
        new CurrentThread().run();
        new Thread(new CurrentThread()).start();
        new Thread(new CurrentThread()).start();
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
