package com.zqykj.proxy;

import java.lang.reflect.Method;

public interface MyInvocationHandler {

    Object invoke(Object proxy, Method m, Object[] args) throws Throwable;
}
