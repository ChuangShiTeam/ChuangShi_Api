package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.dao.ProductCategorySkuAttributeItemDao;
import com.nowui.chuangshi.api.product.model.ProductCategorySkuAttributeItem;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductCategorySkuAttributeItemService extends Service {

    public static final ProductCategorySkuAttributeItemService instance = new ProductCategorySkuAttributeItemService();
    private final String PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ITEM_CACHE = "product_category_sku_attribute_item_item_cache";
    private final ProductCategorySkuAttributeItemDao productCategorySkuAttributeItemDao = new ProductCategorySkuAttributeItemDao();

    public Integer adminCount(String product_category_sku_attribute_id) {
        Cnd cnd = new Cnd();
        cnd.where(ProductCategorySkuAttributeItem.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, product_category_sku_attribute_id);

        Integer count = productCategorySkuAttributeItemDao.count(cnd);
        return count;
    }

    public List<ProductCategorySkuAttributeItem> adminList(String product_category_sku_attribute_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(ProductCategorySkuAttributeItem.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, product_category_sku_attribute_id);
        cnd.asc(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_SORT);
        cnd.paginate(m, n);

        List<ProductCategorySkuAttributeItem> product_category_sku_attribute_itemList = productCategorySkuAttributeItemDao.primaryKeyList(cnd);
        for (ProductCategorySkuAttributeItem product_category_sku_attribute_item : product_category_sku_attribute_itemList) {
            product_category_sku_attribute_item.put(find(product_category_sku_attribute_item.getProduct_category_sku_attribute_item_id()));
        }
        return product_category_sku_attribute_itemList;
    }

    public ProductCategorySkuAttributeItem find(String product_category_sku_attribute_item_id) {
        ProductCategorySkuAttributeItem product_category_sku_attribute_item = CacheUtil.get(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ITEM_CACHE, product_category_sku_attribute_item_id);

        if (product_category_sku_attribute_item == null) {
            product_category_sku_attribute_item = productCategorySkuAttributeItemDao.find(product_category_sku_attribute_item_id);

            CacheUtil.put(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ITEM_CACHE, product_category_sku_attribute_item_id, product_category_sku_attribute_item);
        }

        return product_category_sku_attribute_item;
    }

    public Boolean save(ProductCategorySkuAttributeItem product_category_sku_attribute_item, String system_create_user_id) {
        Boolean success = productCategorySkuAttributeItemDao.save(product_category_sku_attribute_item, system_create_user_id);
        return success;
    }

    public Boolean update(ProductCategorySkuAttributeItem product_category_sku_attribute_item, String product_category_sku_attribute_item_id, String system_update_user_id, Integer system_version) {
        product_category_sku_attribute_item.remove(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID);

        Cnd cnd = new Cnd();
        cnd.where(ProductCategorySkuAttributeItem.SYSTEM_STATUS, true);
        cnd.and(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ID, product_category_sku_attribute_item_id);
        cnd.and(ProductCategorySkuAttributeItem.SYSTEM_VERSION, system_version);

        Boolean success = productCategorySkuAttributeItemDao.update(product_category_sku_attribute_item, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ITEM_CACHE, product_category_sku_attribute_item_id);
        }

        return success;
    }

    public Boolean delete(String product_category_sku_attribute_item_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(ProductCategorySkuAttributeItem.SYSTEM_STATUS, true);
        cnd.and(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ID, product_category_sku_attribute_item_id);
        cnd.and(ProductCategorySkuAttributeItem.SYSTEM_VERSION, system_version);

        Boolean success = productCategorySkuAttributeItemDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ITEM_CACHE, product_category_sku_attribute_item_id);
        }

        return success;
    }

}