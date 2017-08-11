package com.nowui.chuangshi.api.school.service;

import com.nowui.chuangshi.api.school.cache.ClazzCache;
import com.nowui.chuangshi.common.service.Service;

public class ClazzService extends Service {

    public static final ClazzService me = new ClazzService();

    public ClazzService() {
        setCache(new ClazzCache());
    }

}