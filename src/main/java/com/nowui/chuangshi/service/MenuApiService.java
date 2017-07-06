package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.MenuApiCache;
import com.nowui.chuangshi.model.MenuApi;

import java.util.List;

public class MenuApiService extends Service {

    private MenuApiCache menuApiCache = new MenuApiCache();

    public Integer countByMenu_idAndApi_id(String menu_id, String api_id) {
        return menuApiCache.countByMenu_idAndApi_id(menu_id, api_id);
    }

    public List<MenuApi> listByMenu_id(String menu_id) {
        return menuApiCache.listByMenu_id(menu_id);
    }

    public Boolean save(String menu_id, String api_id, Integer menu_api_sort, String system_create_user_id) {
        return menuApiCache.save(menu_id, api_id, menu_api_sort, system_create_user_id);
    }

    public Boolean deleteByMenu_id(String menu_id, String system_update_user_id) {
        return menuApiCache.deleteByMenu_id(menu_id, system_update_user_id);
    }

    public Boolean deleteByMenu_idAndApi_id(String menu_id, String api_id, String system_update_user_id) {
        return menuApiCache.deleteByMenu_idAndApi_id(menu_id, api_id, system_update_user_id);
    }

}