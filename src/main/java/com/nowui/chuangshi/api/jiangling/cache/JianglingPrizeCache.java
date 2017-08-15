package com.nowui.chuangshi.api.jiangling.cache;

import com.nowui.chuangshi.api.jiangling.dao.JianglingPrizeDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingPrize;
import com.nowui.chuangshi.common.cache.Cache;

public class JianglingPrizeCache extends Cache {

    public static final String JIANGLING_PRIZE_ITEM_CACHE = "jiangling_prize_item_cache";

    public JianglingPrizeCache() {
        setDao(new JianglingPrizeDao());

        setItemCache(JIANGLING_PRIZE_ITEM_CACHE, JianglingPrize.PRIZE_ID);
    }

}