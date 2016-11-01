package com.zqykj.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogInvocationHandler implements InvocationHandler {

    private Object m;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin logging...");
        method.invoke(m, args);
        System.out.println("end logging...");
        return null;
    }

    public LogInvocationHandler(Object m) {
        super();
        this.m = m;
    }

}
