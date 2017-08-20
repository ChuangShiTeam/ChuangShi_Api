package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.cache.JianglingCustomerCache;
import com.nowui.chuangshi.common.service.Service;

public class JianglingCustomerService extends Service {

    public static final JianglingCustomerService me = new JianglingCustomerService();

    public JianglingCustomerService() {
        setCache(new JianglingCustomerCache());
    }

}