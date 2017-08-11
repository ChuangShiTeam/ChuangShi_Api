package com.nowui.chuangshi.api.school.service;

import com.nowui.chuangshi.api.school.cache.CourseConfigCache;
import com.nowui.chuangshi.common.service.Service;

public class CourseConfigService extends Service {

    public static final CourseConfigService me = new CourseConfigService();

    public CourseConfigService() {
        setCache(new CourseConfigCache());
    }

}