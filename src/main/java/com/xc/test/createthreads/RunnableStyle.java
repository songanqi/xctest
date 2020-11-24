package com.xc.test.createthreads;

/**
 * 用 Runnable方式创建线程
 */
public class RunnableStyle implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("用 Runnable方式实现线程");
    }
}
