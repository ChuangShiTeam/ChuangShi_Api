package com.nowui.chuangshi.rocket;

import com.jfinal.plugin.IPlugin;

public class RocketPlugin implements IPlugin {

    public boolean start() {
        Consumer.instance.start();
        Producer.instance.start();
        return true;
    }

    public boolean stop() {
        Consumer.instance.stop();
        Producer.instance.stop();
        return true;
    }
}
