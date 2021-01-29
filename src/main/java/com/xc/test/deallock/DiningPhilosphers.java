package com.xc.test.deallock;

import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;

/**
 * 厌世哲学家问题导致的死锁
 */
public class DiningPhilosphers {
    public static class Philosopher implements Runnable {
        private Object leftChopstick;
        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("Thinking");
                    synchronized (leftChopstick) {
                        doAction("Picked up left chopstice");
                        synchronized (rightChopstick) {
                            doAction("Picked up right chopstice -eating");
                            doAction("Put down right chopstick");
                        }
                        doAction("Put down left chopstick");
                    }
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + "" + action);
            Thread.sleep((long) (Math.random()*10));
        }

        public static void main(String[] args) {
            Philosopher[] philosophers = new Philosopher[5];
            Object[] chopsticks = new Object[philosophers.length];
            for (int i = 0; i < chopsticks.length; i++) {
                chopsticks[i] = new Object();
            }
            for (int i = 0; i < philosophers.length; i++) {
                Object leftChopstick = chopsticks[i];
                Object rightChopstick = chopsticks[(i+1)%chopsticks.length];
                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
                new Thread(philosophers[i], "哲学家" + (i+1) + "号").start();
            }
        }
    }
}
