package com.nowui.chuangshi.handler;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.jfinal.plugin.zbus.annotation.Handler;
//import com.jfinal.plugin.zbus.annotation.Topic;
//import com.jfinal.plugin.zbus.handler.TMsgHandler;
//import com.nowui.chuangshi.model.Sql;
//import com.nowui.chuangshi.service.SqlService;
//import com.nowui.chuangshi.util.MQUtil;
//import com.nowui.chuangshi.util.Util;
//
//import java.util.HashMap;
//import java.util.Map;
//
////@Topic(mq = "MQ", topic = "sql")
////@Handler
//public class SqlHandler extends TMsgHandler<String> {
//
//    private final SqlService sqlService = new SqlService();
//
//    @Override
//    public void handle(String json) {
//        try {
//            JSONObject jsonObject = JSONObject.parseObject(json);
//            String app_id = jsonObject.getString(Sql.APP_ID);
//            String http_id = jsonObject.getString(Sql.HTTP_ID);
//            String sql_table = jsonObject.getString(Sql.SQL_TABLE);
//            String sql_action = jsonObject.getString(Sql.SQL_ACTION);
//            String sql_content = jsonObject.getString(Sql.SQL_CONTENT);
//            String system_create_user_id = jsonObject.getString(Sql.SYSTEM_CREATE_USER_ID);
//
//            System.out.println(sql_content);
//
//            //sqlService.save(Util.getRandomUUID(), app_id, http_id, sql_table, sql_action, sql_content, system_create_user_id);
//        } catch (Exception e) {
//            JSONObject jsonObject = JSONObject.parseObject(json);
//
//            Map<String, Object> exceptionMap = new HashMap<String, Object>();
//            exceptionMap.put(com.nowui.chuangshi.model.Exception.APP_ID, jsonObject.getString(com.nowui.chuangshi.model.Exception.APP_ID));
//            exceptionMap.put(com.nowui.chuangshi.model.Exception.HTTP_ID, jsonObject.getString(com.nowui.chuangshi.model.Exception.HTTP_ID));
//            exceptionMap.put(com.nowui.chuangshi.model.Exception.EXCEPTION_CONTENT, e.toString());
//            exceptionMap.put(com.nowui.chuangshi.model.Exception.SYSTEM_CREATE_USER_ID, jsonObject.getString(com.nowui.chuangshi.model.Exception.SYSTEM_CREATE_USER_ID));
//            MQUtil.sendSync("exception", JSON.toJSONString(exceptionMap));
//
//            e.printStackTrace();
//        }
//    }
//
//}