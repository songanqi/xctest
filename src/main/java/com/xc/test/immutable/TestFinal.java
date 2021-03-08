package com.xc.test.immutable;

/**
 * 测试final能否被修改
 */
public class TestFinal {


    public static void main(String[] args) {
        Person person = new Person();
//        person.age = 19;
//        person.name = "Alice";
        int age = person.age;
    }
}
