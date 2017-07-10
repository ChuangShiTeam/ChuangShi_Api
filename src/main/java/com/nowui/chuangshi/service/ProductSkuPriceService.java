package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.nowui.chuangshi.cache.ProductSkuPriceCache;
import com.nowui.chuangshi.model.ProductSkuPrice;

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

    public BigDecimal findByProduct_sku_idAndMember_level_id(String product_sku_id, String member_level_id) {
        List<ProductSkuPrice> productSkuPriceList = productSkuPriceCache.listByProduct_sku_id(product_sku_id);

        BigDecimal product_sku_price = BigDecimal.ZERO;
        for (int j = 0; j < productSkuPriceList.size(); j++) {
            if (StringUtils.isEmpty(productSkuPriceList.get(j).getMember_level_id())) {
                product_sku_price = productSkuPriceList.get(j).getProduct_sku_price();
            } else if (productSkuPriceList.get(j).getMember_level_id().equals(member_level_id)) {
                return productSkuPriceList.get(j).getProduct_sku_price();
            }
        }

        return product_sku_price;
    }

}