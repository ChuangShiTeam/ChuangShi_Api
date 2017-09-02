package com.nowui.chuangshi.listener;

//import java.util.HashMap;
//import java.util.Map;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.nowui.chuangshi.model.Sql;
//import com.nowui.chuangshi.service.SqlService;
//import com.nowui.chuangshi.util.JSONObjectEvent;
//import com.nowui.chuangshi.util.MQUtil;
//import com.nowui.chuangshi.util.Util;
//
//import net.dreamlu.event.core.ApplicationListener;
//import net.dreamlu.event.core.Listener;
//
//@Listener(order = 1, tag = "sql", enableAsync = true)
//public class SqlListener implements ApplicationListener<JSONObjectEvent> {
//
//    private final SqlService sqlService = new SqlService();
//
//    @Override
//    public void onApplicationEvent(JSONObjectEvent event) {
//        System.out.println(Thread.currentThread().getName() + "\tsource: sql");
//        try {
//            JSONObject jsonObject = (JSONObject) event.getSource();
//
//            String app_id = jsonObject.getString(Sql.APP_ID);
//            String http_id = jsonObject.getString(Sql.HTTP_ID);
//            String sql_table = jsonObject.getString(Sql.SQL_TABLE);
//            String sql_action = jsonObject.getString(Sql.SQL_ACTION);
//            String sql_content = jsonObject.getString(Sql.SQL_CONTENT);
//            String system_create_user_id = jsonObject.getString(Sql.SYSTEM_CREATE_USER_ID);
//
//            System.out.println(sql_content);
//
//            sqlService.save(Util.getRandomUUID(), app_id, http_id, sql_table, sql_action, sql_content,
//                    system_create_user_id);
//        } catch (Exception e) {
//            JSONObject jsonObject = (JSONObject) event.getSource();
//
//            Map<String, Object> exceptionMap = new HashMap<String, Object>();
//            exceptionMap.put(com.nowui.chuangshi.model.Exception.APP_ID,
//                    jsonObject.getString(com.nowui.chuangshi.model.Exception.APP_ID));
//            exceptionMap.put(com.nowui.chuangshi.model.Exception.HTTP_ID,
//                    jsonObject.getString(com.nowui.chuangshi.model.Exception.HTTP_ID));
//            exceptionMap.put(com.nowui.chuangshi.model.Exception.EXCEPTION_CONTENT, e.toString());
//            exceptionMap.put(com.nowui.chuangshi.model.Exception.SYSTEM_CREATE_USER_ID,
//                    jsonObject.getString(com.nowui.chuangshi.model.Exception.SYSTEM_CREATE_USER_ID));
//            MQUtil.sendSync("exception", JSON.toJSONString(exceptionMap));
//
//            e.printStackTrace();
//        }
//
//    }
//
//}
