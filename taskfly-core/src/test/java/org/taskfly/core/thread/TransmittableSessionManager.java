package org.taskfly.core.thread;

import com.alibaba.ttl.TransmittableThreadLocal;

public class TransmittableSessionManager {
    private static final TransmittableThreadLocal<UserSession> holder = new TransmittableThreadLocal<>();

    public static void set(UserSession userSession) {
        holder.set(userSession);
    }
    public static UserSession get() {
        return holder.get();
    }
    public static void remove() {
        holder.remove();
    }
    public static void putUserSessionName(String name) {
        holder.get().setName(name);
    }

    public static int getUserSessionId() {
        return holder.get().getId();
    }
}
