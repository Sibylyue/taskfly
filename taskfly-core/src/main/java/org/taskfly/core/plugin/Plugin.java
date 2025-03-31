package org.taskfly.core.plugin;

public interface Plugin {
    void initialize();
    void execute();
    String getName();
}
