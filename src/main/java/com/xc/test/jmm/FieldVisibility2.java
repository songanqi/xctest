package com.xc.test.jmm;

/**
 * 演示可见性带来的问题  synchronized可避免
 */
public class FieldVisibility2 {
     int a = 1;
     int b = 2;
     int c = 2;
     int d = 2;

     private void change() {
         a = 3;
         b = 4;
         c = 5;
         synchronized (this) {
             d = 6;
         }
     }

    private void print() {
         synchronized (this) {
             int aa = a;
         }
         int bb = b;
         int cc = c;
         int dd = d;
        System.out.println("b = " + b + ", a = " + a);
    }

    public static void main(String[] args) {
         while (true) {
             FieldVisibility2 test = new FieldVisibility2();
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         Thread.sleep(1);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     test.change();
                 }
             }).start();

             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         Thread.sleep(1);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     test.print();
                 }
             }).start();

             
         }
    }




}
