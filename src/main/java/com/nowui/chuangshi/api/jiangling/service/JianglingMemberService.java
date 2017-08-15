package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.cache.JianglingMemberCache;
import com.nowui.chuangshi.common.service.Service;

public class JianglingMemberService extends Service {

    public static final JianglingMemberService me = new JianglingMemberService();

    public JianglingMemberService() {
        setCache(new JianglingMemberCache());
    }

}