package com.tms.multithreding;

public class Counter {

    private int x = 0;

    public void increase(){
        x++;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
