package com.nowui.chuangshi.api.feijiu.cache;

import com.nowui.chuangshi.api.feijiu.dao.FeijiuFastProductDao;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProduct;
import com.nowui.chuangshi.common.cache.Cache;

public class FeijiuFastProductCache extends Cache {

    public static final String FEIJIU_FAST_PRODUCT_ITEM_CACHE = "feijiu_fast_product_item_cache";

    public FeijiuFastProductCache() {
        setDao(new FeijiuFastProductDao());

        setItemCache(FEIJIU_FAST_PRODUCT_ITEM_CACHE, FeijiuFastProduct.PRODUCT_ID);
    }

}