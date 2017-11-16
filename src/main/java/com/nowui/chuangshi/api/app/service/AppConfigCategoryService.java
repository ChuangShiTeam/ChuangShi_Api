package com.nowui.chuangshi.api.app.service;

import com.nowui.chuangshi.api.app.dao.AppConfigCategoryDao;
import com.nowui.chuangshi.api.app.model.AppConfigCategory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class AppConfigCategoryService extends Service {

    public static final AppConfigCategoryService instance = new AppConfigCategoryService();
    private final String APP_CONFIG_CATEGORY_ITEM_CACHE = "app_config_category_item_cache";
    private final AppConfigCategoryDao appConfigCategoryDao = new AppConfigCategoryDao();

    public Integer adminCount(String app_id, String config_category_name, String config_category_code) {
        Cnd cnd = new Cnd();
        cnd.where(AppConfigCategory.SYSTEM_STATUS, true);
        cnd.and(AppConfigCategory.APP_ID, app_id);
        cnd.andAllowEmpty(AppConfigCategory.CONFIG_CATEGORY_NAME, config_category_name);
        cnd.andAllowEmpty(AppConfigCategory.CONFIG_CATEGORY_CODE, config_category_code);

        Integer count = appConfigCategoryDao.count(cnd);
        return count;
    }

    public List<AppConfigCategory> adminList(String app_id, String config_category_name, String config_category_code, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(AppConfigCategory.SYSTEM_STATUS, true);
        cnd.and(AppConfigCategory.APP_ID, app_id);
        cnd.andAllowEmpty(AppConfigCategory.CONFIG_CATEGORY_NAME, config_category_name);
        cnd.andAllowEmpty(AppConfigCategory.CONFIG_CATEGORY_CODE, config_category_code);
        cnd.paginate(m, n);

        List<AppConfigCategory> app_config_categoryList = appConfigCategoryDao.primaryKeyList(cnd);
        for (AppConfigCategory app_config_category : app_config_categoryList) {
            app_config_category.put(find(app_config_category.getConfig_category_id()));
        }
        return app_config_categoryList;
    }
    
    public List<AppConfigCategory> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(AppConfigCategory.SYSTEM_STATUS, true);
        cnd.and(AppConfigCategory.APP_ID, app_id);
        
        List<AppConfigCategory> app_config_categoryList = appConfigCategoryDao.primaryKeyList(cnd);
        for (AppConfigCategory app_config_category : app_config_categoryList) {
            app_config_category.put(find(app_config_category.getConfig_category_id()));
        }
        return app_config_categoryList;
    }

    public AppConfigCategory find(String config_category_id) {
        AppConfigCategory app_config_category = CacheUtil.get(APP_CONFIG_CATEGORY_ITEM_CACHE, config_category_id);

        if (app_config_category == null) {
            app_config_category = appConfigCategoryDao.find(config_category_id);

            CacheUtil.put(APP_CONFIG_CATEGORY_ITEM_CACHE, config_category_id, app_config_category);
        }

        return app_config_category;
    }

    public Boolean save(AppConfigCategory app_config_category, String system_create_user_id) {
        Boolean success = appConfigCategoryDao.save(app_config_category, system_create_user_id);
        return success;
    }

    public Boolean update(AppConfigCategory app_config_category, String config_category_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(AppConfigCategory.SYSTEM_STATUS, true);
        cnd.and(AppConfigCategory.CONFIG_CATEGORY_ID, config_category_id);
        cnd.and(AppConfigCategory.SYSTEM_VERSION, system_version);

        Boolean success = appConfigCategoryDao.update(app_config_category, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(APP_CONFIG_CATEGORY_ITEM_CACHE, config_category_id);
        }

        return success;
    }

    public Boolean delete(String config_category_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(AppConfigCategory.SYSTEM_STATUS, true);
        cnd.and(AppConfigCategory.CONFIG_CATEGORY_ID, config_category_id);
        cnd.and(AppConfigCategory.SYSTEM_VERSION, system_version);

        Boolean success = appConfigCategoryDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(APP_CONFIG_CATEGORY_ITEM_CACHE, config_category_id);
        }

        return success;
    }

}