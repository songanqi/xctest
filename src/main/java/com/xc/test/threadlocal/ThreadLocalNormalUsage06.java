package com.xc.test.threadlocal;

/**
 * 演示threadLocal用法2 , 避免传递参数的麻烦
 */


public class ThreadLocalNormalUsage06 implements Runnable {
    static ThreadLocalNormalUsage06 o1 = new ThreadLocalNormalUsage06();
    static ThreadLocalNormalUsage06 o2 = new ThreadLocalNormalUsage06();
    int flag = 0;
    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                new Service1().process("张三");
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                new Service1().process("李四");
//            }
//        }).start();
        o1.flag = 0;
        o2.flag = 1;
        Thread t1 = new Thread(o1);
        Thread t2 = new Thread(o2);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        if (flag == 0) {
            new Service1().process("王五");
        }
        if (flag == 1) {
            new Service1().process("赵六");
        }
    }
}

class Service1 {
    public void process(String name) {
        User user = new User(name);
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}
class Service2 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println(Thread.currentThread().getName() + "Service2拿到用户名" + user.name);
        new Service3().process();
    }
}
class Service3 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println(Thread.currentThread().getName() +"Service3拿到用户名" + user.name);
        // 避免内存泄漏
        UserContextHolder.holder.remove();
    }
}

class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}
