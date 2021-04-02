package com.xc.test.future;

import java.util.concurrent.*;

/**
 * 演示get的超市方法,需要注意超时后处理,调用future.cancel().演示cancel传入true和false的区别,代表是否终端正在执行的任务
 */
public class TimeOut {
    private static final Ad DEAFAULT_AD = new Ad("无网络时候的默认广告");

    private static final ExecutorService exec = Executors.newFixedThreadPool(10);
    static class Ad {
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {
        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断了");
                return new Ad("被中断的默认广告");
            }
            return new Ad("旅游订票哪家强?找某程");
        }
    }

    public void printAd() {
        Future<Ad> f = exec.submit(new FetchAdTask());
        Ad ad;
        try {
            ad = f.get(2000, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断的默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("异常时候的默认广告");
        } catch (TimeoutException e) {
            e.printStackTrace();
            ad = new Ad("超市时候的默认广告");
            System.out.println("超市啦,未获取到广告");
            boolean cancel = f.cancel(true);
            System.out.println("cancel的结果" + cancel);
        }
        exec.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        TimeOut timeOut = new TimeOut();
        timeOut.printAd();
    }
}
