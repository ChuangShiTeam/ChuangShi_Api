package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.HttpDao;
import com.nowui.chuangshi.model.Http;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class HttpCache extends Cache {

    public static final String HTTP_BY_HTTP_ID_CACHE = "http_by_http_id_cache";

    private HttpDao httpDao = new HttpDao();

    public Integer countByApp_id(String app_id) {
        return httpDao.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return httpDao.countByOrApp_id(app_id);
    }

    public Integer countByApp_idAndHttp_url(String app_id, String http_url) {
        return httpDao.countByApp_idAndHttp_url(app_id, http_url);
    }

    public List<Http> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Http> httpList = httpDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Http http : httpList) {
            http.put(findByHttp_id(http.getHttp_id()));
        }

        return httpList;
    }

    public List<Http> listByApp_idAndLimit(String app_id, int m, int n) {
        List<Http> httpList = httpDao.listByApp_idAndLimit(app_id, m, n);

        for (Http http : httpList) {
            http.put(findByHttp_id(http.getHttp_id()));
        }

        return httpList;
    }

    public List<Http> listByOrApp_idAndLimit(String app_id, int m, int n) {
        List<Http> httpList = httpDao.listByOrApp_idAndLimit(app_id, m, n);

        for (Http http : httpList) {
            http.put(findByHttp_id(http.getHttp_id()));
        }

        return httpList;
    }

    public Http findByHttp_id(String http_id) {
        Http http = CacheUtil.get(HTTP_BY_HTTP_ID_CACHE, http_id);

        if (http == null) {
            http = httpDao.findByHttp_id(http_id);

            CacheUtil.put(HTTP_BY_HTTP_ID_CACHE, http_id, http);
        }

        return http;
    }

    public Boolean save(String http_id, String app_id, String http_url, String http_code, String http_request, String http_response, String http_token, String http_platform, String http_version, String http_ip_address, String http_run_time, String system_create_user_id) {
        return httpDao.save(http_id, app_id, http_url, http_code, http_request, http_response, http_token, http_platform, http_version, http_ip_address, http_run_time, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String http_id, String http_url, String http_code, String http_request, String http_response, String http_token, String http_platform, String http_version, String http_ip_address, String http_run_time, String system_update_user_id, Integer system_version) {
        Http http = findByHttp_id(http_id);
        if (!http.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = httpDao.update(http_id, http_url, http_code, http_request, http_response, http_token, http_platform, http_version, http_ip_address, http_run_time, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(HTTP_BY_HTTP_ID_CACHE, http_id);
        }

        return result;
    }

    public Boolean deleteByHttp_idAndSystem_update_user_idValidateSystem_version(String http_id, String system_update_user_id, Integer system_version) {
        Http http = findByHttp_id(http_id);
        if (!http.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = httpDao.deleteByHttp_idAndSystem_version(http_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(HTTP_BY_HTTP_ID_CACHE, http_id);
        }

        return result;
    }

}