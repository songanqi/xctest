package com.xc.test.syn;

/**
 * 可重入粒度测试: 递归调用本方法
 */
public class SynchronizedRecursion10 {
    int a = 0;

    public static void main(String[] args) {
        SynchronizedRecursion10 synchronizedRecursion10 = new SynchronizedRecursion10();
        synchronizedRecursion10.method1();
    }

    private void method1() {
        System.out.println("这里是method1, a=" + a);
        if (a == 0) {
            a++;
            method1();
        }
    }
 }
