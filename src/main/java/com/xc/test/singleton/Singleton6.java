package com.xc.test.singleton;

/**
 * 单例模式写法: 双重检查 (推荐面试的时候使用)
 * 优点: 线程安全, 延迟加载, 效率及高
 * 要加volatile,因为实例化对象不是原子的,jvm上可能发生重排序
 */
public class Singleton6 {
    private volatile static Singleton6 instance;

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
