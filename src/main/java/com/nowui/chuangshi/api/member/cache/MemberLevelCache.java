package com.nowui.chuangshi.api.member.cache;

import com.nowui.chuangshi.api.member.dao.MemberLevelDao;
import com.nowui.chuangshi.api.member.model.MemberLevel;
import com.nowui.chuangshi.common.cache.Cache;

public class MemberLevelCache extends Cache {

    public static final String MEMBER_LEVEL_ITEM_CACHE = "member_level_item_cache";

    public MemberLevelCache() {
        setDao(new MemberLevelDao());

        setItemCache(MEMBER_LEVEL_ITEM_CACHE, MemberLevel.MEMBER_LEVEL_ID);
    }

}