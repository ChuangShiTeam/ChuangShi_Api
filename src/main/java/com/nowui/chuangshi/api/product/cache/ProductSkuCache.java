package com.nowui.chuangshi.api.product.cache;

import com.nowui.chuangshi.api.product.dao.ProductSkuDao;
import com.nowui.chuangshi.api.product.model.ProductSku;
import com.nowui.chuangshi.common.cache.Cache;

public class ProductSkuCache extends Cache {

    public static final String PRODUCT_SKU_ITEM_CACHE = "product_sku_item_cache";

    public ProductSkuCache() {
        setDao(new ProductSkuDao());

        setItemCache(PRODUCT_SKU_ITEM_CACHE, ProductSku.PRODUCT_SKU_ID);
    }

}