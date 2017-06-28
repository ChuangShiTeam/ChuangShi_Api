package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ApiCache;
import com.nowui.chuangshi.model.Api;

import java.util.Date;
import java.util.List;

public class ApiService extends Service {

    private ApiCache apiCache = new ApiCache();

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return apiCache.countByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return apiCache.countByOrApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<Api> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return apiCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<Api> listUnusedByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return apiCache.listUnusedByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<Api> listByApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return apiCache.listByApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<Api> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return apiCache.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);
    }

    public Api findByApi_id(String api_id, String request_app_id, String request_http_id, String request_user_id) {
        return apiCache.findByApi_id(api_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String api_id, String app_id, String api_name, String api_url, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return apiCache.save(api_id, app_id, api_name, api_url, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String api_id, String api_name, String api_url, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return apiCache.updateValidateSystem_version(api_id, api_name, api_url, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByApi_idAndSystem_update_user_idValidateSystem_version(String api_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return apiCache.deleteByApi_idAndSystem_update_user_idValidateSystem_version(api_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}