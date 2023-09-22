package com.tms.multithreding;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("Main начал работу");
//        FirstClassThread f = new FirstClassThread();
//        f.start();
//
//        Thread t = new Thread(new SecondClassThread());
//        t.start();
//        t.join();
//        System.out.println("Main закончил работу");

        Counter counter = new Counter();

        for (int i = 0; i < 5; i++){
            Thread t = new Thread(new SecondClassThread(counter));
            t.start();
        }
    }
}
