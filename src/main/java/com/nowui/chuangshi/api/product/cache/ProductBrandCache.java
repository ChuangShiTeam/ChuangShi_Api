package com.nowui.chuangshi.api.product.cache;

import com.nowui.chuangshi.api.product.dao.ProductBrandDao;
import com.nowui.chuangshi.api.product.model.ProductBrand;
import com.nowui.chuangshi.common.cache.Cache;

public class ProductBrandCache extends Cache {

    public static final String PRODUCT_BRAND_ITEM_CACHE = "product_brand_item_cache";

    public ProductBrandCache() {
        setDao(new ProductBrandDao());

        setItemCache(PRODUCT_BRAND_ITEM_CACHE, ProductBrand.PRODUCT_BRAND_ID);
    }

}