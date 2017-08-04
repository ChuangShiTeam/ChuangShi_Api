package com.nowui.chuangshi.api.member.cache;

import com.nowui.chuangshi.api.member.dao.MemberDao;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.common.cache.Cache;

public class MemberCache extends Cache {

    public static final String MEMBER_ITEM_CACHE = "member_item_cache";

    public MemberCache() {
        setDao(new MemberDao());

        setItemCache(MEMBER_ITEM_CACHE, Member.MEMBER_ID);
    }

}