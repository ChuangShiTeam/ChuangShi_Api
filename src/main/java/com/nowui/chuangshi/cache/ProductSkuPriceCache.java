package com.nowui.chuangshi.cache;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowui.chuangshi.dao.ProductSkuPriceDao;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.ProductSkuPrice;
import com.nowui.chuangshi.util.CacheUtil;

public class ProductSkuPriceCache extends Cache {

    public static final String PRODUCT_SKU_PRICE_LIST_BY_PRODUCT_SKU_ID_CACHE = "product_sku_price_list_by_product_sku_id_cache";

    private ProductSkuPriceDao productSkuPriceDao = new ProductSkuPriceDao();

    public List<ProductSkuPrice> listByProduct_sku_id(String product_sku_id) {
        List<ProductSkuPrice> productSkuPriceList = CacheUtil.get(PRODUCT_SKU_PRICE_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);

        if (productSkuPriceList == null) {
            productSkuPriceList = productSkuPriceDao.listByProduct_sku_id(product_sku_id);

            CacheUtil.put(PRODUCT_SKU_PRICE_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id, productSkuPriceList);
        }

        return productSkuPriceList;
    }

    public Boolean save(List<ProductSkuPrice> productSkuPriceList, String system_create_user_id) {
        Boolean result = productSkuPriceDao.save(productSkuPriceList, system_create_user_id);

        if (result) {
            for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
                CacheUtil.remove(PRODUCT_SKU_PRICE_LIST_BY_PRODUCT_SKU_ID_CACHE, productSkuPrice.getProduct_sku_id());
            }
        }

        return result;
    }

    public Boolean delete(List<String> productSkuIdList, String system_update_user_id) {
        Boolean result = productSkuPriceDao.delete(productSkuIdList, system_update_user_id);

        if (result) {
            for (String product_sku_id : productSkuIdList) {
                CacheUtil.remove(PRODUCT_SKU_PRICE_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);
            }
        }

        return result;
    }

    public Map<String, Object> listByProduct_sku_idAndMember_level_id(JSONArray jsonArray, String member_level_id, Map<String, Object> ret) {
        List<Map<String, Object>> product_sku_list = new ArrayList<Map<String, Object>>();

        BigDecimal total_price = BigDecimal.ZERO;

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String product_sku_id = jsonObject.getString(ProductSku.PRODUCT_SKU_ID);

            List<ProductSkuPrice> productSkuPriceList = CacheUtil.get(PRODUCT_SKU_PRICE_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);

            if (productSkuPriceList == null) {
                productSkuPriceList = productSkuPriceDao.listByProduct_sku_id(product_sku_id);

                CacheUtil.put(PRODUCT_SKU_PRICE_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id, productSkuPriceList);
            }

            for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
                if (productSkuPrice.getMember_level_id().equals(member_level_id)) {
                    jsonObject.put("product_sku_price", productSkuPrice.getProduct_sku_price());
                    break;
                } else {
                    jsonObject.put("product_sku_price", BigDecimal.ZERO);
                }
            }

            total_price.add(jsonObject.getBigDecimal("product_sku_price"));
            product_sku_list.add(jsonObject);
        }

        ret.put("total_price", total_price);
        ret.put("product_sku_list", product_sku_list);

        return ret;
    }

}