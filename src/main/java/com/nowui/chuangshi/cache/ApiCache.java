package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.ApiDao;
import com.nowui.chuangshi.model.Api;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class ApiCache extends Cache {

    public static final String API_BY_API_ID_CACHE = "api_by_api_id_cache";

    private ApiDao apiDao = new ApiDao();

    public Integer countByApp_idOrLikeApi_name(String app_id, String api_name) {
        return apiDao.countByApp_idOrLikeApi_name(app_id, api_name);
    }

    public Integer countByOrApp_idOrLikeApi_name(String app_id, String api_name) {
        return apiDao.countByOrApp_idOrLikeApi_name(app_id, api_name);
    }

    public List<Api> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Api> apiList = apiDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Api api : apiList) {
            api.put(findByApi_id(api.getApi_id()));
        }

        return apiList;
    }

    public List<Api> listNotInMenuByApp_id(String app_id) {
        List<Api> apiList = apiDao.listNotInMenuByApp_id(app_id);

        for (Api api : apiList) {
            api.put(findByApi_id(api.getApi_id()));
        }

        return apiList;
    }

    public List<Api> listByApp_idOrLikeApi_nameAndLimit(String app_id, String api_name, int m, int n) {
        List<Api> apiList = apiDao.listByApp_idOrLikeApi_nameAndLimit(app_id, api_name, m, n);

        for (Api api : apiList) {
            api.put(findByApi_id(api.getApi_id()));
        }

        return apiList;
    }

    public List<Api> listByOrApp_idOrLikeApi_nameAndLimit(String app_id, String api_name, int m, int n) {
        List<Api> apiList = apiDao.listByOrApp_idOrLikeApi_nameAndLimit(app_id, api_name, m, n);

        for (Api api : apiList) {
            api.put(findByApi_id(api.getApi_id()));
        }

        return apiList;
    }

    public Api findByApi_id(String api_id) {
        Api api = CacheUtil.get(API_BY_API_ID_CACHE, api_id);

        if (api == null) {
            api = apiDao.findByApi_id(api_id);

            CacheUtil.put(API_BY_API_ID_CACHE, api_id, api);
        }

        return api;
    }

    public Boolean save(String api_id, String app_id, String api_name, String api_url, String system_create_user_id) {
        return apiDao.save(api_id, app_id, api_name, api_url, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String api_id, String api_name, String api_url, String system_update_user_id, Integer system_version) {
        Api api = findByApi_id(api_id);
        if (!api.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = apiDao.update(api_id, api_name, api_url, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(API_BY_API_ID_CACHE, api_id);
        }

        return result;
    }

    public Boolean deleteByApi_idAndSystem_update_user_idValidateSystem_version(String api_id, String system_update_user_id, Integer system_version) {
        Api api = findByApi_id(api_id);
        if (!api.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = apiDao.deleteByApi_idAndSystem_version(api_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(API_BY_API_ID_CACHE, api_id);
        }

        return result;
    }

}