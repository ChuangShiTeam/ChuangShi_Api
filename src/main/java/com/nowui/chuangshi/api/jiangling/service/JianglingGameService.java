package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.cache.JianglingGameCache;
import com.nowui.chuangshi.common.service.Service;

public class JianglingGameService extends Service {

    public static final JianglingGameService me = new JianglingGameService();

    public JianglingGameService() {
        setCache(new JianglingGameCache());
    }

}