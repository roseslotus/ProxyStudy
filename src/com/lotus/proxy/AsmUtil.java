package com.lotus.proxy;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class AsmUtil {

    public static void main(String[] args) {
        String path = "/" + UserServiceImpl.class.getName().replaceAll("[.]","/")+".class";
        System.out.println(path);
        InputStream stream = AsmUtil.class.getResourceAsStream(path);
        ClassReader reader;
        try{
            // class 读取器
            reader = new ClassReader(stream);
            // 反编译成指令码 打印到控制台
            TraceClassVisitor visitor = new TraceClassVisitor(new PrintWriter(System.out));
            reader.accept(visitor,ClassReader.SKIP_FRAMES);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
