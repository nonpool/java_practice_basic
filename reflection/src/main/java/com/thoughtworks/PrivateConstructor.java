package com.thoughtworks;

public class PrivateConstructor {
    public PrivateConstructor() {
    }

    public PrivateConstructor(String name) {
        this.name = name;
    }

    private String name;
}
