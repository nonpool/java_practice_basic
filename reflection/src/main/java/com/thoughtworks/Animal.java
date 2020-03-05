package com.thoughtworks;

public abstract class Animal implements Walkable {
    private String name;

    protected void run() {
        System.out.println("running");
    }
}
