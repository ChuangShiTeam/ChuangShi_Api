package com.nowui.chuangshi.api.product.cache;

import com.nowui.chuangshi.api.product.dao.ProductSkuPriceDao;
import com.nowui.chuangshi.common.cache.Cache;

public class ProductSkuPriceCache extends Cache {

    public static final String PRODUCT_SKU_PRICE_ITEM_CACHE = "product_sku_price_item_cache";

    public ProductSkuPriceCache() {
        setDao(new ProductSkuPriceDao());

    }

}