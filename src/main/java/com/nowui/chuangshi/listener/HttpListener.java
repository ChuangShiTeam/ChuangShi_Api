package com.nowui.chuangshi.listener;

//import com.alibaba.fastjson.JSONObject;
//import com.nowui.chuangshi.model.Http;
//import com.nowui.chuangshi.service.HttpService;
//import com.nowui.chuangshi.util.DateUtil;
//import com.nowui.chuangshi.util.JSONObjectEvent;
//import com.nowui.chuangshi.util.ValidateUtil;

//import net.dreamlu.event.EventKit;
//import net.dreamlu.event.core.ApplicationListener;
//import net.dreamlu.event.core.Listener;
//
//@Listener(order = 1, tag = "http", enableAsync = true)
//public class HttpListener implements ApplicationListener<JSONObjectEvent> {
//
//    private final HttpService httpService = new HttpService();
//
//    @Override
//    public void onApplicationEvent(JSONObjectEvent event) {
//
//        System.out.println(Thread.currentThread().getName() + "\tsource: http");
//
//        try {
//            JSONObject jsonObject = (JSONObject) event.getSource();
//
//            Http http = JSONObject.parseObject(jsonObject.toJSONString(), Http.class);
//
//            String app_id = http.getApp_id();
//
//            if (ValidateUtil.isNullOrEmpty(app_id)) {
//                app_id = "";
//            }
//
//            System.out.println(
//                    "----------------------------------------------------------------------------------------------------------------");
//            System.out.println("url: " + http.getHttp_url());
//            System.out.println("time: " + DateUtil.getDateTimeString(http.getSystem_create_time()));
//            System.out.println("request: " + http.getHttp_request());
//            System.out.println("response: " + http.getHttp_response());
//            System.out.println(
//                    "----------------------------------------------------------------------------------------------------------------");
//
//            httpService.save(http.getHttp_id(), app_id, http.getHttp_url(), http.getHttp_code(), http.getHttp_request(),
//                    http.getHttp_response(), http.getHttp_token(), http.getHttp_platform(), http.getHttp_version(),
//                    http.getHttp_ip_address(), http.getHttp_run_time(), http.getSystem_create_user_id());
//        } catch (Exception e) {
//            JSONObject jsonObject = (JSONObject) event.getSource();
//
//            com.nowui.chuangshi.model.Exception exception = JSONObject.parseObject(jsonObject.toJSONString(),
//                    com.nowui.chuangshi.model.Exception.class);
//
//            jsonObject.put(com.nowui.chuangshi.model.Exception.APP_ID, exception.getApp_id());
//            jsonObject.put(com.nowui.chuangshi.model.Exception.HTTP_ID, exception.getHttp_id());
//            jsonObject.put(com.nowui.chuangshi.model.Exception.EXCEPTION_CONTENT, e.toString());
//            jsonObject.put(com.nowui.chuangshi.model.Exception.SYSTEM_CREATE_USER_ID,
//                    exception.getSystem_create_user_id());
//
//            EventKit.postRemote("exception", new JSONObjectEvent(jsonObject));
//
//            e.printStackTrace();
//        }
//
//    }
//
//}
