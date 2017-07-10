package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowui.chuangshi.cache.ProductSkuPriceCache;
import com.nowui.chuangshi.model.ProductSku;
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

    /**
     * 下单前返回会员信息sku价格
     * 
     * @param jsonArray
     * @param member_level_id
     * @param ret
     * @return
     */
    public Map<String, Object> listByProduct_sku_idAndMember_level_id(JSONArray jsonArray, String member_level_id, Map<String, Object> ret) {
        List<Map<String, Object>> product_sku_list = new ArrayList<Map<String, Object>>();

        BigDecimal total_price = BigDecimal.ZERO;

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            List<ProductSkuPrice> productSkuPriceList = productSkuPriceCache.listByProduct_sku_id(jsonObject.getString(ProductSku.PRODUCT_SKU_ID));

            BigDecimal product_sku_price = BigDecimal.ZERO;

            for (int j = 0; j < productSkuPriceList.size(); j++) {

                if (StringUtils.isEmpty(productSkuPriceList.get(j).getMember_level_id())) {

                    product_sku_price = productSkuPriceList.get(j).getProduct_sku_price();

                } else if (productSkuPriceList.get(j).getMember_level_id().equals(member_level_id)) {

                    jsonObject.put("product_sku_price", productSkuPriceList.get(j).getProduct_sku_price());

                    break;

                } else if (j == productSkuPriceList.size()) {

                    jsonObject.put("product_sku_price", product_sku_price);

                }
            }

            total_price.add(jsonObject.getBigDecimal("product_sku_price"));
            product_sku_list.add(jsonObject);
        }

        ret.put("total_price", total_price);
        ret.put("product_sku_list", product_sku_list);

        return ret;
    }

    public BigDecimal findByProduct_sku_idAndMember_level_id(String product_sku_id, String member_level_id) {
        BigDecimal product_sku_price = BigDecimal.ZERO;

        List<ProductSkuPrice> productSkuPriceList = productSkuPriceCache.listByProduct_sku_id(product_sku_id);
        
        for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
            if (productSkuPrice.getMember_level_id().equals(member_level_id)) {
                return productSkuPrice.getProduct_sku_price();
            }
        }

        return product_sku_price;
    }

}