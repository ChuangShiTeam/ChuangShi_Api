package com.nowui.chuangshi.api.page.service;

import com.nowui.chuangshi.api.page.cache.PageCache;
import com.nowui.chuangshi.common.service.Service;

public class PageService extends Service {

    public static final PageService me = new PageService();

    public PageService() {
        setCache(new PageCache());
    }

}