package com.xc.test.Threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 演示关闭线程池
 */
public class ShuntDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShuntDownTask());
        }
        Thread.sleep(1500);
//        System.out.println(executorService.isShutdown());
//        executorService.shutdown();
        // 是否是终止状态
//        System.out.println(executorService.isShutdown());
        // 是否全部结束
//        System.out.println(executorService.isTerminated());
//        executorService.execute(new ShuntDownTask());
        // 在一段时间内是否还在运行
//        boolean b = executorService.awaitTermination(7L, TimeUnit.SECONDS);
//        System.out.println(b);
        List<Runnable> runnableList = executorService.shutdownNow();

    }
}

class ShuntDownTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
            e.printStackTrace();
        }
    }
}
