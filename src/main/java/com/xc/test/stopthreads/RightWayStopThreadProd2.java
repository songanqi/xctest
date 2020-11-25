package com.xc.test.stopthreads;

/**
 * 最佳实践2: 在catch语句中调用Thread.currentThread().interrupt()来回=恢复设置中断状态,以便在后续的执行中,依然能够检测到刚才发生了中断
 */
public class RightWayStopThreadProd2 implements Runnable {
    @Override
    public void run () {
        while(true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted, 程序运行结束");
                break;
            }
            System.out.println("go");
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
