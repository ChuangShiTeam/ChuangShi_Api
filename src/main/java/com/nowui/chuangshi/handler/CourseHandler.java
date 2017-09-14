package com.nowui.chuangshi.handler;

import com.nowui.chuangshi.common.annotation.Handler;
import com.nowui.chuangshi.rocket.RocketHandler;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Handler(tag = "course", thread_max = 5, thread_min = 3)
public class CourseHandler implements RocketHandler {

    @Override
    public void handle(String message) {

        Integer count = CacheUtil.get("course", "aaa");
        if (count == null) {
            count = 0;
        }
        CacheUtil.put("course", "aaa", count + 1);
        System.out.println(message + "---" + count);

        try {
            //模拟业务逻辑处理中...
            Random random = new Random();
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
