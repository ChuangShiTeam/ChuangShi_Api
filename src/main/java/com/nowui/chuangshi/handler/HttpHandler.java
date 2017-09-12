package com.nowui.chuangshi.handler;

import com.alibaba.fastjson.JSONObject;
import com.nowui.chuangshi.api.http.model.Http;
import com.nowui.chuangshi.api.http.service.HttpService;
import com.nowui.chuangshi.common.annotation.Handler;
import com.nowui.chuangshi.rocket.RocketHandler;
import com.nowui.chuangshi.util.DateUtil;

@Handler(tag = "http")
public class HttpHandler implements RocketHandler {

    @Override
    public void handle(String message) {
        Http http = JSONObject.parseObject(message, Http.class);

        try {
            String app_id = http.getApp_id();

            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.println("url: " + http.getHttp_url());
            System.out.println("time: " + DateUtil.getDateTimeString(http.getSystem_create_time()));
            System.out.println("app_id: " + app_id);
            System.out.println("user_id: " + http.getSystem_create_user_id());
            System.out.println("http_token: " + http.getHttp_token());
            System.out.println("request: " + http.getHttp_request());
            System.out.println("response: " + http.getHttp_response());
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            HttpService.instance.save(http);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}