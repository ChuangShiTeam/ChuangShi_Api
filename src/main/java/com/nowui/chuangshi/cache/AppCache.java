package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.AppDao;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class AppCache extends Cache {

    public static final String APP_BY_APP_ID_CACHE = "app_by_app_id_cache";

    private AppDao appDao = new AppDao();

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return appDao.countByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return appDao.countByOrApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<App> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<App> appList = appDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);

        for (App app : appList) {
            app.put(findByApp_id(app.getApp_id(), request_app_id, request_http_id, request_user_id));
        }

        return appList;
    }

    public List<App> listByApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<App> appList = appDao.listByApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);

        for (App app : appList) {
            app.put(findByApp_id(app.getApp_id(), request_app_id, request_http_id, request_user_id));
        }

        return appList;
    }

    public List<App> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<App> appList = appDao.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);

        for (App app : appList) {
            app.put(findByApp_id(app.getApp_id(), request_app_id, request_http_id, request_user_id));
        }

        return appList;
    }

    public List<App> list(String request_app_id, String request_http_id, String request_user_id) {
        List<App> appList = appDao.list(request_app_id, request_http_id, request_user_id);

        for (App app : appList) {
            app.put(findByApp_id(app.getApp_id(), request_app_id, request_http_id, request_user_id));
        }

        return appList;
    }

    public App findByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        App app = CacheUtil.get(APP_BY_APP_ID_CACHE, app_id);

        if (app == null) {
            app = appDao.findByApp_id(app_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(APP_BY_APP_ID_CACHE, app_id, app);
        }

        return app;
    }

    public Boolean save(String app_id, String app_name, String app_secret, String wechat_app_id, String wechat_app_secret, String wechat_mch_id, String wechat_mch_key, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return appDao.save(app_id, app_name, app_secret, wechat_app_id, wechat_app_secret, wechat_mch_id, wechat_mch_key, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String app_id, String app_name, String app_secret, String wechat_app_id, String wechat_app_secret, String wechat_mch_id, String wechat_mch_key, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        App app = findByApp_id(app_id, request_app_id, request_http_id, request_user_id);
        if (!app.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = appDao.update(app_id, app_name, app_secret, wechat_app_id, wechat_app_secret, wechat_mch_id, wechat_mch_key, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(APP_BY_APP_ID_CACHE, app_id);
        }

        return result;
    }

    public Boolean deleteByApp_idAndSystem_update_user_idValidateSystem_version(String app_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        App app = findByApp_id(app_id, request_app_id, request_http_id, request_user_id);
        if (!app.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = appDao.deleteByApp_idAndSystem_version(app_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(APP_BY_APP_ID_CACHE, app_id);
        }

        return result;
    }

}