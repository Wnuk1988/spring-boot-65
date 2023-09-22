package com.tms.multithreding;

public class FirstClassThread extends Thread{
    @Override
    public void run() {
        System.out.println("Hello from new thread!!!");
    }
}
