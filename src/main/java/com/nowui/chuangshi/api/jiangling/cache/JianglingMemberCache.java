package com.nowui.chuangshi.api.jiangling.cache;

import com.nowui.chuangshi.api.jiangling.dao.JianglingMemberDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingMember;
import com.nowui.chuangshi.common.cache.Cache;

public class JianglingMemberCache extends Cache {

    public static final String JIANGLING_MEMBER_ITEM_CACHE = "jiangling_member_item_cache";

    public JianglingMemberCache() {
        setDao(new JianglingMemberDao());

        setItemCache(JIANGLING_MEMBER_ITEM_CACHE, JianglingMember.USER_ID);
    }

}