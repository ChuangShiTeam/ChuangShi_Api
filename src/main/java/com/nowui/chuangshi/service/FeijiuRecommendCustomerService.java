package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.FeijiuRecommendCustomerCache;
import com.nowui.chuangshi.model.FeijiuRecommendCustomer;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.Date;
import java.util.List;

public class FeijiuRecommendCustomerService extends Service {

    private FeijiuRecommendCustomerCache feijiuRecommendCustomerCache = new FeijiuRecommendCustomerCache();

    public Integer countByApp_idAndCustomer_name(String app_id, String customer_name, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendCustomerCache.countByApp_idAndCustomer_name(app_id, customer_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendCustomerCache.countByOrApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<FeijiuRecommendCustomer> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendCustomerCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<FeijiuRecommendCustomer> listByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendCustomerCache.listByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<FeijiuRecommendCustomer> listByApp_idAndCustomer_nameAndLimit(String app_id, String customer_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendCustomerCache.listByApp_idAndCustomer_nameAndLimit(app_id, customer_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<FeijiuRecommendCustomer> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendCustomerCache.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);
    }

    public FeijiuRecommendCustomer findByCustomer_id(String customer_id, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendCustomerCache.findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String customer_id, String app_id, String customer_name, String customer_phone, String customer_city, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        if (!ValidateUtil.isPhone(customer_phone)) {
            throw new RuntimeException("手机号码不对");
        }

        return feijiuRecommendCustomerCache.save(customer_id, app_id, customer_name, customer_phone, customer_city, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String customer_id, String customer_name, String customer_phone, String customer_city, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendCustomerCache.updateValidateSystem_version(customer_id, customer_name, customer_phone, customer_city, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(String customer_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendCustomerCache.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(customer_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}