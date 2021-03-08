package com.xc.test.immutable;

public class FinalStringDemo2 {
    public static void main(String[] args) {
        String a = "wukong2";
        final String b = getDaShiXiong();
        String c = b + 2;
        System.out.println(a == c);
    }

    private static String getDaShiXiong() {
        return "wukong";
    }
}
