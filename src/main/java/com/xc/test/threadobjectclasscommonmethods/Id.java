package com.xc.test.threadobjectclasscommonmethods;

/**
 * 线程Id从1始,jvm运行起来后,我们自己创建的线程Id早已不是0
 */
public class Id {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程Id" + Thread.currentThread().getId());
        System.out.println("子线程Id" + thread.getId());
    }
}
