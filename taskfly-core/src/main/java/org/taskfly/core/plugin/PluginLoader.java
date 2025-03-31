package org.taskfly.core.plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

//插件加载器
public class PluginLoader {
    public static void loadPlugins() {
        ServiceLoader<Plugin> plugins = ServiceLoader.load(Plugin.class);
        for (Plugin plugin : plugins) {
            plugin.initialize();
        }
    }
}
