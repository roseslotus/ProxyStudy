package com.lotus.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ManInvoketion implements InvocationHandler {


    // 目标对象
    private Object target;

    /**
     * 构造方法
     * @param target 目标对象
     */
    public ManInvoketion(Object target) {
        super();
        this.target = target;
    }



    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.out.println("ManProxy Man sayHi");
        Object result = method.invoke(target,args);
        return result;
    }
}
