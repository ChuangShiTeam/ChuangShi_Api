package com.nowui.chuangshi.util;

import com.alibaba.fastjson.JSONObject;

import net.dreamlu.event.core.ApplicationEvent;

public class JSONObjectEvent extends ApplicationEvent {

    private static final long serialVersionUID = 6994987952247306131L;

    public JSONObjectEvent(JSONObject source) {
        super(source);
    }

}