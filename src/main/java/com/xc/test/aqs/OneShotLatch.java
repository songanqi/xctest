package com.xc.test.aqs;




import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自己用aqs实现一个简单地线程协作器
 */
public class OneShotLatch {
    private final Sync sync = new Sync();
    public void signal() {
        sync.releaseShared(0);
    }
    public void await() {
        sync.acquireShared(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }
    }
}
