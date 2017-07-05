package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ProductSkuPriceCache;
import com.nowui.chuangshi.model.ProductSkuPrice;

import java.util.List;

public class ProductSkuPriceService extends Service {

    private ProductSkuPriceCache productSkuPriceCache = new ProductSkuPriceCache();

    public List<ProductSkuPrice> listByProduct_sku_id(String product_sku_id) {
        return productSkuPriceCache.listByProduct_sku_id(product_sku_id);
    }

    public Boolean save(List<ProductSkuPrice> productSkuPriceList, String system_create_user_id) {
        return productSkuPriceCache.save(productSkuPriceList, system_create_user_id);
    }

    public Boolean delete(List<String> productSkuIdList, String system_update_user_id) {
        return productSkuPriceCache.delete(productSkuIdList, system_update_user_id);
    }

}