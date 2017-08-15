package com.nowui.chuangshi.api.jiangling.cache;

import com.nowui.chuangshi.api.jiangling.dao.JianglingMemberPrizeDao;
import com.nowui.chuangshi.common.cache.Cache;

public class JianglingMemberPrizeCache extends Cache {

    public static final String JIANGLING_MEMBER_PRIZE_ITEM_CACHE = "jiangling_member_prize_item_cache";

    public JianglingMemberPrizeCache() {
        setDao(new JianglingMemberPrizeDao());

    }

}