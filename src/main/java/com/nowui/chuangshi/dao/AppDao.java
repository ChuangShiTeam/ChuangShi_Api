package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.App;

import java.util.Date;
import java.util.List;

public class AppDao extends Dao {

    public Integer countByApp_idOrLikeApp_name(String app_id, String app_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(App.APP_ID, app_id);
        sqlMap.put(App.APP_NAME, app_name);
        SqlPara sqlPara = Db.getSqlPara("app.countByApp_idOrLikeApp_name", sqlMap);

        logSql("app", "countByApp_idOrLikeApp_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeApp_name(String app_id, String app_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(App.APP_ID, app_id);
        sqlMap.put(App.APP_NAME, app_name);
        SqlPara sqlPara = Db.getSqlPara("app.countByOrApp_idOrLikeApp_name", sqlMap);

        logSql("app", "countByOrApp_idOrLikeApp_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<App> list() {
        Kv sqlMap = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("app.list", sqlMap);

//        logSql("app", "list", sqlPara);

        return new App().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<App> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(App.APP_ID, app_id);
        sqlMap.put(App.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("app.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("app", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new App().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<App> listByApp_idOrLikeApp_nameAndLimit(String app_id, String app_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(App.APP_ID, app_id);
        sqlMap.put(App.APP_NAME, app_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("app.listByApp_idOrLikeApp_nameAndLimit", sqlMap);

        logSql("app", "listByApp_idOrLikeApp_nameAndLimit", sqlPara);

        return new App().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<App> listByOrApp_idOrLikeApp_nameAndLimit(String app_id, String app_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(App.APP_ID, app_id);
        sqlMap.put(App.APP_NAME, app_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("app.listByOrApp_idOrLikeApp_nameAndLimit", sqlMap);

        logSql("app", "listByOrApp_idOrLikeApp_nameAndLimit", sqlPara);

        return new App().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public App findByApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(App.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("app.findByApp_id", sqlMap);

        logSql("app", "findByApp_id", sqlPara);

        List<App> appList = new App().find(sqlPara.getSql(), sqlPara.getPara());
        if (appList.size() == 0) {
            return null;
        } else {
            return appList.get(0);
        }
    }

    public Boolean save(String app_id, String app_name, String app_secret, String wechat_app_id, String wechat_app_secret, String wechat_mch_id, String wechat_mch_key, String wechat_token, String wechat_encoding_aes_key, Boolean app_is_create_warehouse, Boolean app_is_delivery, Boolean app_is_audit_member, Boolean app_is_commission, Integer app_commission_level, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(App.APP_ID, app_id);
        sqlMap.put(App.APP_NAME, app_name);
        sqlMap.put(App.APP_SECRET, app_secret);
        sqlMap.put(App.WECHAT_APP_ID, wechat_app_id);
        sqlMap.put(App.WECHAT_APP_SECRET, wechat_app_secret);
        sqlMap.put(App.WECHAT_MCH_ID, wechat_mch_id);
        sqlMap.put(App.WECHAT_MCH_KEY, wechat_mch_key);
        sqlMap.put(App.WECHAT_TOKEN, wechat_token);
        sqlMap.put(App.WECHAT_ENCODING_AES_KEY, wechat_encoding_aes_key);
        sqlMap.put(App.APP_IS_CREATE_WAREHOUSE, app_is_create_warehouse);
        sqlMap.put(App.APP_IS_DELIVERY, app_is_delivery);
        sqlMap.put(App.APP_IS_AUDIT_MEMBER, app_is_audit_member);
        sqlMap.put(App.APP_IS_COMMISSION, app_is_commission);
        sqlMap.put(App.APP_COMMISSION_LEVEL, app_commission_level);
        sqlMap.put(App.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(App.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(App.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(App.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(App.SYSTEM_VERSION, 0);
        sqlMap.put(App.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("app.save", sqlMap);

        logSql("app", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String app_id, String app_name, String app_secret, String wechat_app_id, String wechat_app_secret, String wechat_mch_id, String wechat_mch_key, String wechat_token, String wechat_encoding_aes_key, Boolean app_is_create_warehouse, Boolean app_is_delivery, Boolean app_is_audit_member, Boolean app_is_commission, Integer app_commission_level, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(App.APP_ID, app_id);
        sqlMap.put(App.APP_NAME, app_name);
        sqlMap.put(App.APP_SECRET, app_secret);
        sqlMap.put(App.WECHAT_APP_ID, wechat_app_id);
        sqlMap.put(App.WECHAT_APP_SECRET, wechat_app_secret);
        sqlMap.put(App.WECHAT_MCH_ID, wechat_mch_id);
        sqlMap.put(App.WECHAT_MCH_KEY, wechat_mch_key);
        sqlMap.put(App.WECHAT_TOKEN, wechat_token);
        sqlMap.put(App.WECHAT_ENCODING_AES_KEY, wechat_encoding_aes_key);
        sqlMap.put(App.APP_IS_CREATE_WAREHOUSE, app_is_create_warehouse);
        sqlMap.put(App.APP_IS_DELIVERY, app_is_delivery);
        sqlMap.put(App.APP_IS_AUDIT_MEMBER, app_is_audit_member);
        sqlMap.put(App.APP_IS_COMMISSION, app_is_commission);
        sqlMap.put(App.APP_COMMISSION_LEVEL, app_commission_level);
        sqlMap.put(App.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(App.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(App.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("app.update", sqlMap);

        logSql("app", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean deleteByApp_idAndSystem_version(String app_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(App.APP_ID, app_id);
        sqlMap.put(App.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(App.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(App.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("app.deleteByApp_idAndSystem_version", sqlMap);

        logSql("app", "deleteByApp_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}