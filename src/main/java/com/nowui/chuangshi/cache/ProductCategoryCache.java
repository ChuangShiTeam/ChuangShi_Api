package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.ProductCategoryDao;
import com.nowui.chuangshi.model.ProductCategory;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductCategoryCache extends Cache {

    public static final String PRODUCT_CATEGORY_BY_PRODUCT_CATEGORY_ID_CACHE = "product_category_by_product_category_id_cache";

    private ProductCategoryDao productCategoryDao = new ProductCategoryDao();

    public List<ProductCategory> listByApp_id(String app_id) {
        List<ProductCategory> product_categoryList = productCategoryDao.listByApp_id(app_id);

        for (ProductCategory product_category : product_categoryList) {
            product_category.put(findByProduct_category_id(product_category.getProduct_category_id()));
        }

        return product_categoryList;
    }

    public List<ProductCategory> listByApp_idOrLikeProduct_category_name(String app_id, String product_category_name) {
        List<ProductCategory> product_categoryList = productCategoryDao.listByApp_idOrLikeProduct_category_name(app_id, product_category_name);

        for (ProductCategory product_category : product_categoryList) {
            product_category.put(findByProduct_category_id(product_category.getProduct_category_id()));
        }

        return product_categoryList;
    }

    public List<ProductCategory> listByOrApp_idOrLikeProduct_category_name(String app_id, String product_category_name) {
        List<ProductCategory> product_categoryList = productCategoryDao.listByOrApp_idOrLikeProduct_category_name(app_id, product_category_name);

        for (ProductCategory product_category : product_categoryList) {
            product_category.put(findByProduct_category_id(product_category.getProduct_category_id()));
        }

        return product_categoryList;
    }

    public ProductCategory findByProduct_category_id(String product_category_id) {
        ProductCategory product_category = CacheUtil.get(PRODUCT_CATEGORY_BY_PRODUCT_CATEGORY_ID_CACHE, product_category_id);

        if (product_category == null) {
            product_category = productCategoryDao.findByProduct_category_id(product_category_id);

            CacheUtil.put(PRODUCT_CATEGORY_BY_PRODUCT_CATEGORY_ID_CACHE, product_category_id, product_category);
        }

        return product_category;
    }

    public Boolean save(String product_category_id, String app_id, String product_category_parent_id, String product_category_name, Integer product_category_sort, String product_category_path, String system_create_user_id) {
        return productCategoryDao.save(product_category_id, app_id, product_category_parent_id, product_category_name, product_category_sort, product_category_path, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String product_category_id, String product_category_parent_id, String product_category_name, Integer product_category_sort, String product_category_path, String system_update_user_id, Integer system_version) {
        ProductCategory product_category = findByProduct_category_id(product_category_id);
        if (!product_category.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = productCategoryDao.update(product_category_id, product_category_parent_id, product_category_name, product_category_sort, product_category_path, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(PRODUCT_CATEGORY_BY_PRODUCT_CATEGORY_ID_CACHE, product_category_id);
        }

        return result;
    }

    public Boolean deleteByProduct_category_idAndSystem_update_user_idValidateSystem_version(String product_category_id, String system_update_user_id, Integer system_version) {
        ProductCategory product_category = findByProduct_category_id(product_category_id);
        if (!product_category.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = productCategoryDao.deleteByProduct_category_idAndSystem_version(product_category_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(PRODUCT_CATEGORY_BY_PRODUCT_CATEGORY_ID_CACHE, product_category_id);
        }

        return result;
    }

    public Boolean deleteByProduct_category_parent_id(String product_category_parent_id, String system_update_user_id) {
        List<ProductCategory> productCategoryList = productCategoryDao.listByLikeProduct_category_parent_id(product_category_parent_id);

        boolean result = productCategoryDao.deleteByProduct_category_parent_id(product_category_parent_id, system_update_user_id);

        if (result) {
            for (ProductCategory productCategory : productCategoryList) {
                CacheUtil.remove(PRODUCT_CATEGORY_BY_PRODUCT_CATEGORY_ID_CACHE, productCategory.getProduct_category_id());
            }
        }

        return result;
    }

}