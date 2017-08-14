package com.nowui.chuangshi.api.course.service;

import com.nowui.chuangshi.api.course.cache.CourseStudentCache;
import com.nowui.chuangshi.common.service.Service;

public class CourseStudentService extends Service {

    public static final CourseStudentService me = new CourseStudentService();

    public CourseStudentService() {
        setCache(new CourseStudentCache());
    }

}