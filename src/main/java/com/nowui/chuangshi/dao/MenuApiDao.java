package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.MenuApi;

import java.util.Date;
import java.util.List;

public class MenuApiDao extends Dao {

    public Integer countByMenu_idAndApi_id(String menu_id, String api_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MenuApi.MENU_ID, menu_id);
        sqlMap.put(MenuApi.API_ID, api_id);
        SqlPara sqlPara = Db.getSqlPara("menu_api.countByApp_idAndApi_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_menu_api", "countByApp_idAndApi_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<MenuApi> listByMenu_id(String menu_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MenuApi.MENU_ID, menu_id);
        SqlPara sqlPara = Db.getSqlPara("menu_api.listByMenu_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_menu_api", "listByMenu_id", sqlPara, request_user_id);

        return new MenuApi().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String menu_id, String api_id, Integer menu_api_sort, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MenuApi.MENU_ID, menu_id);
        sqlMap.put(MenuApi.API_ID, api_id);
        sqlMap.put(MenuApi.MENU_API_SORT, menu_api_sort);
        sqlMap.put(MenuApi.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(MenuApi.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(MenuApi.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(MenuApi.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MenuApi.SYSTEM_VERSION, 0);
        sqlMap.put(MenuApi.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("menu_api.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_menu_api", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMenu_id(String menu_id, String system_update_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MenuApi.MENU_ID, menu_id);
        sqlMap.put(MenuApi.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MenuApi.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("menu_api.deleteByMenu_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_menu_api", "deleteByMenu_id", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMenu_idAndApi_id(String menu_id, String api_id, String system_update_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MenuApi.MENU_ID, menu_id);
        sqlMap.put(MenuApi.API_ID, api_id);
        sqlMap.put(MenuApi.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MenuApi.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("menu_api.deleteByMenu_idAndApi_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_menu_api", "deleteByMenu_idAndApi_id", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}