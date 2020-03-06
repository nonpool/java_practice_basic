package com.thoughtworks;

public class A {
    @Limit(min = 1,max = 100)
    private int length;

    public A(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "A{" +
                "length=" + length +
                '}';
    }
}
