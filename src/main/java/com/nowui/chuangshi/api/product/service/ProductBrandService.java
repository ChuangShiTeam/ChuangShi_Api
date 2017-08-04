package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.cache.ProductBrandCache;
import com.nowui.chuangshi.common.service.Service;

public class ProductBrandService extends Service {

    public static final ProductBrandService me = new ProductBrandService();

    public ProductBrandService() {
        setCache(new ProductBrandCache());
    }

}