package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.AppCache;
import com.nowui.chuangshi.model.App;

import java.util.Date;
import java.util.List;

public class AppService extends Service {

    private AppCache appCache = new AppCache();

    public Integer countByApp_idOrLikeApp_name(String app_id, String app_name, String request_app_id, String request_http_id, String request_user_id) {
        return appCache.countByApp_idOrLikeApp_name(app_id, app_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idOrLikeApp_name(String app_id, String app_name, String request_app_id, String request_http_id, String request_user_id) {
        return appCache.countByOrApp_idOrLikeApp_name(app_id, app_name, request_app_id, request_http_id, request_user_id);
    }

    public List<App> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return appCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<App> listByApp_idOrLikeApp_nameAndLimit(String app_id, String app_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return appCache.listByApp_idOrLikeApp_nameAndLimit(app_id, app_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<App> listByOrApp_idOrLikeApp_nameAndLimit(String app_id, String app_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return appCache.listByOrApp_idOrLikeApp_nameAndLimit(app_id, app_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<App> list(String request_app_id, String request_http_id, String request_user_id) {
        return appCache.list(request_app_id, request_http_id, request_user_id);
    }

    public App findByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return appCache.findByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String app_id, String app_name, String app_secret, String wechat_app_id, String wechat_app_secret, String wechat_mch_id, String wechat_mch_key, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return appCache.save(app_id, app_name, app_secret, wechat_app_id, wechat_app_secret, wechat_mch_id, wechat_mch_key, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String app_id, String app_name, String app_secret, String wechat_app_id, String wechat_app_secret, String wechat_mch_id, String wechat_mch_key, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return appCache.updateValidateSystem_version(app_id, app_name, app_secret, wechat_app_id, wechat_app_secret, wechat_mch_id, wechat_mch_key, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByApp_idAndSystem_update_user_idValidateSystem_version(String app_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return appCache.deleteByApp_idAndSystem_update_user_idValidateSystem_version(app_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}