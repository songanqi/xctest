package com.xc.test.immutable;

/**
 * final的方法不能被重写
 */
public class FinalMethodDemo {
    public void drink() {

    }

    public final void eat() {

    }

    public static void sleep() {

    }
}


class SubClass extends FinalMethodDemo {
    @Override
    public void drink() {
        super.drink();
        eat();
    }

//    @Override
//    public void eat() {
//        super.drink();
//    }
}
