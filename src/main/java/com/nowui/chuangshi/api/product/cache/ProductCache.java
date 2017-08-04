package com.nowui.chuangshi.api.product.cache;

import com.nowui.chuangshi.api.product.dao.ProductDao;
import com.nowui.chuangshi.api.product.model.Product;
import com.nowui.chuangshi.common.cache.Cache;

public class ProductCache extends Cache {

    public static final String PRODUCT_ITEM_CACHE = "product_item_cache";

    public ProductCache() {
        setDao(new ProductDao());

        setItemCache(PRODUCT_ITEM_CACHE, Product.PRODUCT_ID);
    }

}