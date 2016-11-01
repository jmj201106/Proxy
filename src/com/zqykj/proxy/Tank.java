package com.zqykj.proxy;

public class Tank implements Movable {

    @Override
    public String move(String addr) {
        System.out.println("tank moving to " + addr);
        return addr;
    }

}
