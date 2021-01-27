package com.xc.test.singleton;

/**
 * 单例模式写法1: 饿汉式(静态常量) (可用)
 */
public class Singleton1 {
    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
