package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.FeijiuRecommendProductCache;
import com.nowui.chuangshi.model.FeijiuRecommendProduct;

import java.util.Date;
import java.util.List;

public class FeijiuRecommendProductService extends Service {

    private FeijiuRecommendProductCache feijiuRecommendProductCache = new FeijiuRecommendProductCache();

    public Integer countByApp_id(String app_id) {
        return feijiuRecommendProductCache.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return feijiuRecommendProductCache.countByOrApp_id(app_id);
    }

    public List<FeijiuRecommendProduct> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return feijiuRecommendProductCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<FeijiuRecommendProduct> listByApp_id(String app_id) {
        return feijiuRecommendProductCache.listByApp_id(app_id);
    }

    public List<FeijiuRecommendProduct> listByApp_idAndLimit(String app_id, int m, int n) {
        return feijiuRecommendProductCache.listByApp_idAndLimit(app_id, m, n);
    }

    public List<FeijiuRecommendProduct> listByOrApp_idAndLimit(String app_id, int m, int n) {
        return feijiuRecommendProductCache.listByOrApp_idAndLimit(app_id, m, n);
    }

    public FeijiuRecommendProduct findByProduct_id(String product_id) {
        return feijiuRecommendProductCache.findByProduct_id(product_id);
    }

    public Boolean save(String product_id, String app_id, String product_name, String product_image, String product_link, String product_content, String system_create_user_id) {
        return feijiuRecommendProductCache.save(product_id, app_id, product_name, product_image, product_link, product_content, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String product_id, String product_name, String product_image, String product_link, String product_content, String system_update_user_id, Integer system_version) {
        return feijiuRecommendProductCache.updateValidateSystem_version(product_id, product_name, product_image, product_link, product_content, system_update_user_id, system_version);
    }

    public Boolean deleteByProduct_idAndSystem_update_user_idValidateSystem_version(String product_id, String system_update_user_id, Integer system_version) {
        return feijiuRecommendProductCache.deleteByProduct_idAndSystem_update_user_idValidateSystem_version(product_id, system_update_user_id, system_version);
    }

}