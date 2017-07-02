package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Api;

import java.util.Date;
import java.util.List;

public class ApiDao extends Dao {

    public Integer countByApp_idOrLikeApi_name(String app_id, String api_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.APP_ID, app_id);
        sqlMap.put(Api.API_NAME, api_name);
        SqlPara sqlPara = Db.getSqlPara("api.countByApp_idOrLikeApi_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_api", "countByApp_idOrLikeApi_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeApi_name(String app_id, String api_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.APP_ID, app_id);
        sqlMap.put(Api.API_NAME, api_name);
        SqlPara sqlPara = Db.getSqlPara("api.countByOrApp_idOrLikeApi_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_api", "countByOrApp_idOrLikeApi_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Api> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.APP_ID, app_id);
        sqlMap.put(Api.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("api.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_api", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new Api().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Api> listNotInMenuByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("api.listNotInMenuByApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_api", "listNotInMenuByApp_id", sqlPara, request_user_id);

        return new Api().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Api> listByApp_idOrLikeApi_nameAndLimit(String app_id, String api_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.APP_ID, app_id);
        sqlMap.put(Api.API_NAME, api_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("api.listByApp_idOrLikeApi_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_api", "listByApp_idOrLikeApi_nameAndLimit", sqlPara, request_user_id);

        return new Api().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Api> listByOrApp_idOrLikeApi_nameAndLimit(String app_id, String api_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.APP_ID, app_id);
        sqlMap.put(Api.API_NAME, api_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("api.listByOrApp_idOrLikeApi_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_api", "listByOrApp_idOrLikeApi_nameAndLimit", sqlPara, request_user_id);

        return new Api().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Api findByApi_id(String api_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.API_ID, api_id);
        SqlPara sqlPara = Db.getSqlPara("api.findByApi_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_api", "findByApi_id", sqlPara, request_user_id);

        List<Api> apiList = new Api().find(sqlPara.getSql(), sqlPara.getPara());
        if (apiList.size() == 0) {
            return null;
        } else {
            return apiList.get(0);
        }
    }

    public Boolean save(String api_id, String app_id, String api_name, String api_url, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.API_ID, api_id);
        sqlMap.put(Api.APP_ID, app_id);
        sqlMap.put(Api.API_NAME, api_name);
        sqlMap.put(Api.API_URL, api_url);
        sqlMap.put(Api.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Api.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Api.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Api.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Api.SYSTEM_VERSION, 0);
        sqlMap.put(Api.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("api.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_api", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String api_id, String api_name, String api_url, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.API_ID, api_id);
        sqlMap.put(Api.API_NAME, api_name);
        sqlMap.put(Api.API_URL, api_url);
        sqlMap.put(Api.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Api.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Api.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("api.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_api", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByApi_idAndSystem_version(String api_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.API_ID, api_id);
        sqlMap.put(Api.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Api.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Api.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("api.deleteByApi_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_api", "deleteByApi_idAndSystem_version", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}