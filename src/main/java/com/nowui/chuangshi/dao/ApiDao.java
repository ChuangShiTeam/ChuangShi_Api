package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Api;

import java.util.Date;
import java.util.List;

public class ApiDao extends Dao {

    public Integer countByApp_idOrLikeApi_name(String app_id, String api_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.APP_ID, app_id);
        sqlMap.put(Api.API_NAME, api_name);
        SqlPara sqlPara = Db.getSqlPara("api.countByApp_idOrLikeApi_name", sqlMap);

        logSql("api", "countByApp_idOrLikeApi_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeApi_name(String app_id, String api_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.APP_ID, app_id);
        sqlMap.put(Api.API_NAME, api_name);
        SqlPara sqlPara = Db.getSqlPara("api.countByOrApp_idOrLikeApi_name", sqlMap);

        logSql("api", "countByOrApp_idOrLikeApi_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Api> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.APP_ID, app_id);
        sqlMap.put(Api.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("api.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("api", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new Api().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Api> listNotInMenuByApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("api.listNotInMenuByApp_id", sqlMap);

        logSql("api", "listNotInMenuByApp_id", sqlPara);

        return new Api().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Api> listByApp_idOrLikeApi_nameAndLimit(String app_id, String api_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.APP_ID, app_id);
        sqlMap.put(Api.API_NAME, api_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("api.listByApp_idOrLikeApi_nameAndLimit", sqlMap);

        logSql("api", "listByApp_idOrLikeApi_nameAndLimit", sqlPara);

        return new Api().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Api> listByOrApp_idOrLikeApi_nameAndLimit(String app_id, String api_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.APP_ID, app_id);
        sqlMap.put(Api.API_NAME, api_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("api.listByOrApp_idOrLikeApi_nameAndLimit", sqlMap);

        logSql("api", "listByOrApp_idOrLikeApi_nameAndLimit", sqlPara);

        return new Api().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Api findByApi_id(String api_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.API_ID, api_id);
        SqlPara sqlPara = Db.getSqlPara("api.findByApi_id", sqlMap);

        logSql("api", "findByApi_id", sqlPara);

        List<Api> apiList = new Api().find(sqlPara.getSql(), sqlPara.getPara());
        if (apiList.size() == 0) {
            return null;
        } else {
            return apiList.get(0);
        }
    }

    public Boolean save(String api_id, String app_id, String api_name, String api_url, String system_create_user_id) {
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

        logSql("api", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String api_id, String api_name, String api_url, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.API_ID, api_id);
        sqlMap.put(Api.API_NAME, api_name);
        sqlMap.put(Api.API_URL, api_url);
        sqlMap.put(Api.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Api.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Api.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("api.update", sqlMap);

        logSql("api", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByApi_idAndSystem_version(String api_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Api.API_ID, api_id);
        sqlMap.put(Api.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Api.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Api.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("api.deleteByApi_idAndSystem_version", sqlMap);

        logSql("api", "deleteByApi_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}