package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ProductSkuPriceCache;
import com.nowui.chuangshi.model.ProductSkuPrice;

import java.util.List;

public class ProductSkuPriceService extends Service {

    private ProductSkuPriceCache productSkuPriceCache = new ProductSkuPriceCache();

    public List<ProductSkuPrice> listByProduct_sku_id(String product_sku_id, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuPriceCache.listByProduct_sku_id(product_sku_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(List<ProductSkuPrice> productSkuPriceList, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuPriceCache.save(productSkuPriceList, request_app_id, request_http_id, request_user_id);
    }

    public Boolean delete(List<String> productSkuIdList, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuPriceCache.delete(productSkuIdList, request_app_id, request_http_id, request_user_id);
    }

}