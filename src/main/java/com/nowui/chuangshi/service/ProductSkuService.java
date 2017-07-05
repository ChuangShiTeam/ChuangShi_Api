package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ProductSkuCache;
import com.nowui.chuangshi.model.ProductSku;

import java.util.List;

public class ProductSkuService extends Service {

    private ProductSkuCache productSkuCache = new ProductSkuCache();

    public List<ProductSku> listByProduct_id(String product_id, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuCache.listByProduct_id(product_id, request_app_id, request_http_id, request_user_id);
    }

    public ProductSku findByProduct_sku_id(String product_sku_id, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuCache.findByProduct_sku_id(product_sku_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String product_id, List<ProductSku> productSkuList, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuCache.save(product_id, productSkuList, request_app_id, request_http_id, request_user_id);
    }

    public Boolean delete(String product_id, List<String> productSkuIdList, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuCache.delete(product_id, productSkuIdList, request_app_id, request_http_id, request_user_id);
    }

}