package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.HttpCache;
import com.nowui.chuangshi.model.Http;

import java.util.Date;
import java.util.List;

public class HttpService extends Service {

    private HttpCache httpCache = new HttpCache();

    public Integer countByApp_id(String app_id) {
        return httpCache.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return httpCache.countByOrApp_id(app_id);
    }

    public Integer countByApp_idAndHttp_url(String app_id, String http_url) {
        return httpCache.countByApp_idAndHttp_url(app_id, http_url);
    }

    public List<Http> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return httpCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Http> listByApp_idAndLimit(String app_id, int m, int n) {
        return httpCache.listByApp_idAndLimit(app_id, m, n);
    }

    public List<Http> listByOrApp_idAndLimit(String app_id, int m, int n) {
        return httpCache.listByOrApp_idAndLimit(app_id, m, n);
    }

    public Http findByHttp_id(String http_id) {
        return httpCache.findByHttp_id(http_id);
    }

    public Boolean save(String http_id, String app_id, String http_url, String http_code, String http_request, String http_response, String http_token, String http_platform, String http_version, String http_ip_address, String http_run_time, String system_create_user_id) {
        return httpCache.save(http_id, app_id, http_url, http_code, http_request, http_response, http_token, http_platform, http_version, http_ip_address, http_run_time, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String http_id, String http_url, String http_code, String http_request, String http_response, String http_token, String http_platform, String http_version, String http_ip_address, String http_run_time, String system_update_user_id, Integer system_version) {
        return httpCache.updateValidateSystem_version(http_id, http_url, http_code, http_request, http_response, http_token, http_platform, http_version, http_ip_address, http_run_time, system_update_user_id, system_version);
    }

    public Boolean deleteByHttp_idAndSystem_update_user_idValidateSystem_version(String http_id, String system_update_user_id, Integer system_version) {
        return httpCache.deleteByHttp_idAndSystem_update_user_idValidateSystem_version(http_id, system_update_user_id, system_version);
    }

}