package com.zqykj.proxy;

public class MovableProxy implements Movable {
    private Movable m;

    public MovableProxy(Movable m) {
        super();
        this.m = m;
    }

    @Override
    public String move(String addr) {
        System.out.println("begin logging...");
        String str = m.move(addr);
        System.out.println("end logging...");
        return str;
    }

}
