package com.company;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        Main.someMethod();
        Class mainClass = Main.class;
    }

    private synchronized static void someMethod(){
        //some code
    }

    private void anotherMethod(){
        synchronized (this) {
            //some code
        }
    }
}
