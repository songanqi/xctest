package com.xc.test.cas;

/**
 * 模拟CAS操作, 等价代码
 */
public class TwoThreadCompatition implements Runnable{
    private volatile int value;
    public synchronized int compareAndSwap(int expextedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expextedValue) {
            value = newValue;
        }
        return oldValue;
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadCompatition r = new TwoThreadCompatition();
        r.value = 0;
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.value);
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }
}
