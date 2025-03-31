package org.taskfly.core.plugin.impl;
import org.taskfly.core.plugin.Plugin;

public class MyPlugin implements Plugin {
    @Override
    public void initialize() {
        System.out.println("Initializing MyPlugin");
    }

    @Override
    public void execute() {
        System.out.println("Executing MyPlugin");
    }

    @Override
    public String getName() {
        return "MyPlugin";
    }
}
