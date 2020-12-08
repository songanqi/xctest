package com.xc.test.background;

import org.springframework.data.repository.query.Param;

/**
 * 初始化未完毕,就this赋值
 */
public class MultiThreadsError4 {
    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        Thread.sleep(105);
        if (point != null) {
            System.out.println(point);
        }
    }
}

class Point {
    private final int x, y;

    public Point (int x, int y) {
        this.x = x;
        MultiThreadsError4.point = this;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}

class PointMaker extends Thread {
    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
