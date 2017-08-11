package com.nowui.chuangshi.api.school.service;

import com.nowui.chuangshi.api.school.cache.StudentCache;
import com.nowui.chuangshi.common.service.Service;

public class StudentService extends Service {

    public static final StudentService me = new StudentService();

    public StudentService() {
        setCache(new StudentCache());
    }

}