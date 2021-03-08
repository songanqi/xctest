package com.xc.test.immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * 一个属性对象,但是整体不可变,其他类无法修改Set里面的数据
 */
public class ImmutableDemo {
    private final Set<String> students = new HashSet<>();

    public ImmutableDemo () {
        students.add("李晓梅");
        students.add("王庄");
        students.add("徐福记");
    }

    public boolean isStudent(String name) {
        return students.contains(name);
    }
}
