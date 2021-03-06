package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.dao.ProductSkuAttributeDao;
import com.nowui.chuangshi.model.ProductSkuAttribute;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductSkuAttributeCache extends Cache {

    public static final String PRODUCT_SKU_ATTRIBUTE_LIST_BY_PRODUCT_SKU_ID_CACHE = "product_sku_attribute_list_by_product_sku_id_cache";

    private ProductSkuAttributeDao productSkuAttributeDao = new ProductSkuAttributeDao();

    public List<ProductSkuAttribute> listByProduct_sku_id(String product_sku_id) {
        List<ProductSkuAttribute> productSkuAttributeList = CacheUtil.get(PRODUCT_SKU_ATTRIBUTE_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);

        if (productSkuAttributeList == null) {
            productSkuAttributeList = productSkuAttributeDao.listByProduct_sku_id(product_sku_id);

            CacheUtil.put(PRODUCT_SKU_ATTRIBUTE_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id, productSkuAttributeList);
        }

        return productSkuAttributeList;
    }

    public Boolean save(List<ProductSkuAttribute> productSkuAttributeList, String system_create_user_id) {
        Boolean result = productSkuAttributeDao.save(productSkuAttributeList, system_create_user_id);

        if (result) {
            for (ProductSkuAttribute productSkuAttribute : productSkuAttributeList) {
                CacheUtil.remove(PRODUCT_SKU_ATTRIBUTE_LIST_BY_PRODUCT_SKU_ID_CACHE, productSkuAttribute.getProduct_sku_id());
            }
        }

        return result;
    }

    public Boolean delete(List<String> productSkuIdList, String system_update_user_id) {
        Boolean result = productSkuAttributeDao.delete(productSkuIdList, system_update_user_id);

        if (result) {
            for (String product_sku_id : productSkuIdList) {
                CacheUtil.remove(PRODUCT_SKU_ATTRIBUTE_LIST_BY_PRODUCT_SKU_ID_CACHE, product_sku_id);
            }
        }

        return result;
    }

}