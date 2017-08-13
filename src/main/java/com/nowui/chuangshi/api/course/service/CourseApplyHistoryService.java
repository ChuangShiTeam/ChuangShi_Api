package com.nowui.chuangshi.api.course.service;

import com.nowui.chuangshi.api.course.cache.CourseApplyHistoryCache;
import com.nowui.chuangshi.common.service.Service;

public class CourseApplyHistoryService extends Service {

    public static final CourseApplyHistoryService me = new CourseApplyHistoryService();

    public CourseApplyHistoryService() {
        setCache(new CourseApplyHistoryCache());
    }

}