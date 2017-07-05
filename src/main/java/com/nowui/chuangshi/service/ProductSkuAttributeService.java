package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ProductSkuAttributeCache;
import com.nowui.chuangshi.model.ProductSkuAttribute;

import java.util.Date;
import java.util.List;

public class ProductSkuAttributeService extends Service {

    private ProductSkuAttributeCache productSkuAttributeCache = new ProductSkuAttributeCache();

    public List<ProductSkuAttribute> listByProduct_sku_id(String product_sku_id) {
        return productSkuAttributeCache.listByProduct_sku_id(product_sku_id);
    }

    public Boolean save(List<ProductSkuAttribute> productSkuPriceList, String system_create_user_id) {
        return productSkuAttributeCache.save(productSkuPriceList, system_create_user_id);
    }

    public Boolean delete(List<String> productSkuIdList, String system_update_user_id) {
        return productSkuAttributeCache.delete(productSkuIdList, system_update_user_id);
    }

}