package com.xc.test.threadlocal;
/**
 * 1000个打印日期的任务，用线程池来执行
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalNormalUsage03 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {

            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage03().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();

    }
    public String date(int seconds) {
        // 参数的单位是毫秒, 从1970.1.1 00:00:00 计时
        Date date = new Date(1000 * seconds);

        return simpleDateFormat.format(date);
    }
}
