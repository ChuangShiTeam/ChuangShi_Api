package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ProductSkuCommissionCache;
import com.nowui.chuangshi.model.ProductSkuCommission;

import java.util.List;

public class ProductSkuCommissionService extends Service {

    private ProductSkuCommissionCache productSkuCommissionCache = new ProductSkuCommissionCache();

    public List<ProductSkuCommission> listByProduct_sku_id(String product_sku_id) {
        return productSkuCommissionCache.listByProduct_sku_id(product_sku_id);
    }

    public Boolean save(List<ProductSkuCommission> productSkuCommissionList, String system_create_user_id) {
        return productSkuCommissionCache.save(productSkuCommissionList, system_create_user_id);
    }

    public Boolean delete(List<String> productSkuIdList, String system_update_user_id) {
        return productSkuCommissionCache.delete(productSkuIdList, system_update_user_id);
    }

}