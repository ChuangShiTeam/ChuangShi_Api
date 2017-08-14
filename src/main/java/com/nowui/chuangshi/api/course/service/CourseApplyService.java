package com.nowui.chuangshi.api.course.service;

import com.nowui.chuangshi.api.course.cache.CourseApplyCache;
import com.nowui.chuangshi.common.service.Service;

public class CourseApplyService extends Service {

    public static final CourseApplyService me = new CourseApplyService();

    public CourseApplyService() {
        setCache(new CourseApplyCache());
    }

}