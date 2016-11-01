package com.zqykj.proxy;

import java.lang.reflect.Method;

public class ObjectProxy {
    private Object m;

    public ObjectProxy(Object m) {
        super();
        this.m = m;
    }

    public Object invoke(Method method, Object[] args) throws Exception {
        System.out.println("begin logging...");
        Object o = method.invoke(m, args);
        System.out.println("end logging...");
        return o;
    }

}
