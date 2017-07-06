package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.Menu;

import java.util.Date;
import java.util.List;

public class MenuDao extends Dao {

    public List<Menu> listByApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Menu.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("menu.listByApp_id", sqlMap);

        logSql("menu", "listByApp_id", sqlPara);

        return new Menu().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Menu> listByLikeMenu_parent_id(String menu_parent_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Menu.MENU_PARENT_ID, menu_parent_id);
        SqlPara sqlPara = Db.getSqlPara("menu.listByLikeMenu_parent_id", sqlMap);

        logSql("menu", "listByLikeMenu_parent_id", sqlPara);

        return new Menu().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Menu> listByApp_idOrLikeMenu_name(String app_id, String menu_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Menu.APP_ID, app_id);
        sqlMap.put(Menu.MENU_NAME, menu_name);
        SqlPara sqlPara = Db.getSqlPara("menu.listByApp_idOrLikeMenu_name", sqlMap);

        logSql("menu", "listByApp_idOrLikeMenu_name", sqlPara);

        return new Menu().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Menu> listByOrApp_idOrLikeMenu_name(String app_id, String menu_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Menu.APP_ID, app_id);
        sqlMap.put(Menu.MENU_NAME, menu_name);
        SqlPara sqlPara = Db.getSqlPara("menu.listByOrApp_idOrLikeMenu_name", sqlMap);

        logSql("menu", "listByOrApp_idOrLikeMenu_name", sqlPara);

        return new Menu().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Menu findByMenu_id(String menu_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Menu.MENU_ID, menu_id);
        SqlPara sqlPara = Db.getSqlPara("menu.findByMenu_id", sqlMap);

        logSql("menu", "findByMenu_id", sqlPara);

        List<Menu> menuList = new Menu().find(sqlPara.getSql(), sqlPara.getPara());
        if (menuList.size() == 0) {
            return null;
        } else {
            return menuList.get(0);
        }
    }

    public Boolean save(String menu_id, String app_id, String menu_parent_id, String menu_name, String menu_image, String menu_url, Integer menu_sort, String menu_path, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Menu.MENU_ID, menu_id);
        sqlMap.put(Menu.APP_ID, app_id);
        sqlMap.put(Menu.MENU_PARENT_ID, menu_parent_id);
        sqlMap.put(Menu.MENU_NAME, menu_name);
        sqlMap.put(Menu.MENU_IMAGE, menu_image);
        sqlMap.put(Menu.MENU_URL, menu_url);
        sqlMap.put(Menu.MENU_SORT, menu_sort);
        sqlMap.put(Menu.MENU_PATH, menu_path);
        sqlMap.put(Menu.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Menu.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Menu.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Menu.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Menu.SYSTEM_VERSION, 0);
        sqlMap.put(Menu.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("menu.save", sqlMap);

        logSql("menu", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String menu_id, String menu_parent_id, String menu_name, String menu_image, String menu_url, Integer menu_sort, String menu_path, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Menu.MENU_ID, menu_id);
        sqlMap.put(Menu.MENU_PARENT_ID, menu_parent_id);
        sqlMap.put(Menu.MENU_NAME, menu_name);
        sqlMap.put(Menu.MENU_IMAGE, menu_image);
        sqlMap.put(Menu.MENU_URL, menu_url);
        sqlMap.put(Menu.MENU_SORT, menu_sort);
        sqlMap.put(Menu.MENU_PATH, menu_path);
        sqlMap.put(Menu.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Menu.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Menu.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("menu.update", sqlMap);

        logSql("menu", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMenu_idAndSystem_version(String menu_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Menu.MENU_ID, menu_id);
        sqlMap.put(Menu.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Menu.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Menu.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("menu.deleteByMenu_idAndSystem_version", sqlMap);

        logSql("menu", "deleteByMenu_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMenu_parent_id(String menu_parent_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Menu.MENU_PARENT_ID, menu_parent_id);
        sqlMap.put(Menu.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Menu.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("menu.deleteByMenu_parent_id", sqlMap);

        logSql("menu", "deleteByMenu_parent_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}