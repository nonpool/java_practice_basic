package com.thoughtworks;

public class Parrot extends Animal {

    public Parrot() {
    }

    public Parrot(boolean canTalk) {
        this.canTalk = canTalk;
    }

    @Limit(min = 1, max = 140)
    private int flySpeed;

    private boolean canTalk;

    @Override
    public void walk() {
        System.out.println("Cuckoo is walking");
    }

    private void privateMethod(){
        System.out.println("private method in parrot");
    }

    public static void staticMethod(){
        System.out.println("static method in parrot");
    }

    @Override
    public String toString() {
        return "Parrot{" +
                "flySpeed=" + flySpeed +
                ", canTalk=" + canTalk +
                '}';
    }
}
