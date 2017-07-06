package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.ProductBrandDao;
import com.nowui.chuangshi.model.ProductBrand;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class ProductBrandCache extends Cache {

    public static final String PRODUCT_BRAND_BY_PRODUCT_BRAND_ID_CACHE = "product_brand_by_product_brand_id_cache";

    private ProductBrandDao productBrandDao = new ProductBrandDao();

    public Integer countByApp_idOrLikeProduct_brand_name(String app_id, String product_brand_name) {
        return productBrandDao.countByApp_idOrLikeProduct_brand_name(app_id, product_brand_name);
    }

    public Integer countByOrApp_idOrLikeProduct_brand_name(String app_id, String product_brand_name) {
        return productBrandDao.countByOrApp_idOrLikeProduct_brand_name(app_id, product_brand_name);
    }

    public List<ProductBrand> listByApp_id(String app_id) {
        List<ProductBrand> product_brandList = productBrandDao.listByApp_id(app_id);

        for (ProductBrand product_brand : product_brandList) {
            product_brand.put(findByProduct_brand_id(product_brand.getProduct_brand_id()));
        }

        return product_brandList;
    }

    public List<ProductBrand> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<ProductBrand> product_brandList = productBrandDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (ProductBrand product_brand : product_brandList) {
            product_brand.put(findByProduct_brand_id(product_brand.getProduct_brand_id()));
        }

        return product_brandList;
    }

    public List<ProductBrand> listByApp_idOrLikeProduct_brand_nameAndLimit(String app_id, String product_brand_name, int m, int n) {
        List<ProductBrand> product_brandList = productBrandDao.listByApp_idOrLikeProduct_brand_nameAndLimit(app_id, product_brand_name, m, n);

        for (ProductBrand product_brand : product_brandList) {
            product_brand.put(findByProduct_brand_id(product_brand.getProduct_brand_id()));
        }

        return product_brandList;
    }

    public List<ProductBrand> listByOrApp_idOrLikeProduct_brand_nameAndLimit(String app_id, String product_brand_name, int m, int n) {
        List<ProductBrand> product_brandList = productBrandDao.listByOrApp_idOrLikeProduct_brand_nameAndLimit(app_id, product_brand_name, m, n);

        for (ProductBrand product_brand : product_brandList) {
            product_brand.put(findByProduct_brand_id(product_brand.getProduct_brand_id()));
        }

        return product_brandList;
    }

    public ProductBrand findByProduct_brand_id(String product_brand_id) {
        ProductBrand product_brand = CacheUtil.get(PRODUCT_BRAND_BY_PRODUCT_BRAND_ID_CACHE, product_brand_id);

        if (product_brand == null) {
            product_brand = productBrandDao.findByProduct_brand_id(product_brand_id);

            CacheUtil.put(PRODUCT_BRAND_BY_PRODUCT_BRAND_ID_CACHE, product_brand_id, product_brand);
        }

        return product_brand;
    }

    public Boolean save(String product_brand_id, String app_id, String product_brand_name, String product_brand_image, String product_brand_content, String system_create_user_id) {
        return productBrandDao.save(product_brand_id, app_id, product_brand_name, product_brand_image, product_brand_content, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String product_brand_id, String product_brand_name, String product_brand_image, String product_brand_content, String system_update_user_id, Integer system_version) {
        ProductBrand product_brand = findByProduct_brand_id(product_brand_id);
        if (!product_brand.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = productBrandDao.update(product_brand_id, product_brand_name, product_brand_image, product_brand_content, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(PRODUCT_BRAND_BY_PRODUCT_BRAND_ID_CACHE, product_brand_id);
        }

        return result;
    }

    public Boolean deleteByProduct_brand_idAndSystem_update_user_idValidateSystem_version(String product_brand_id, String system_update_user_id, Integer system_version) {
        ProductBrand product_brand = findByProduct_brand_id(product_brand_id);
        if (!product_brand.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = productBrandDao.deleteByProduct_brand_idAndSystem_version(product_brand_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(PRODUCT_BRAND_BY_PRODUCT_BRAND_ID_CACHE, product_brand_id);
        }

        return result;
    }

}