package com.xc.test.immutable;

public class FinalStringDemo1 {
    public static void main(String[] args) {
        //        String a = "wukong2";
//        final String b = "wukong";
//        String d = "wukong";
//        String c = b + 2;
//        String e = d + 2;
//        System.out.println(a);
//        System.out.println(e);
//        System.out.println((a == c));
//        System.out.println((a == e));

        String ff = "aaa";
        String dd = "aa";
        String cc = "a";
        String hh = dd + cc;
        System.out.println((ff == hh));
        System.out.println((ff.equals(hh)));
    }
}
