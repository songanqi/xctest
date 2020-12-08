package com.xc.test.background;

import javax.xml.bind.Marshaller;

/**
 * 用工厂模式修复刚才的初始化问题
 */
public class MultiThreadError7 {
    int count;
    private EventListener listener;
    private MultiThreadError7(MultiThreadError7.MySource source) {
        listener = new EventListener() {
            @Override
            public void onEvent(MultiThreadError7.Event e) {
                System.out.println("\n我得到的数字是" + count);
            }
        };
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static MultiThreadError7 getInstance(MySource source) {
        MultiThreadError7 safeListener = new MultiThreadError7(source);
        source.registerListener(safeListener.listener);
        return safeListener;
    }

    public static void main(String[] args) {
        MultiThreadError7.MySource mySource = new MultiThreadError7.MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new MultiThreadError7.Event() {
                });
            }
        }).start();
        MultiThreadError7 multiThreadError7 = new MultiThreadError7(mySource);
    }


    static class MySource {
        private MultiThreadError7.EventListener listener;

        void registerListener(MultiThreadError7.EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(MultiThreadError7.Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }
    }

    interface EventListener {
        void onEvent(MultiThreadError7.Event e);
    }
    interface Event {

    }
}
