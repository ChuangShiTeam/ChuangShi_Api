package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.dao.ProductSkuDao;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductSkuCache extends Cache {

    public static final String PRODUCT_SKU_LIST_BY_PRODUCT_ID_CACHE = "product_sku_list_by_product_id_cache";
    public static final String PRODUCT_SKU_BY_PRODUCT_SKU_ID_CACHE = "product_sku_by_product_sku_id_cache";

    private ProductSkuDao productSkuDao = new ProductSkuDao();

    public List<ProductSku> listByProduct_id(String product_id) {
        List<ProductSku> productSkuList = CacheUtil.get(PRODUCT_SKU_LIST_BY_PRODUCT_ID_CACHE, product_id);

        if (productSkuList == null) {
            productSkuList = productSkuDao.listByProduct_id(product_id);

            CacheUtil.put(PRODUCT_SKU_LIST_BY_PRODUCT_ID_CACHE, product_id, productSkuList);
        }

        return productSkuList;
    }

    public ProductSku findByProduct_sku_id(String product_sku_id) {
        ProductSku product_sku = CacheUtil.get(PRODUCT_SKU_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);

        if (product_sku == null) {
            product_sku = productSkuDao.findByProduct_sku_id(product_sku_id);

            CacheUtil.put(PRODUCT_SKU_BY_PRODUCT_SKU_ID_CACHE, product_sku_id, product_sku);
        }

        return product_sku;
    }

    public Boolean save(String product_id, List<ProductSku> productSkuList, String system_create_user_id) {
        boolean result = productSkuDao.save(productSkuList, system_create_user_id);

        if (result) {
            CacheUtil.remove(PRODUCT_SKU_LIST_BY_PRODUCT_ID_CACHE, product_id);
        }

        return result;
    }

    public Boolean delete(String product_id, List<String> productSkuIdList, String system_update_user_id) {
        boolean result = productSkuDao.delete(productSkuIdList, system_update_user_id);

        if (result) {
            CacheUtil.remove(PRODUCT_SKU_LIST_BY_PRODUCT_ID_CACHE, product_id);

            for(String product_sku_id : productSkuIdList) {
                CacheUtil.remove(PRODUCT_SKU_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);
            }
        }

        return result;
    }

}