package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Http;

import java.util.Date;
import java.util.List;

public class HttpDao extends Dao {

    public Integer countByApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Http.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("http.countByApp_id", sqlMap);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Http.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("http.countByOrApp_id", sqlMap);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByApp_idAndHttp_url(String app_id, String http_url) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Http.APP_ID, app_id);
        sqlMap.put(Http.HTTP_URL, http_url);
        SqlPara sqlPara = Db.getSqlPara("http.countByApp_idAndHttp_url", sqlMap);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Http> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Http.APP_ID, app_id);
        sqlMap.put(Http.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("http.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        return new Http().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Http> listByApp_idAndLimit(String app_id, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Http.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("http.listByApp_idAndLimit", sqlMap);

        return new Http().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Http> listByOrApp_idAndLimit(String app_id, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Http.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("http.listByOrApp_idAndLimit", sqlMap);

        return new Http().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Http findByHttp_id(String http_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Http.HTTP_ID, http_id);
        SqlPara sqlPara = Db.getSqlPara("http.findByHttp_id", sqlMap);

        List<Http> httpList = new Http().find(sqlPara.getSql(), sqlPara.getPara());
        if (httpList.size() == 0) {
            return null;
        } else {
            return httpList.get(0);
        }
    }

    public Boolean save(String http_id, String app_id, String http_url, String http_code, String http_request, String http_response, String http_token, String http_platform, String http_version, String http_ip_address, String http_run_time, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Http.HTTP_ID, http_id);
        sqlMap.put(Http.APP_ID, app_id);
        sqlMap.put(Http.HTTP_URL, http_url);
        sqlMap.put(Http.HTTP_CODE, http_code);
        sqlMap.put(Http.HTTP_REQUEST, http_request);
        sqlMap.put(Http.HTTP_RESPONSE, http_response);
        sqlMap.put(Http.HTTP_TOKEN, http_token);
        sqlMap.put(Http.HTTP_PLATFORM, http_platform);
        sqlMap.put(Http.HTTP_VERSION, http_version);
        sqlMap.put(Http.HTTP_IP_ADDRESS, http_ip_address);
        sqlMap.put(Http.HTTP_RUN_TIME, http_run_time);
        sqlMap.put(Http.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Http.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Http.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Http.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Http.SYSTEM_VERSION, 0);
        sqlMap.put(Http.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("http.save", sqlMap);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String http_id, String http_url, String http_code, String http_request, String http_response, String http_token, String http_platform, String http_version, String http_ip_address, String http_run_time, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Http.HTTP_ID, http_id);
        sqlMap.put(Http.HTTP_URL, http_url);
        sqlMap.put(Http.HTTP_CODE, http_code);
        sqlMap.put(Http.HTTP_REQUEST, http_request);
        sqlMap.put(Http.HTTP_RESPONSE, http_response);
        sqlMap.put(Http.HTTP_TOKEN, http_token);
        sqlMap.put(Http.HTTP_PLATFORM, http_platform);
        sqlMap.put(Http.HTTP_VERSION, http_version);
        sqlMap.put(Http.HTTP_IP_ADDRESS, http_ip_address);
        sqlMap.put(Http.HTTP_RUN_TIME, http_run_time);
        sqlMap.put(Http.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Http.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Http.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("http.update", sqlMap);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByHttp_idAndSystem_version(String http_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Http.HTTP_ID, http_id);
        sqlMap.put(Http.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Http.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Http.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("http.deleteByHttp_idAndSystem_version", sqlMap);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}