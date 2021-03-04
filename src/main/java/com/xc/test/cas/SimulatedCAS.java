package com.xc.test.cas;

/**
 * 模拟CAS操作, 等价代码
 */
public class SimulatedCAS{
    private volatile int value;
    public synchronized int compareAndSwap(int expextedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expextedValue) {
            value = newValue;
        }
        return oldValue;
    }
}
