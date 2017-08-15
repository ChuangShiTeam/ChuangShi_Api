package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.cache.JianglingMemberPrizeCache;
import com.nowui.chuangshi.common.service.Service;

public class JianglingMemberPrizeService extends Service {

    public static final JianglingMemberPrizeService me = new JianglingMemberPrizeService();

    public JianglingMemberPrizeService() {
        setCache(new JianglingMemberPrizeCache());
    }

}