package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.UserLevel;

import java.util.Date;
import java.util.List;

public class UserLevelDao extends Dao {

    public Integer countByApp_idOrLikeUser_level_name(String app_id, String user_level_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(UserLevel.APP_ID, app_id);
        sqlMap.put(UserLevel.USER_LEVEL_NAME, user_level_name);
        SqlPara sqlPara = Db.getSqlPara("user_level.countByApp_idOrLikeUser_level_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_user_level", "countByApp_idOrLikeUser_level_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeUser_level_name(String app_id, String user_level_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(UserLevel.APP_ID, app_id);
        sqlMap.put(UserLevel.USER_LEVEL_NAME, user_level_name);
        SqlPara sqlPara = Db.getSqlPara("user_level.countByOrApp_idOrLikeUser_level_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_user_level", "countByOrApp_idOrLikeUser_level_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<UserLevel> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(UserLevel.APP_ID, app_id);
        sqlMap.put(UserLevel.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("user_level.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_user_level", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new UserLevel().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<UserLevel> listByApp_idOrLikeUser_level_nameAndLimit(String app_id, String user_level_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(UserLevel.APP_ID, app_id);
        sqlMap.put(UserLevel.USER_LEVEL_NAME, user_level_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("user_level.listByApp_idOrLikeUser_level_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_user_level", "listByApp_idOrLikeUser_level_nameAndLimit", sqlPara, request_user_id);

        return new UserLevel().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<UserLevel> listByOrApp_idOrLikeUser_level_nameAndLimit(String app_id, String user_level_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(UserLevel.APP_ID, app_id);
        sqlMap.put(UserLevel.USER_LEVEL_NAME, user_level_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("user_level.listByOrApp_idOrLikeUser_level_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_user_level", "listByOrApp_idOrLikeUser_level_nameAndLimit", sqlPara, request_user_id);

        return new UserLevel().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public UserLevel findByUser_level_id(String user_level_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(UserLevel.USER_LEVEL_ID, user_level_id);
        SqlPara sqlPara = Db.getSqlPara("user_level.findByUser_level_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_user_level", "findByUser_level_id", sqlPara, request_user_id);

        List<UserLevel> user_levelList = new UserLevel().find(sqlPara.getSql(), sqlPara.getPara());
        if (user_levelList.size() == 0) {
            return null;
        } else {
            return user_levelList.get(0);
        }
    }

    public Boolean save(String user_level_id, String app_id, String user_level_name, Integer user_level_value, Integer user_level_sort, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(UserLevel.USER_LEVEL_ID, user_level_id);
        sqlMap.put(UserLevel.APP_ID, app_id);
        sqlMap.put(UserLevel.USER_LEVEL_NAME, user_level_name);
        sqlMap.put(UserLevel.USER_LEVEL_VALUE, user_level_value);
        sqlMap.put(UserLevel.USER_LEVEL_SORT, user_level_sort);
        sqlMap.put(UserLevel.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(UserLevel.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(UserLevel.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(UserLevel.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(UserLevel.SYSTEM_VERSION, 0);
        sqlMap.put(UserLevel.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("user_level.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_user_level", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String user_level_id, String user_level_name, Integer user_level_value, Integer user_level_sort, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(UserLevel.USER_LEVEL_ID, user_level_id);
        sqlMap.put(UserLevel.USER_LEVEL_NAME, user_level_name);
        sqlMap.put(UserLevel.USER_LEVEL_VALUE, user_level_value);
        sqlMap.put(UserLevel.USER_LEVEL_SORT, user_level_sort);
        sqlMap.put(UserLevel.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(UserLevel.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(UserLevel.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("user_level.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_user_level", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByUser_level_idAndSystem_version(String user_level_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(UserLevel.USER_LEVEL_ID, user_level_id);
        sqlMap.put(UserLevel.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(UserLevel.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(UserLevel.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("user_level.deleteByUser_level_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_user_level", "deleteBy", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}