package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ProductBrandCache;
import com.nowui.chuangshi.model.ProductBrand;

import java.util.Date;
import java.util.List;

public class ProductBrandService extends Service {

    private ProductBrandCache productBrandCache = new ProductBrandCache();

    public Integer countByApp_idOrLikeProduct_brand_name(String app_id, String product_brand_name, String request_app_id, String request_http_id, String request_user_id) {
        return productBrandCache.countByApp_idOrLikeProduct_brand_name(app_id, product_brand_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idOrLikeProduct_brand_name(String app_id, String product_brand_name, String request_app_id, String request_http_id, String request_user_id) {
        return productBrandCache.countByOrApp_idOrLikeProduct_brand_name(app_id, product_brand_name, request_app_id, request_http_id, request_user_id);
    }

    public List<ProductBrand> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return productBrandCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<ProductBrand> listByApp_idOrLikeProduct_brand_nameAndLimit(String app_id, String product_brand_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return productBrandCache.listByApp_idOrLikeProduct_brand_nameAndLimit(app_id, product_brand_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<ProductBrand> listByOrApp_idOrLikeProduct_brand_nameAndLimit(String app_id, String product_brand_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return productBrandCache.listByOrApp_idOrLikeProduct_brand_nameAndLimit(app_id, product_brand_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public ProductBrand findByProduct_brand_id(String product_brand_id, String request_app_id, String request_http_id, String request_user_id) {
        return productBrandCache.findByProduct_brand_id(product_brand_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String product_brand_id, String app_id, String product_brand_name, String product_brand_image, String product_brand_content, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return productBrandCache.save(product_brand_id, app_id, product_brand_name, product_brand_image, product_brand_content, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String product_brand_id, String product_brand_name, String product_brand_image, String product_brand_content, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return productBrandCache.updateValidateSystem_version(product_brand_id, product_brand_name, product_brand_image, product_brand_content, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByProduct_brand_idAndSystem_update_user_idValidateSystem_version(String product_brand_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return productBrandCache.deleteByProduct_brand_idAndSystem_update_user_idValidateSystem_version(product_brand_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}