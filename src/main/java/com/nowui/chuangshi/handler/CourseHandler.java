package com.nowui.chuangshi.handler;

import com.alibaba.fastjson.JSONObject;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApply;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApplyHistory;
import com.nowui.chuangshi.api.xietong.service.XietongCourseService;
import com.nowui.chuangshi.common.annotation.Handler;
import com.nowui.chuangshi.rocket.RocketHandler;

@Handler(tag = "course", thread_max = 5, thread_min = 3)
public class CourseHandler implements RocketHandler {

    @Override
    public void handle(String message) {
        
        JSONObject jsonObject = JSONObject.parseObject(message);
        
        String course_id = jsonObject.getString(XietongCourseApply.COURSE_ID);
        String course_apply_history_id = jsonObject.getString(XietongCourseApplyHistory.COURSE_APPLY_HISTORY_ID);
        String user_id = jsonObject.getString(XietongCourseApply.USER_ID);
        String app_id = jsonObject.getString(XietongCourseApply.APP_ID);

        try {
            XietongCourseService.instance.applySave(course_id, course_apply_history_id, user_id, app_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
