package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.dao.ProductCategorySkuAttributeDao;
import com.nowui.chuangshi.api.product.model.ProductCategorySkuAttribute;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductCategorySkuAttributeService extends Service {

    public static final ProductCategorySkuAttributeService instance = new ProductCategorySkuAttributeService();
    private final String PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_CACHE = "product_category_sku_attribute_item_cache";
    private final ProductCategorySkuAttributeDao productCategorySkuAttributeDao = new ProductCategorySkuAttributeDao();

    public Integer adminCount(String product_category_id) {
        Cnd cnd = new Cnd();
        cnd.where(ProductCategorySkuAttribute.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(ProductCategorySkuAttribute.PRODUCT_CATEGORY_ID, product_category_id);

        Integer count = productCategorySkuAttributeDao.count(cnd);
        return count;
    }

    public List<ProductCategorySkuAttribute> adminList(String product_category_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(ProductCategorySkuAttribute.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(ProductCategorySkuAttribute.PRODUCT_CATEGORY_ID, product_category_id);
        cnd.asc(ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_SORT);
        cnd.paginate(m, n);

        List<ProductCategorySkuAttribute> product_category_sku_attributeList = productCategorySkuAttributeDao.primaryKeyList(cnd);
        for (ProductCategorySkuAttribute product_category_sku_attribute : product_category_sku_attributeList) {
            product_category_sku_attribute.put(find(product_category_sku_attribute.getProduct_category_sku_attribute_id()));
        }
        return product_category_sku_attributeList;
    }

    public ProductCategorySkuAttribute find(String product_category_sku_attribute_id) {
        ProductCategorySkuAttribute product_category_sku_attribute = CacheUtil.get(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_CACHE, product_category_sku_attribute_id);

        if (product_category_sku_attribute == null) {
            product_category_sku_attribute = productCategorySkuAttributeDao.find(product_category_sku_attribute_id);

            CacheUtil.put(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_CACHE, product_category_sku_attribute_id, product_category_sku_attribute);
        }

        return product_category_sku_attribute;
    }

    public Boolean save(ProductCategorySkuAttribute product_category_sku_attribute, String system_create_user_id) {
        Boolean success = productCategorySkuAttributeDao.save(product_category_sku_attribute, system_create_user_id);
        return success;
    }

    public Boolean update(ProductCategorySkuAttribute product_category_sku_attribute, String product_category_sku_attribute_id, String system_update_user_id, Integer system_version) {
        product_category_sku_attribute.remove(ProductCategorySkuAttribute.PRODUCT_CATEGORY_ID);

        Cnd cnd = new Cnd();
        cnd.where(ProductCategorySkuAttribute.SYSTEM_STATUS, true);
        cnd.and(ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, product_category_sku_attribute_id);
        cnd.and(ProductCategorySkuAttribute.SYSTEM_VERSION, system_version);

        Boolean success = productCategorySkuAttributeDao.update(product_category_sku_attribute, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_CACHE, product_category_sku_attribute_id);
        }

        return success;
    }

    public Boolean delete(String product_category_sku_attribute_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(ProductCategorySkuAttribute.SYSTEM_STATUS, true);
        cnd.and(ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, product_category_sku_attribute_id);
        cnd.and(ProductCategorySkuAttribute.SYSTEM_VERSION, system_version);

        Boolean success = productCategorySkuAttributeDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_CACHE, product_category_sku_attribute_id);
        }

        return success;
    }

}