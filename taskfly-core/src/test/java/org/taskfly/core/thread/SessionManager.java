package org.taskfly.core.thread;

import com.alibaba.ttl.TransmittableThreadLocal;

public class SessionManager {
    /**
     * 定义threadLocal变量
     * final变量：初始化一次，不能在更改，
     * 为引用变量时引用对象地址不被修改但其属性元素可被修改；
     */
    private static final ThreadLocal<UserSession> threadLocal = new ThreadLocal<>();


    public static void set(UserSession userSession) {
        threadLocal.set(userSession);
    }

    public static UserSession get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
    public static void print(){

    }
}