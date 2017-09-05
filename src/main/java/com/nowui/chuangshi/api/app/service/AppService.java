package com.nowui.chuangshi.api.app.service;

import com.nowui.chuangshi.api.app.dao.AppDao;
import com.nowui.chuangshi.api.app.model.App;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class AppService extends Service {

    public static final AppService instance = new AppService();
    private final String APP_ITEM_CACHE = "app_item_cache";
    private final AppDao appDao = new AppDao();

    public Integer adminCount(String app_id, String app_name) {
        Cnd cnd = Cnd.where(App.SYSTEM_STATUS, true);
        cnd.and(App.APP_ID, app_id);
        cnd.andAllowEmpty(App.APP_NAME, app_name);

        Integer count = appDao.count(cnd);
        return count;
    }

    public List<App> adminList(String app_id, String app_name, Integer m, Integer n) {
        Cnd cnd = Cnd.where(App.SYSTEM_STATUS, true);
        cnd.and(App.APP_ID, app_id);
        cnd.andAllowEmpty(App.APP_NAME, app_name);
        cnd.paginate(m, n);

        List<App> appList = appDao.primaryKeyList(cnd);
        for (App app : appList) {
            app.put(find(app.getApp_id()));
        }
        return appList;
    }

    public List<App> allList() {
        Cnd cnd = Cnd.where(App.SYSTEM_STATUS, true);

        List<App> appList = appDao.primaryKeyList(cnd);
        for (App app : appList) {
            app.put(find(app.getApp_id()));
        }
        return appList;
    }

    public App find(String app_id) {
        App app = CacheUtil.get(APP_ITEM_CACHE, app_id);

        if (app == null) {
            app = appDao.find(app_id);

            CacheUtil.put(APP_ITEM_CACHE, app_id, app);
        }

        return app;
    }

    public Boolean save(App app) {
        Boolean success = appDao.save(app);
        return success;
    }

    public Boolean update(App app, String app_id, Integer system_version) {
        Cnd cnd = Cnd.where(App.SYSTEM_STATUS, true);
        cnd.and(App.APP_ID, app_id);
        cnd.and(App.SYSTEM_VERSION, system_version);

        Boolean success = appDao.update(app, cnd);

        if (success) {
            CacheUtil.remove(APP_ITEM_CACHE, app_id);
        }

        return success;
    }

    public Boolean delete(String app_id, Integer system_version) {
        Cnd cnd = Cnd.where(App.SYSTEM_STATUS, true);
        cnd.and(App.APP_ID, app_id);
        cnd.and(App.SYSTEM_VERSION, system_version);

        Boolean success = appDao.delete(cnd);

        if (success) {
            CacheUtil.remove(APP_ITEM_CACHE, app_id);
        }

        return success;
    }

}