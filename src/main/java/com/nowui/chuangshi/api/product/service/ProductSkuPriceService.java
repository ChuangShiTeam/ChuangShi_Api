package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.cache.ProductSkuPriceCache;
import com.nowui.chuangshi.common.service.Service;

public class ProductSkuPriceService extends Service {

    public static final ProductSkuPriceService me = new ProductSkuPriceService();

    public ProductSkuPriceService() {
        setCache(new ProductSkuPriceCache());
    }

}