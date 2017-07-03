package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.ProductSkuDao;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.util.CacheUtil;

public class ProductSkuCache extends Cache {

    public static final String PRODUCT_SKU_BY_PRODUCT_SKU_ID_CACHE = "product_sku_by_product_sku_id_cache";

    private ProductSkuDao productSkuDao = new ProductSkuDao();

    public ProductSku findByProduct_sku_id(String product_sku_id, String request_app_id, String request_http_id, String request_user_id) {
        ProductSku product_sku = CacheUtil.get(PRODUCT_SKU_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);

        if (product_sku == null) {
            product_sku = productSkuDao.findByProduct_sku_id(product_sku_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(PRODUCT_SKU_BY_PRODUCT_SKU_ID_CACHE, product_sku_id, product_sku);
        }

        return product_sku;
    }

    public Boolean save(String product_sku_id, String product_id, Boolean product_sku_is_default, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return productSkuDao.save(product_sku_id, product_id, product_sku_is_default, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String product_sku_id, String product_id, Boolean product_sku_is_default, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        ProductSku product_sku = findByProduct_sku_id(product_sku_id, request_app_id, request_http_id, request_user_id);
        if (!product_sku.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = productSkuDao.update(product_sku_id, product_id, product_sku_is_default, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(PRODUCT_SKU_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);
        }

        return result;
    }

    public Boolean deleteByProduct_sku_idAndSystem_update_user_idValidateSystem_version(String product_sku_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        ProductSku product_sku = findByProduct_sku_id(product_sku_id, request_app_id, request_http_id, request_user_id);
        if (!product_sku.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = productSkuDao.deleteByProduct_sku_idAndSystem_version(product_sku_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(PRODUCT_SKU_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);
        }

        return result;
    }

}