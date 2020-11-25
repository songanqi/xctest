package com.xc.test.stopthreads;

/**
 * 最佳实践: catch了InterruptedExcpetion
 */
public class RightWayStopThreadProd implements Runnable {
    @Override
    public void run () {
        while(true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                // 保存日志,停止程序
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

//    private void throwInMethod() {
        // 错误示范
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
