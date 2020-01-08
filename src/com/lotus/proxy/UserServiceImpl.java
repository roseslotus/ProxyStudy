package com.lotus.proxy;

public class UserServiceImpl implements UserService {

    @Override
    public String getName(String userId) {
        // 1 将out 静态变量放到当前线程执行栈顶
        // 2 "业务方法执行 " 放到执行栈顶
        // 3 执行println 方法指令
        System.out.println("业务方法执行 ");
        return "hell0"+userId;
    }

    public String createUser(String name,int id) {
        System.out.println("name ");
        return name+id;
    }
}
