package com.lotus.proxy;

import javassist.*;
import sun.misc.IOUtils;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;

public class LubanProxyServer {
    public static void main(String[] args) throws IOException {
//        UserServiceImpl target = new UserServiceImpl();
////        UserService userService = (UserService) Proxy.newProxyInstance(LubanProxyServer.class.getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
////            @Override
////            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
////                System.out.println("代理前置逻辑"); // 三行指令码
////                try {
////                    return method.invoke(target, args);
////                }finally {
////                    System.out.println("代理后置逻辑"); // 三行指令码
////                }
////            }
////        });
////
////        // 新增的proxy class里面的包含的逻辑？
////        userService.getClass();
////        String result = userService.getName("111");
//////        System.out.println("execute result = " + result);
////
////        //
////        byte[] bytes = ProxyGenerator.generateProxyClass("UserService$Proxy",new Class[]{ UserService.class});
////        Files.write(new File("D:\\workbench\\ProxyStudy\\out\\UserService$Proxy.class").toPath(),bytes);


        try {
            byte[] bytes = build();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
        new UserServiceImpl().getName("111");
    }

    // javassist
    public static byte[] build() throws IOException, NotFoundException, CannotCompileException {
//        String path = "/" + "com.lotus.proxy.UserServiceImpl".replaceAll("[.]","/")+".class";
//        System.out.println(path);
//        InputStream stream = AsmUtil.class.getResourceAsStream(path);
//        byte[] bytes = IOUtils.readFully(stream,-1,false);
        ClassPool pool = new ClassPool();
        pool.appendSystemPath();
        CtClass ctClass = pool.getCtClass("com.lotus.proxy.UserServiceImpl");
        CtMethod method = ctClass.getDeclaredMethod("getName");
        method.insertBefore("System.out.println(System.currentTimeMillis());");
        ctClass.toClass();
        return ctClass.toBytecode();
    }
}
