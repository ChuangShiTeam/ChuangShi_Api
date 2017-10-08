package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.dao.ProductSkuAttributeDao;
import com.nowui.chuangshi.api.product.model.ProductSkuAttribute;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductSkuAttributeService extends Service {

    public static final ProductSkuAttributeService instance = new ProductSkuAttributeService();
    private final String PRODUCT_SKU_ATTRIBUTE_ITEM_CACHE = "product_sku_attribute_item_cache";
    private final ProductSkuAttributeDao productSkuAttributeDao = new ProductSkuAttributeDao();

    public List<ProductSkuAttribute> productSkuList(String product_sku_id) {
        Cnd cnd = new Cnd();
        cnd.where(ProductSkuAttribute.SYSTEM_STATUS, true);
        cnd.and(ProductSkuAttribute.PRODUCT_SKU_ID, product_sku_id);

        List<ProductSkuAttribute> product_sku_attributeList = productSkuAttributeDao.primaryKeyList(cnd);
        for (ProductSkuAttribute product_sku_attribute : product_sku_attributeList) {
            product_sku_attribute.put(find(product_sku_attribute.getProduct_sku_attribute_id()));
        }
        return product_sku_attributeList;
    }

    public ProductSkuAttribute find(String product_sku_attribute_id) {
        ProductSkuAttribute product_sku_attribute = CacheUtil.get(PRODUCT_SKU_ATTRIBUTE_ITEM_CACHE, product_sku_attribute_id);

        if (product_sku_attribute == null) {
            product_sku_attribute = productSkuAttributeDao.find(product_sku_attribute_id);

            CacheUtil.put(PRODUCT_SKU_ATTRIBUTE_ITEM_CACHE, product_sku_attribute_id, product_sku_attribute);
        }

        return product_sku_attribute;
    }

    public Boolean save(ProductSkuAttribute product_sku_attribute, String system_create_user_id) {
        Boolean success = productSkuAttributeDao.save(product_sku_attribute, system_create_user_id);
        return success;
    }

    public Boolean update(ProductSkuAttribute product_sku_attribute, String product_sku_attribute_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(ProductSkuAttribute.SYSTEM_STATUS, true);
        cnd.and(ProductSkuAttribute.PRODUCT_SKU_ATTRIBUTE_ID, product_sku_attribute_id);
        cnd.and(ProductSkuAttribute.SYSTEM_VERSION, system_version);

        Boolean success = productSkuAttributeDao.update(product_sku_attribute, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_SKU_ATTRIBUTE_ITEM_CACHE, product_sku_attribute_id);
        }

        return success;
    }

    public Boolean delete(String product_sku_attribute_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(ProductSkuAttribute.SYSTEM_STATUS, true);
        cnd.and(ProductSkuAttribute.PRODUCT_SKU_ATTRIBUTE_ID, product_sku_attribute_id);
        cnd.and(ProductSkuAttribute.SYSTEM_VERSION, system_version);

        Boolean success = productSkuAttributeDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_SKU_ATTRIBUTE_ITEM_CACHE, product_sku_attribute_id);
        }

        return success;
    }

}