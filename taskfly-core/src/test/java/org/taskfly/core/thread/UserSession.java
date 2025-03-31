package org.taskfly.core.thread;

import lombok.Data;

@Data
public class UserSession {
    String name;
    int id;
    public UserSession(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
