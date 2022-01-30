package com.cqnu.dlzf.abstractclass;

import java.util.ArrayList;
import java.util.List;

public class TestAbstract {

    public static void main(String[] args) {
        B b = new B();
        b.nums.forEach(System.out::println);

        C c = new C();
        c.nums.forEach(System.out::println);
    }
}

abstract class A{
    protected List<Integer> nums = new ArrayList<>();
}

class B extends A{
    public B(){
        nums.add(1);
    }
}

class C extends A{
    public C(){
        nums.add(2);
    }
}
