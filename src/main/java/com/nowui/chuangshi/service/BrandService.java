package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.BrandCache;
import com.nowui.chuangshi.model.Brand;

import java.util.Date;
import java.util.List;

public class BrandService extends Service {

    private BrandCache brandCache = new BrandCache();

    public Integer countByApp_idOrLikeBrand_name(String app_id, String brand_name, String request_app_id, String request_http_id, String request_user_id) {
        return brandCache.countByApp_idOrLikeBrand_name(app_id, brand_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idOrLikeBrand_name(String app_id, String brand_name, String request_app_id, String request_http_id, String request_user_id) {
        return brandCache.countByOrApp_idOrLikeBrand_name(app_id, brand_name, request_app_id, request_http_id, request_user_id);
    }

    public List<Brand> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return brandCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<Brand> listByApp_idOrLikeBrand_nameAndLimit(String app_id, String brand_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return brandCache.listByApp_idOrLikeBrand_nameAndLimit(app_id, brand_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<Brand> listByOrApp_idOrLikeBrand_nameAndLimit(String app_id, String brand_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return brandCache.listByOrApp_idOrLikeBrand_nameAndLimit(app_id, brand_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public Brand findByBrand_id(String brand_id, String request_app_id, String request_http_id, String request_user_id) {
        return brandCache.findByBrand_id(brand_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String brand_id, String app_id, String category_id, String brand_name, String brand_image, String brand_content, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return brandCache.save(brand_id, app_id, category_id, brand_name, brand_image, brand_content, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String brand_id, String category_id, String brand_name, String brand_image, String brand_content, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return brandCache.updateValidateSystem_version(brand_id, category_id, brand_name, brand_image, brand_content, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByBrand_idAndSystem_update_user_idValidateSystem_version(String brand_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return brandCache.deleteByBrand_idAndSystem_update_user_idValidateSystem_version(brand_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}