package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.MenuDao;
import com.nowui.chuangshi.model.Menu;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MenuCache extends Cache {

    public static final String MENU_BY_MENU_ID_CACHE = "menu_by_menu_id_cache";

    private MenuDao menuDao = new MenuDao();

    public List<Menu> listByApp_id(String app_id) {
        return menuDao.listByApp_id(app_id);
    }

    public List<Menu> listByApp_idOrLikeMenu_name(String app_id, String menu_name) {
        return menuDao.listByApp_idOrLikeMenu_name(app_id, menu_name);
    }

    public List<Menu> listByOrApp_idOrLikeMenu_name(String app_id, String menu_name) {
        List<Menu> menuList = menuDao.listByOrApp_idOrLikeMenu_name(app_id, menu_name);

        for (Menu menu : menuList) {
            menu.put(findByMenu_id(menu.getMenu_id()));
        }

        return menuList;
    }

    public Menu findByMenu_id(String menu_id) {
        Menu menu = CacheUtil.get(MENU_BY_MENU_ID_CACHE, menu_id);

        if (menu == null) {
            menu = menuDao.findByMenu_id(menu_id);

            CacheUtil.put(MENU_BY_MENU_ID_CACHE, menu_id, menu);
        }

        return menu;
    }

    public Boolean save(String menu_id, String app_id, String menu_parent_id, String menu_name, String menu_image, String menu_url, Integer menu_sort, String menu_path, String system_create_user_id) {
        return menuDao.save(menu_id, app_id, menu_parent_id, menu_name, menu_image, menu_url, menu_sort, menu_path, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String menu_id, String menu_parent_id, String menu_name, String menu_image, String menu_url, Integer menu_sort, String menu_path, String system_update_user_id, Integer system_version) {
        Menu menu = findByMenu_id(menu_id);
        if (!menu.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = menuDao.update(menu_id, menu_parent_id, menu_name, menu_image, menu_url, menu_sort, menu_path, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MENU_BY_MENU_ID_CACHE, menu_id);
        }

        return result;
    }

    public Boolean deleteByMenu_idAndSystem_update_user_idValidateSystem_version(String menu_id, String system_update_user_id, Integer system_version) {
        Menu menu = findByMenu_id(menu_id);
        if (!menu.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = menuDao.deleteByMenu_idAndSystem_version(menu_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MENU_BY_MENU_ID_CACHE, menu_id);
        }

        return result;
    }

    public Boolean deleteByMenu_parent_id(String menu_parent_id, String system_update_user_id) {
        List<Menu> menuList = menuDao.listByLikeMenu_parent_id(menu_parent_id);

        boolean result = menuDao.deleteByMenu_parent_id(menu_parent_id, system_update_user_id);

        if (result) {
            for (Menu menu : menuList) {
                CacheUtil.remove(MENU_BY_MENU_ID_CACHE, menu.getMenu_id());
            }
        }

        return result;
    }

}