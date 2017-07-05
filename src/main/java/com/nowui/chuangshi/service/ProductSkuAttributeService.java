package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ProductSkuAttributeCache;
import com.nowui.chuangshi.model.ProductSkuAttribute;

import java.util.Date;
import java.util.List;

public class ProductSkuAttributeService extends Service {

    private ProductSkuAttributeCache productSkuAttributeCache = new ProductSkuAttributeCache();

    public List<ProductSkuAttribute> listByProduct_sku_id(String product_sku_id, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuAttributeCache.listByProduct_sku_id(product_sku_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(List<ProductSkuAttribute> productSkuPriceList, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuAttributeCache.save(productSkuPriceList, request_app_id, request_http_id, request_user_id);
    }

    public Boolean delete(List<String> productSkuIdList, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuAttributeCache.delete(productSkuIdList, request_app_id, request_http_id, request_user_id);
    }

}