package com.nowui.chuangshi.api.enchashment.service;

import com.nowui.chuangshi.api.enchashment.cache.EnchashmentCache;
import com.nowui.chuangshi.common.service.Service;

public class EnchashmentService extends Service {

    public static final EnchashmentService me = new EnchashmentService();

    public EnchashmentService() {
        setCache(new EnchashmentCache());
    }

}