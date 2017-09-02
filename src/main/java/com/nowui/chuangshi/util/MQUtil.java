package com.nowui.chuangshi.util;

//import com.alibaba.fastjson.JSON;
//import com.jfinal.plugin.zbus.sender.Sender;
//import com.jfinal.plugin.zbus.sender.TopicSender;
//import com.nowui.chuangshi.model.Exception;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MQUtil {
//
//    public static void sendSync(String topic, String json) {
////        try {
////            Sender<String> topicSender = new TopicSender<String>("MQ", topic);
////            topicSender.sendSync(json);
////        } catch (IOException e) {
////            Map<String, Object> exceptionMap = new HashMap<String, Object>();
////            exceptionMap.put(Exception.EXCEPTION_CONTENT, e.toString());
////            sendSyncException(topic, JSON.toJSONString(exceptionMap));
////
////            e.printStackTrace();
////        } catch (InterruptedException e) {
////            Map<String, Object> exceptionMap = new HashMap<String, Object>();
////            exceptionMap.put(Exception.EXCEPTION_CONTENT, e.toString());
////            sendSyncException(topic, JSON.toJSONString(exceptionMap));
////
////            e.printStackTrace();
////        }
//    }
//
//    private static void sendSyncException(String topic, String json) {
////        if (topic.equals("exception")) {
////            return;
////        }
////
////        try {
////            Sender<String> topicSender = new TopicSender<String>("MQ", "exception");
////            topicSender.sendSync(json);
////        } catch (IOException e) {
////            e.printStackTrace();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//    }
//
//}