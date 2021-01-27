package com.xc.test.singleton;

/**
 * 单例模式写法: 懒汉式(线程不安全) (不可用)
 */
public class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
