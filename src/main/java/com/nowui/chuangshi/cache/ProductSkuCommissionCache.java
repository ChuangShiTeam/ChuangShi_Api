package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.dao.ProductSkuCommissionDao;
import com.nowui.chuangshi.model.ProductSkuCommission;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductSkuCommissionCache extends Cache {

    public static final String PRODUCT_SKU_COMMISSION_LIST_BY_PRODUCT_SKU_ID_CACHE = "product_sku_commission_list_by_product_sku_id_cache";

    private ProductSkuCommissionDao productSkuCommissionDao = new ProductSkuCommissionDao();

    public List<ProductSkuCommission> listByProduct_sku_id(String product_sku_id) {
        List<ProductSkuCommission> productSkuCommissionList = CacheUtil.get(PRODUCT_SKU_COMMISSION_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);

        if (productSkuCommissionList == null) {
            productSkuCommissionList = productSkuCommissionDao.listByProduct_sku_id(product_sku_id);

            CacheUtil.put(PRODUCT_SKU_COMMISSION_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id, productSkuCommissionList);
        }

        return productSkuCommissionList;
    }

    public Boolean save(List<ProductSkuCommission> productSkuCommissionList, String system_create_user_id) {
        Boolean result = productSkuCommissionDao.save(productSkuCommissionList, system_create_user_id);

        if (result) {
            for (ProductSkuCommission productSkuCommission : productSkuCommissionList) {
                CacheUtil.remove(PRODUCT_SKU_COMMISSION_LIST_BY_PRODUCT_SKU_ID_CACHE, productSkuCommission.getProduct_sku_id());
            }
        }

        return result;
    }

    public Boolean delete(List<String> productSkuIdList, String system_update_user_id) {
        Boolean result = productSkuCommissionDao.delete(productSkuIdList, system_update_user_id);

        if (result) {
            for (String product_sku_id : productSkuIdList) {
                CacheUtil.remove(PRODUCT_SKU_COMMISSION_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);
            }
        }

        return result;
    }

}