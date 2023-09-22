package com.tms.multithreding;

public class SecondClassThread implements Runnable{
    public Counter counter;

    public SecondClassThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
       synchronized (counter) {
           counter.setX(0);
           System.out.println("Method Run start working ...");
           for (int i = 0; i < 5; i++) {
               System.out.println(counter.getX());
               counter.increase();
           }
       }
    }
}
