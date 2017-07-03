package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ProductSkuCache;
import com.nowui.chuangshi.model.ProductSku;

public class ProductSkuService extends Service {

    private ProductSkuCache productSkuCache = new ProductSkuCache();

    public ProductSku findByProduct_sku_id(String product_sku_id, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuCache.findByProduct_sku_id(product_sku_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String product_sku_id, String product_id, Boolean product_sku_is_default, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuCache.save(product_sku_id, product_id, product_sku_is_default, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String product_sku_id, String product_id, Boolean product_sku_is_default, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuCache.updateValidateSystem_version(product_sku_id, product_id, product_sku_is_default, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByProduct_sku_idAndSystem_update_user_idValidateSystem_version(String product_sku_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuCache.deleteByProduct_sku_idAndSystem_update_user_idValidateSystem_version(product_sku_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}