package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.FeijiuRecommendProductCache;
import com.nowui.chuangshi.model.FeijiuRecommendProduct;

import java.util.Date;
import java.util.List;

public class FeijiuRecommendProductService extends Service {

    private FeijiuRecommendProductCache feijiuRecommendProductCache = new FeijiuRecommendProductCache();

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendProductCache.countByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendProductCache.countByOrApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<FeijiuRecommendProduct> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendProductCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<FeijiuRecommendProduct> listByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendProductCache.listByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<FeijiuRecommendProduct> listByApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendProductCache.listByApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<FeijiuRecommendProduct> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendProductCache.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);
    }

    public FeijiuRecommendProduct findByProduct_id(String product_id, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendProductCache.findByProduct_id(product_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String product_id, String app_id, String product_name, String product_image, String product_content, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendProductCache.save(product_id, app_id, product_name, product_image, product_content, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String product_id, String product_name, String product_image, String product_content, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendProductCache.updateValidateSystem_version(product_id, product_name, product_image, product_content, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByProduct_idAndSystem_update_user_idValidateSystem_version(String product_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendProductCache.deleteByProduct_idAndSystem_update_user_idValidateSystem_version(product_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}