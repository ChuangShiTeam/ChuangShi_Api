package com.nowui.chuangshi.api.member.service;

import com.nowui.chuangshi.api.member.cache.MemberLevelCache;
import com.nowui.chuangshi.common.service.Service;

public class MemberLevelService extends Service {

    public static final MemberLevelService me = new MemberLevelService();

    public MemberLevelService() {
        setCache(new MemberLevelCache());
    }

}