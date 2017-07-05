package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.dao.MenuApiDao;
import com.nowui.chuangshi.model.MenuApi;

import java.util.List;

public class MenuApiCache extends Cache {

    private MenuApiDao menuApiDao = new MenuApiDao();

    public Integer countByMenu_idAndApi_id(String menu_id, String api_id) {
        return menuApiDao.countByMenu_idAndApi_id(menu_id, api_id);
    }

    public List<MenuApi> listByMenu_id(String menu_id) {
        return menuApiDao.listByMenu_id(menu_id);
    }

    public Boolean save(String menu_id, String api_id, Integer menu_api_sort, String system_create_user_id) {
        return menuApiDao.save(menu_id, api_id, menu_api_sort, system_create_user_id);
    }

    public Boolean deleteByMenu_id(String menu_id, String system_update_user_id) {
        return menuApiDao.deleteByMenu_id(menu_id, system_update_user_id);
    }

    public Boolean deleteByMenu_idAndApi_id(String menu_id, String api_id, String system_update_user_id) {
        return menuApiDao.deleteByMenu_idAndApi_id(menu_id, api_id, system_update_user_id);
    }

}