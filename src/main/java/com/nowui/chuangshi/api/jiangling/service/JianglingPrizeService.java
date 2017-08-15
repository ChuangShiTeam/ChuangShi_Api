package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.cache.JianglingPrizeCache;
import com.nowui.chuangshi.common.service.Service;

public class JianglingPrizeService extends Service {

    public static final JianglingPrizeService me = new JianglingPrizeService();

    public JianglingPrizeService() {
        setCache(new JianglingPrizeCache());
    }

}