package com.nowui.chuangshi.api.member.service;

import com.nowui.chuangshi.api.member.cache.MemberCache;
import com.nowui.chuangshi.common.service.Service;

public class MemberService extends Service {

    public static final MemberService me = new MemberService();

    public MemberService() {
        setCache(new MemberCache());
    }

}