package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Admin;
import com.nowui.chuangshi.model.User;

import java.util.Date;
import java.util.List;

public class AdminDao extends Dao {

    public Integer countByApp_idOrLikeUser_name(String app_id, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Admin.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("admin.countByApp_idOrLikeUser_name", sqlMap);

        logSql("admin", "countByApp_idOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeUser_name(String app_id, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Admin.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("admin.countByOrApp_idOrLikeUser_name", sqlMap);

        logSql("admin", "countByOrApp_idOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Admin> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Admin.APP_ID, app_id);
        sqlMap.put(Admin.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("admin.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("admin", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new Admin().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Admin> listByApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Admin.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("admin.listByApp_idOrLikeUser_nameAndLimit", sqlMap);

        logSql("admin", "listByApp_idOrLikeUser_nameAndLimit", sqlPara);

        return new Admin().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Admin> listByOrApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Admin.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("admin.listByOrApp_idOrLikeUser_nameAndLimit", sqlMap);

        logSql("admin", "listByOrApp_idOrLikeUser_nameAndLimit", sqlPara);

        return new Admin().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Admin findByAdmin_id(String admin_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Admin.ADMIN_ID, admin_id);
        SqlPara sqlPara = Db.getSqlPara("admin.findByAdmin_id", sqlMap);

        logSql("admin", "findByAdmin_id", sqlPara);

        List<Admin> adminList = new Admin().find(sqlPara.getSql(), sqlPara.getPara());
        if (adminList.size() == 0) {
            return null;
        } else {
            return adminList.get(0);
        }
    }

    public Boolean save(String admin_id, String app_id, String user_id, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Admin.ADMIN_ID, admin_id);
        sqlMap.put(Admin.APP_ID, app_id);
        sqlMap.put(Admin.USER_ID, user_id);
        sqlMap.put(Admin.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Admin.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Admin.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Admin.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Admin.SYSTEM_VERSION, 0);
        sqlMap.put(Admin.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("admin.save", sqlMap);

        logSql("admin", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String admin_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Admin.ADMIN_ID, admin_id);
        sqlMap.put(Admin.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Admin.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Admin.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("admin.update", sqlMap);

        logSql("admin", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByAdmin_idAndSystem_version(String admin_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Admin.ADMIN_ID, admin_id);
        sqlMap.put(Admin.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Admin.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("admin.deleteByAdmin_idAndSystem_version", sqlMap);

        logSql("admin", "deleteByAdmin_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}