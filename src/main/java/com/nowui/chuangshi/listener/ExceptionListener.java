package com.nowui.chuangshi.listener;

import com.alibaba.fastjson.JSONObject;
import com.nowui.chuangshi.model.Exception;
import com.nowui.chuangshi.service.ExceptionService;
import com.nowui.chuangshi.util.JSONObjectEvent;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import net.dreamlu.event.core.ApplicationListener;
import net.dreamlu.event.core.Listener;

@Listener(order = 1, tag = "exception", enableAsync = true)
public class ExceptionListener implements ApplicationListener<JSONObjectEvent> {

    private final ExceptionService exceptionService = new ExceptionService();

    @Override
    public void onApplicationEvent(JSONObjectEvent event) {

        System.out.println(Thread.currentThread().getName() + "\tsource: exception");

        JSONObject jsonObject = (JSONObject) event.getSource();

        Exception exception = JSONObject.parseObject(jsonObject.toJSONString(), Exception.class);

        String app_id = exception.getApp_id();

        if (ValidateUtil.isNullOrEmpty(app_id)) {
            app_id = "";
        }

        Boolean exception_is_confirm = false;
        String exception_remark = "";

        exceptionService.save(Util.getRandomUUID(), app_id, exception.getHttp_id(), exception.getException_content(),
                exception_is_confirm, exception_remark, exception.getSystem_create_user_id());
    }

}
