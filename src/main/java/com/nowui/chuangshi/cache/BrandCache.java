package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.BrandDao;
import com.nowui.chuangshi.model.Brand;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class BrandCache extends Cache {

    public static final String BRAND_BY_BRAND_ID_CACHE = "brand_by_brand_id_cache";

    private BrandDao brandDao = new BrandDao();

    public Integer countByApp_idOrLikeBrand_name(String app_id, String brand_name, String request_app_id, String request_http_id, String request_user_id) {
        return brandDao.countByApp_idOrLikeBrand_name(app_id, brand_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idOrLikeBrand_name(String app_id, String brand_name, String request_app_id, String request_http_id, String request_user_id) {
        return brandDao.countByOrApp_idOrLikeBrand_name(app_id, brand_name, request_app_id, request_http_id, request_user_id);
    }

    public List<Brand> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<Brand> brandList = brandDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);

        for (Brand brand : brandList) {
            brand.put(findByBrand_id(brand.getBrand_id(), request_app_id, request_http_id, request_user_id));
        }

        return brandList;
    }

    public List<Brand> listByApp_idOrLikeBrand_nameAndLimit(String app_id, String brand_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<Brand> brandList = brandDao.listByApp_idOrLikeBrand_nameAndLimit(app_id, brand_name, m, n, request_app_id, request_http_id, request_user_id);

        for (Brand brand : brandList) {
            brand.put(findByBrand_id(brand.getBrand_id(), request_app_id, request_http_id, request_user_id));
        }

        return brandList;
    }

    public List<Brand> listByOrApp_idOrLikeBrand_nameAndLimit(String app_id, String brand_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<Brand> brandList = brandDao.listByOrApp_idOrLikeBrand_nameAndLimit(app_id, brand_name, m, n, request_app_id, request_http_id, request_user_id);

        for (Brand brand : brandList) {
            brand.put(findByBrand_id(brand.getBrand_id(), request_app_id, request_http_id, request_user_id));
        }

        return brandList;
    }

    public Brand findByBrand_id(String brand_id, String request_app_id, String request_http_id, String request_user_id) {
        Brand brand = CacheUtil.get(BRAND_BY_BRAND_ID_CACHE, brand_id);

        if (brand == null) {
            brand = brandDao.findByBrand_id(brand_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(BRAND_BY_BRAND_ID_CACHE, brand_id, brand);
        }

        return brand;
    }

    public Boolean save(String brand_id, String app_id, String category_id, String brand_name, String brand_image, String brand_content, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return brandDao.save(brand_id, app_id, category_id, brand_name, brand_image, brand_content, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String brand_id, String category_id, String brand_name, String brand_image, String brand_content, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Brand brand = findByBrand_id(brand_id, request_app_id, request_http_id, request_user_id);
        if (!brand.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = brandDao.update(brand_id, category_id, brand_name, brand_image, brand_content, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(BRAND_BY_BRAND_ID_CACHE, brand_id);
        }

        return result;
    }

    public Boolean deleteByBrand_idAndSystem_update_user_idValidateSystem_version(String brand_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Brand brand = findByBrand_id(brand_id, request_app_id, request_http_id, request_user_id);
        if (!brand.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = brandDao.deleteByBrand_idAndSystem_version(brand_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(BRAND_BY_BRAND_ID_CACHE, brand_id);
        }

        return result;
    }

}