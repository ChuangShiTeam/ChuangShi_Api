package com.nowui.chuangshi.api.teacher.service;

import com.nowui.chuangshi.api.teacher.cache.TeacherCache;
import com.nowui.chuangshi.common.service.Service;

public class TeacherService extends Service {

    public static final TeacherService me = new TeacherService();

    public TeacherService() {
        setCache(new TeacherCache());
    }

}