package com.proxy;


import com.proxy.service.UserService;
import com.proxy.service.UserServiceImpl;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        testProxy();
    }


    private static void testProxy() {
        // 设置这个值，可以把生成的代理类，输出出来。
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

//        IPackageManager pkgManger = new PackageManagerImpl();
//        PackageManagerWorker worker = new PackageManagerWorker(pkgManger);
//        IPackageManager pm = (IPackageManager) Proxy.newProxyInstance(pkgManger.getClass().getClassLoader(), pkgManger
//                .getClass().getInterfaces(), worker);
        //  System.out.println("pm.getName:" +pm.getClass().getName())


        UserService service = new UserServiceImpl();

        //生成被代理类的接口的子类
        UserService proxyObj = (UserService) Proxy.newProxyInstance(LogProxy.class.getClassLoader(), service.getClass().getInterfaces(),
                new LogProxy(service));

        proxyObj.addUser("abel");
        proxyObj.remove("name");
    }

}