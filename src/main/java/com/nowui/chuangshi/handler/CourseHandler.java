package com.nowui.chuangshi.handler;

import com.alibaba.fastjson.JSONObject;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApply;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApplyHistory;
import com.nowui.chuangshi.api.xietong.service.XietongCourseService;
import com.nowui.chuangshi.common.annotation.Handler;
import com.nowui.chuangshi.rocket.RocketHandler;

@Handler(tag = "course", thread_max = 20, thread_min = 10)
public class CourseHandler implements RocketHandler {

    @Override
    public void handle(String message) {
        System.out.println("--------------接收选课消息成功--------------");
        JSONObject jsonObject = JSONObject.parseObject(message);
        
        String course_id = jsonObject.getString(XietongCourseApply.COURSE_ID);
        String course_apply_history_id = jsonObject.getString(XietongCourseApplyHistory.COURSE_APPLY_HISTORY_ID);
        String user_id = jsonObject.getString(XietongCourseApply.USER_ID);
        String app_id = jsonObject.getString(XietongCourseApply.APP_ID);

        System.out.println(message);

        try {
            XietongCourseService.instance.applySave(course_id, course_apply_history_id, user_id, app_id);
            System.out.println("--------------处理申请选课成功--------------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
