package com.nowui.chuangshi.api.feijiu.cache;

import com.nowui.chuangshi.api.feijiu.dao.FeijiuFastProductCategoryDao;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProductCategory;
import com.nowui.chuangshi.common.cache.Cache;

public class FeijiuFastProductCategoryCache extends Cache {

    public static final String FEIJIU_FAST_PRODUCT_CATEGORY_ITEM_CACHE = "feijiu_fast_product_category_item_cache";

    public FeijiuFastProductCategoryCache() {
        setDao(new FeijiuFastProductCategoryDao());

        setItemCache(FEIJIU_FAST_PRODUCT_CATEGORY_ITEM_CACHE, FeijiuFastProductCategory.PRODUCT_CATEGORY_ID);
    }

}