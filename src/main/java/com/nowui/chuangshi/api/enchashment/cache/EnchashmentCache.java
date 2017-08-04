package com.nowui.chuangshi.api.enchashment.cache;

import com.nowui.chuangshi.api.enchashment.dao.EnchashmentDao;
import com.nowui.chuangshi.api.enchashment.model.Enchashment;
import com.nowui.chuangshi.common.cache.Cache;

public class EnchashmentCache extends Cache {

    public static final String ENCHASHMENT_ITEM_CACHE = "enchashment_item_cache";

    public EnchashmentCache() {
        setDao(new EnchashmentDao());

        setItemCache(ENCHASHMENT_ITEM_CACHE, Enchashment.ENCHASHMENT_ID);
    }

}