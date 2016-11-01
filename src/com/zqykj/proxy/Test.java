package com.zqykj.proxy;

public class Test {

    public static void main(String[] args) throws Exception {
        // 1\静态代理
        // TankProxy tp = new TankProxy(new Tank());
        // tp.move("山东");
        // Movable m = new Tank();
        // MovableProxy mp = new MovableProxy(m);
        // mp.move("南京");
        // Movable m = new Tank();
        // ObjectProxy op = new ObjectProxy(m);
        // op.invoke(m.getClass().getMethod("move", String.class), new Object[]
        // { "索马里" });
        // 2\ jdk动态代理
        /*
         * Class[] clazz = new Class[] { Movable.class }; InvocationHandler h =
         * new LogInvocationHandler(new Tank()); Movable o = (Movable)
         * Proxy.newProxyInstance(Test.class.getClassLoader(), clazz, h);
         * System.out.println(o.getClass().getName()); o.move("东北");
         */
        // 3\ 自己实现动态代理
        Movable m = new Tank();
        MyInvocationHandler handler = new MyLogInvocationHandler(m);
        Movable o = (Movable) MyProxy.newProxyInstance(Movable.class, handler);
        String str = o.move("陕北");
        System.out.println(">>>>" + str);
    }

}
