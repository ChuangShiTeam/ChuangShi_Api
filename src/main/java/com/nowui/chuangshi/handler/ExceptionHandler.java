package com.nowui.chuangshi.handler;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.zbus.annotation.Handler;
import com.jfinal.plugin.zbus.annotation.Topic;
import com.jfinal.plugin.zbus.handler.TMsgHandler;
import com.nowui.chuangshi.model.Exception;
import com.nowui.chuangshi.service.ExceptionService;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

//@Topic(mq = "MQ", topic = "exception")
//@Handler
public class ExceptionHandler extends TMsgHandler<String> {

    private final ExceptionService exceptionService = new ExceptionService();

    @Override
    public void handle(String json) {
        Exception exception = JSONObject.parseObject(json, Exception.class);

        String app_id = exception.getApp_id();

        if (ValidateUtil.isNullOrEmpty(app_id)) {
            app_id = "";
        }

        Boolean exception_is_confirm = false;
        String exception_remark = "";

        exceptionService.save(Util.getRandomUUID(), app_id, exception.getHttp_id(), exception.getException_content(), exception_is_confirm, exception_remark, exception.getSystem_create_user_id());
    }

}