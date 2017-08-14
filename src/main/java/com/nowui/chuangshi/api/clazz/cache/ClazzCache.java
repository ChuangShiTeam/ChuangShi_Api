package com.nowui.chuangshi.api.clazz.cache;

import com.nowui.chuangshi.api.clazz.dao.ClazzDao;
import com.nowui.chuangshi.api.clazz.model.Clazz;
import com.nowui.chuangshi.common.cache.Cache;

public class ClazzCache extends Cache {

    public static final String CLAZZ_ITEM_CACHE = "clazz_item_cache";

    public ClazzCache() {
        setDao(new ClazzDao());

        setItemCache(CLAZZ_ITEM_CACHE, Clazz.CLAZZ_ID);
    }

}