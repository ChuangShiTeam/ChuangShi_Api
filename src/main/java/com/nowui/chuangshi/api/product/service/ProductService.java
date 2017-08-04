package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.cache.ProductCache;
import com.nowui.chuangshi.common.service.Service;

public class ProductService extends Service {

    public static final ProductService me = new ProductService();

    public ProductService() {
        setCache(new ProductCache());
    }

}