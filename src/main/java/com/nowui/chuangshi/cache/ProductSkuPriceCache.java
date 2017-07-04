package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.dao.ProductSkuPriceDao;
import com.nowui.chuangshi.model.ProductSkuPrice;

import java.util.List;

public class ProductSkuPriceCache extends Cache {

    public static final String PRODUCT_SKU_PRICE_BY_PRODUCT_SKU_PRICE_ID_CACHE = "product_sku_price_by_lower_model_name_id_cache";

    private ProductSkuPriceDao productSkuPriceDao = new ProductSkuPriceDao();

    public Boolean save(List<ProductSkuPrice> productSkuPriceList, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuPriceDao.save(productSkuPriceList, request_app_id, request_http_id, request_user_id);
    }

    public Boolean update(List<ProductSkuPrice> productSkuPriceList, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuPriceDao.update(productSkuPriceList, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByProduct_sku_idAndMember_level_id(List<ProductSkuPrice> productSkuPriceList, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuPriceDao.deleteByProduct_sku_idAndMember_level_id(productSkuPriceList, request_app_id, request_http_id, request_user_id);
    }

}