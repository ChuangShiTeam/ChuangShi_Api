package com.nowui.chuangshi.api.school.service;

import com.nowui.chuangshi.api.school.cache.CourseStudentCache;
import com.nowui.chuangshi.common.service.Service;

public class CourseStudentService extends Service {

    public static final CourseStudentService me = new CourseStudentService();

    public CourseStudentService() {
        setCache(new CourseStudentCache());
    }

}