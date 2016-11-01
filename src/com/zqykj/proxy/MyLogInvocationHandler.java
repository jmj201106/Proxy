package com.zqykj.proxy;

import java.lang.reflect.Method;

public class MyLogInvocationHandler implements MyInvocationHandler {

    private Object m;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin logging...");
        Object s = method.invoke(m, args);
        System.out.println("end logging...");
        return s;
    }

    public MyLogInvocationHandler(Object m) {
        super();
        this.m = m;
    }

}
