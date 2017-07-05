package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.dao.ProductSkuPriceDao;
import com.nowui.chuangshi.model.ProductSkuPrice;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductSkuPriceCache extends Cache {

    public static final String PRODUCT_SKU_PRICE_LIST_BY_PRODUCT_SKU_ID_CACHE = "product_sku_price_list_by_product_sku_id_cache";

    private ProductSkuPriceDao productSkuPriceDao = new ProductSkuPriceDao();

    public List<ProductSkuPrice> listByProduct_sku_id(String product_sku_id, String request_app_id, String request_http_id, String request_user_id) {
        List<ProductSkuPrice> productSkuPriceList = CacheUtil.get(PRODUCT_SKU_PRICE_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);

        if (productSkuPriceList == null) {
            productSkuPriceList = productSkuPriceDao.listByProduct_sku_id(product_sku_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(PRODUCT_SKU_PRICE_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id, productSkuPriceList);
        }

        return productSkuPriceList;
    }

    public Boolean save(List<ProductSkuPrice> productSkuPriceList, String request_app_id, String request_http_id, String request_user_id) {
        Boolean result = productSkuPriceDao.save(productSkuPriceList, request_app_id, request_http_id, request_user_id);

        if (result) {
            for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
                CacheUtil.remove(PRODUCT_SKU_PRICE_LIST_BY_PRODUCT_SKU_ID_CACHE, productSkuPrice.getProduct_sku_id());
            }
        }

        return result;
    }

    public Boolean delete(List<String> productSkuIdList, String request_app_id, String request_http_id, String request_user_id) {
        Boolean result = productSkuPriceDao.delete(productSkuIdList, request_app_id, request_http_id, request_user_id);

        if (result) {
            for (String product_sku_id : productSkuIdList) {
                CacheUtil.remove(PRODUCT_SKU_PRICE_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);
            }
        }

        return result;
    }

}