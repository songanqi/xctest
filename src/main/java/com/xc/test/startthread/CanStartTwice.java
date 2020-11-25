package com.xc.test.startthread;

/**
 * 演示不能两次调用start方法,否则或报错
 */
public class CanStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
