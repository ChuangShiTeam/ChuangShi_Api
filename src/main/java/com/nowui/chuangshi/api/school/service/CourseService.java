package com.nowui.chuangshi.api.school.service;

import com.nowui.chuangshi.api.school.cache.CourseCache;
import com.nowui.chuangshi.common.service.Service;

public class CourseService extends Service {

    public static final CourseService me = new CourseService();

    public CourseService() {
        setCache(new CourseCache());
    }

}