package com.zqykj.proxy;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class MyProxy {

    public static Object newProxyInstance(Class intf, MyInvocationHandler handler) throws Exception {
        String r = "\r\n";
        Method[] methods = intf.getMethods();

        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < methods.length; i++) {
            Method m = methods[i];
            Class<?>[] paramTypes = m.getParameterTypes();
            Class<?> returnType = m.getReturnType();
            String param = "";
            String hp = "";
            String pTypes = "";
            int index = 0;
            for (Class p : paramTypes) {
                hp += "p" + index + ",";
                pTypes += p.getName() + ".class,";
                param += p.getName() + " p" + index;
                index++;
            }
            hp = hp.substring(0, hp.length() - 1);
            pTypes = pTypes.substring(0, pTypes.length() - 1);
            sb.append(" public " + returnType.getName() + " " + m.getName() + "(" + param + ") {" + r + "       try{ "
                    + r + "       Method md = " + intf.getName() + ".class.getMethod(\"" + m.getName() + "\"," + pTypes
                    + ");" + r + "       Object o = handler.invoke(this,md,new Object[]{" + hp + "});" + r
                    + "       return (" + returnType.getName() + ")o;" + r + "       }catch(Throwable e){" + r
                    + "       e.printStackTrace();" + r + "       }" + r + "       return null;" + r + "   }" + r + r);
        }

        // 生成完整的类代码
        String src = "import java.lang.reflect.*;" + r + r + "public class Proxy$1 implements " + intf.getName() + "{"
                + r + "   private " + handler.getClass().getName() + " handler;" + r + "   public Proxy$1("
                + handler.getClass().getName() + " handler){" + r + "       this.handler = handler;" + r + "   }" + r
                + r + sb.toString() + "}" + r;
        // 输出Java文件
        String dir = "/home/yshi/";
        FileWriter writer = new FileWriter(new File(dir + "Proxy$1.java"));
        writer.write(src);
        writer.flush();
        writer.close();
        // 编译动态代理类
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> units = fileMgr.getJavaFileObjects(dir + "Proxy$1.java");
        CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
        t.call();
        fileMgr.close();
        // 加载动态代理类，并返回动态代理类的实例
        URL[] urls = new URL[] { new URL("file:" + dir) };
        URLClassLoader loader = new URLClassLoader(urls);
        Class c = loader.loadClass("Proxy$1");
        Constructor ctr = c.getConstructor(handler.getClass());
        return ctr.newInstance(handler);
    }
}
