package com.xc.test.future;

/**
 * 在run方法中无法抛出checked Exception
 */
public class RunnableCantThrowsException {
    public static void main(String[] args) {
        Runnable runnable = () -> {
//            throw new Exception();
        };
    }
}
