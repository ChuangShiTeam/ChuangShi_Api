package com.nowui.chuangshi.handler;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.jfinal.plugin.zbus.annotation.Handler;
//import com.jfinal.plugin.zbus.annotation.Topic;
//import com.jfinal.plugin.zbus.handler.TMsgHandler;
//import com.nowui.chuangshi.model.Http;
//import com.nowui.chuangshi.service.HttpService;
//import com.nowui.chuangshi.util.DateUtil;
//import com.nowui.chuangshi.util.MQUtil;
//import com.nowui.chuangshi.util.ValidateUtil;
//
//import java.util.HashMap;
//import java.util.Map;
//
////@Topic(mq = "MQ", topic = "http")
////@Handler
//public class HttpHandler extends TMsgHandler<String> {
//
//    private final HttpService httpService = new HttpService();
//
//    @Override
//    public void handle(String json) {
//        try {
//            Http http = JSONObject.parseObject(json, Http.class);
//
//            String app_id = http.getApp_id();
//
//            if (ValidateUtil.isNullOrEmpty(app_id)) {
//                app_id = "";
//            }
//
//            System.out.println("----------------------------------------------------------------------------------------------------------------");
//            System.out.println("url: " + http.getHttp_url());
//            System.out.println("time: " + DateUtil.getDateTimeString(http.getSystem_create_time()));
//            System.out.println("request: " + http.getHttp_request());
//            System.out.println("response: " + http.getHttp_response());
//            System.out.println("----------------------------------------------------------------------------------------------------------------");
//
//            httpService.save(http.getHttp_id(), app_id, http.getHttp_url(), http.getHttp_code(), http.getHttp_request(), http.getHttp_response(), http.getHttp_token(), http.getHttp_platform(), http.getHttp_version(), http.getHttp_ip_address(), http.getHttp_run_time(), http.getSystem_create_user_id());
//        } catch (Exception e) {
//            com.nowui.chuangshi.model.Exception exception = JSONObject.parseObject(json, com.nowui.chuangshi.model.Exception.class);
//
//            Map<String, Object> exceptionMap = new HashMap<String, Object>();
//            exceptionMap.put(com.nowui.chuangshi.model.Exception.APP_ID, exception.getApp_id());
//            exceptionMap.put(com.nowui.chuangshi.model.Exception.HTTP_ID, exception.getHttp_id());
//            exceptionMap.put(com.nowui.chuangshi.model.Exception.EXCEPTION_CONTENT, e.toString());
//            exceptionMap.put(com.nowui.chuangshi.model.Exception.SYSTEM_CREATE_USER_ID, exception.getSystem_create_user_id());
//            MQUtil.sendSync("exception", JSON.toJSONString(exceptionMap));
//
//            e.printStackTrace();
//        }
//    }
//
//}