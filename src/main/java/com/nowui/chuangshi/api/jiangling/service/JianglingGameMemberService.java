package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.cache.JianglingGameMemberCache;
import com.nowui.chuangshi.common.service.Service;

public class JianglingGameMemberService extends Service {

    public static final JianglingGameMemberService me = new JianglingGameMemberService();

    public JianglingGameMemberService() {
        setCache(new JianglingGameMemberCache());
    }

}