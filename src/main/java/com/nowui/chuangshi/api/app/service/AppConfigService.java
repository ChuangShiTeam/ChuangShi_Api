package com.nowui.chuangshi.api.app.service;

import com.nowui.chuangshi.api.app.dao.AppConfigDao;
import com.nowui.chuangshi.api.app.model.AppConfig;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class AppConfigService extends Service {

    public static final AppConfigService instance = new AppConfigService();
    private final String APP_CONFIG_ITEM_CACHE = "app_config_item_cache";
    private final AppConfigDao appConfigDao = new AppConfigDao();

    public Integer adminCount(String app_id, String config_key) {
        Cnd cnd = new Cnd();
        cnd.where(AppConfig.SYSTEM_STATUS, true);
        cnd.and(AppConfig.APP_ID, app_id);
        cnd.andAllowEmpty(AppConfig.CONFIG_KEY, config_key);

        Integer count = appConfigDao.count(cnd);
        return count;
    }

    public List<AppConfig> adminList(String app_id, String config_key, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(AppConfig.SYSTEM_STATUS, true);
        cnd.and(AppConfig.APP_ID, app_id);
        cnd.andAllowEmpty(AppConfig.CONFIG_KEY, config_key);
        cnd.paginate(m, n);

        List<AppConfig> app_configList = appConfigDao.primaryKeyList(cnd);
        for (AppConfig app_config : app_configList) {
            app_config.put(find(app_config.getConfig_id()));
        }
        return app_configList;
    }

    public AppConfig find(String config_id) {
        AppConfig app_config = CacheUtil.get(APP_CONFIG_ITEM_CACHE, config_id);

        if (app_config == null) {
            app_config = appConfigDao.find(config_id);

            CacheUtil.put(APP_CONFIG_ITEM_CACHE, config_id, app_config);
        }

        return app_config;
    }

    public Boolean save(AppConfig app_config, String system_create_user_id) {
        Boolean success = appConfigDao.save(app_config, system_create_user_id);
        return success;
    }

    public Boolean update(AppConfig app_config, String config_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(AppConfig.SYSTEM_STATUS, true);
        cnd.and(AppConfig.CONFIG_ID, config_id);
        cnd.and(AppConfig.SYSTEM_VERSION, system_version);

        Boolean success = appConfigDao.update(app_config, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(APP_CONFIG_ITEM_CACHE, config_id);
        }

        return success;
    }

    public Boolean delete(String config_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(AppConfig.SYSTEM_STATUS, true);
        cnd.and(AppConfig.CONFIG_ID, config_id);
        cnd.and(AppConfig.SYSTEM_VERSION, system_version);

        Boolean success = appConfigDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(APP_CONFIG_ITEM_CACHE, config_id);
        }

        return success;
    }

}