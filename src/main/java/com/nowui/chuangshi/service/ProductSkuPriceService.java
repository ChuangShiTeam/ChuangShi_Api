package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ProductSkuPriceCache;
import com.nowui.chuangshi.model.ProductSkuPrice;

import java.util.List;

public class ProductSkuPriceService extends Service {

    private ProductSkuPriceCache productSkuPriceCache = new ProductSkuPriceCache();

    public Boolean save(List<ProductSkuPrice> productSkuPriceList, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuPriceCache.save(productSkuPriceList, request_app_id, request_http_id, request_user_id);
    }

    public Boolean update(List<ProductSkuPrice> productSkuPriceList, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuPriceCache.update(productSkuPriceList, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByProduct_sku_idAndMember_level_id(List<ProductSkuPrice> productSkuPriceList, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuPriceCache.deleteByProduct_sku_idAndMember_level_id(productSkuPriceList, request_app_id, request_http_id, request_user_id);
    }

}