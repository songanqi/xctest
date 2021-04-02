package com.xc.test.imooccache;

import com.xc.test.imooccache.computtable.ExpensiveFunction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用线程池测试缓存压力
 */
public class ImoocCache12 {
    static ImoocCache10<String, Integer> expensiveComputer = new ImoocCache10<>(new ExpensiveFunction());

    public static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(100);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            service.submit(() -> {
                Integer result = null;
                try {
                    System.out.println(Thread.currentThread().getName() + "开始等待");
                    countDownLatch.await();
                    SimpleDateFormat simpleDateFormat = ThreadSafeFormat.dateFormat.get();
                    String time = simpleDateFormat.format(new Date());
                    System.out.println(Thread.currentThread().getName() + "    " + time + "被放行");
                    result = expensiveComputer.compute("666");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            });
        }
//        service.shutdown();
//        while(!service.isTerminated()) {
//
//        }
//        System.out.println("总耗时: " + (System.currentTimeMillis() - start));
        Thread.sleep(5000);
        countDownLatch.countDown();
        service.shutdown();
    }
}

class ThreadSafeFormat {

    public static ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("mm:ss");
        }

        @Override
        public SimpleDateFormat get() {
            return super.get();
        }
    };
}

class B extends Object{
    static{
        System.out.println("Load B");
    }
    public B(){
        System.out.println("Create B");
    }
}
class A extends B{
    static{
        System.out.println("Load A");
    }
    public A(){
        System.out.println("Create A");2
    }
}

class Testclass{
    public static void main(String[] args){
        new A();
    }
}
