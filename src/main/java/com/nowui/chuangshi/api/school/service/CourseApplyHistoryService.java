package com.nowui.chuangshi.api.school.service;

import com.nowui.chuangshi.api.school.cache.CourseApplyHistoryCache;
import com.nowui.chuangshi.common.service.Service;

public class CourseApplyHistoryService extends Service {

    public static final CourseApplyHistoryService me = new CourseApplyHistoryService();

    public CourseApplyHistoryService() {
        setCache(new CourseApplyHistoryCache());
    }

}