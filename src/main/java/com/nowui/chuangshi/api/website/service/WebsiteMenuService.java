package com.nowui.chuangshi.api.website.service;

import com.nowui.chuangshi.api.website.cache.WebsiteMenuCache;
import com.nowui.chuangshi.common.service.Service;

public class WebsiteMenuService extends Service {

    public static final WebsiteMenuService me = new WebsiteMenuService();

    public WebsiteMenuService() {
        setCache(new WebsiteMenuCache());
    }

}