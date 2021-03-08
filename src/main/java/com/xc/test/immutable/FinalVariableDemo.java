package com.xc.test.immutable;

/**
 * 演示final变量
 */
public class FinalVariableDemo {
    private  final int a;

//    public FinalVariableDemo(int a) {
//        this.a = a;
//    }
    {
        a=7;
    }

    private static final int b;

    static {
        b= 7;
    }
}
