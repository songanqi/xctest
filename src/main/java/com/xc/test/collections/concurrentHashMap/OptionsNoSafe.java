package com.xc.test.collections.concurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 组合操作并不保证线程安全
 */
public class OptionsNoSafe implements Runnable {
    private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<String, Integer>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);
        Thread t1 = new Thread(new OptionsNoSafe());
        Thread t2 = new Thread(new OptionsNoSafe());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(scores);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            while (true) {
                Integer score = scores.get("小明");
                Integer newScore = score + 1;
                // 安全
                boolean b = scores.replace("小明", score, newScore);
                if (b) {
                    break;
                }

                // 不安全
//            scores.put("小明", newScore);
            }
        }
    }
}
