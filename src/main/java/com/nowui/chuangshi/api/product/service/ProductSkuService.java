package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.cache.ProductSkuCache;
import com.nowui.chuangshi.common.service.Service;

public class ProductSkuService extends Service {

    public static final ProductSkuService me = new ProductSkuService();

    public ProductSkuService() {
        setCache(new ProductSkuCache());
    }

}