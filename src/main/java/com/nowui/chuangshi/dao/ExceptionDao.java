package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Exception;

import java.util.Date;
import java.util.List;

public class ExceptionDao extends Dao {

    public Integer countByApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Exception.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("exception.countByApp_id", sqlMap);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Exception.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("exception.countByOrApp_id", sqlMap);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Exception> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Exception.APP_ID, app_id);
        sqlMap.put(Exception.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("exception.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        return new Exception().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Exception> listByApp_idAndLimit(String app_id, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Exception.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("exception.listByApp_idAndLimit", sqlMap);

        return new Exception().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Exception> listByOrApp_idAndLimit(String app_id, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Exception.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("exception.listByOrApp_idAndLimit", sqlMap);

        return new Exception().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Exception findByException_id(String exception_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Exception.EXCEPTION_ID, exception_id);
        SqlPara sqlPara = Db.getSqlPara("exception.findByException_id", sqlMap);

        List<Exception> exceptionList = new Exception().find(sqlPara.getSql(), sqlPara.getPara());
        if (exceptionList.size() == 0) {
            return null;
        } else {
            return exceptionList.get(0);
        }
    }

    public Boolean save(String exception_id, String app_id, String http_id, String exception_content, Boolean exception_is_confirm, String exception_remark, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Exception.EXCEPTION_ID, exception_id);
        sqlMap.put(Exception.APP_ID, app_id);
        sqlMap.put(Exception.HTTP_ID, http_id);
        sqlMap.put(Exception.EXCEPTION_CONTENT, exception_content);
        sqlMap.put(Exception.EXCEPTION_IS_CONFIRM, exception_is_confirm);
        sqlMap.put(Exception.EXCEPTION_REMARK, exception_remark);
        sqlMap.put(Exception.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Exception.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Exception.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Exception.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Exception.SYSTEM_VERSION, 0);
        sqlMap.put(Exception.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("exception.save", sqlMap);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String http_id, String exception_content, Boolean exception_is_confirm, String exception_remark, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Exception.HTTP_ID, http_id);
        sqlMap.put(Exception.EXCEPTION_CONTENT, exception_content);
        sqlMap.put(Exception.EXCEPTION_IS_CONFIRM, exception_is_confirm);
        sqlMap.put(Exception.EXCEPTION_REMARK, exception_remark);
        sqlMap.put(Exception.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Exception.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Exception.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("exception.update", sqlMap);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByException_idAndSystem_version(String exception_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Exception.HTTP_ID, exception_id);
        sqlMap.put(Exception.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Exception.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Exception.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("exception.deleteByException_idAndSystem_version", sqlMap);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}