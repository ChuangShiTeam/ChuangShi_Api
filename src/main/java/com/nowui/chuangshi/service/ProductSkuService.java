package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ProductSkuCache;
import com.nowui.chuangshi.model.ProductSku;

import java.util.List;

public class ProductSkuService extends Service {

    private ProductSkuCache productSkuCache = new ProductSkuCache();

    public List<ProductSku> listByProduct_id(String product_id) {
        return productSkuCache.listByProduct_id(product_id);
    }

    public ProductSku findByProduct_sku_id(String product_sku_id) {
        return productSkuCache.findByProduct_sku_id(product_sku_id);
    }

    public Boolean save(String product_id, List<ProductSku> productSkuList, String system_create_user_id) {
        return productSkuCache.save(product_id, productSkuList, system_create_user_id);
    }

    public Boolean delete(String product_id, List<String> productSkuIdList, String system_update_user_id) {
        return productSkuCache.delete(product_id, productSkuIdList, system_update_user_id);
    }

}