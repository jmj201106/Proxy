package com.zqykj.proxy;

public class TankProxy implements Movable {
    private Tank tank;

    public TankProxy(Tank tank) {
        super();
        this.tank = tank;
    }

    @Override
    public String move(String addr) {
        System.out.println("begin logging...");
        String str = tank.move(addr);
        System.out.println("end logging...");
        return str;
    }

}
