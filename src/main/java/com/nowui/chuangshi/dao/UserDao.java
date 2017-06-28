package com.nowui.chuangshi.dao;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.Date;
import java.util.List;

public class UserDao extends Dao {

    private String generatePassword(String user_password) {
        if(ValidateUtil.isNullOrEmpty(user_password)) {
            return "";
        }

        return HashKit.sha512(Config.private_key + user_password);
    }

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("user.countByApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_user", "countByApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("user.countByOrApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_user", "countByOrApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<User> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("user.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_user", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new User().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<User> listByApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("user.listByApp_idAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_user", "listByApp_idAndLimit", sqlPara, request_user_id);

        return new User().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<User> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("user.listByOrApp_idAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_user", "listByOrApp_idAndLimit", sqlPara, request_user_id);

        return new User().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public User findByUser_id(String user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.USER_ID, user_id);
        SqlPara sqlPara = Db.getSqlPara("user.findByUser_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_user", "findByUser_id", sqlPara, request_user_id);

        List<User> userList = new User().find(sqlPara.getSql(), sqlPara.getPara());
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public User findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id(String app_id, String user_type, String wechat_open_id, String wechat_union_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.USER_TYPE, user_type);
        sqlMap.put(User.WECHAT_OPEN_ID, wechat_open_id);
        sqlMap.put(User.WECHAT_UNION_ID, wechat_union_id);
        SqlPara sqlPara = Db.getSqlPara("user.findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_user", "findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id", sqlPara, request_user_id);

        List<User> userList = new User().find(sqlPara.getSql(), sqlPara.getPara());
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public User findByApp_idAndUser_typeAndUser_accountAndUser_password(String app_id, String user_type, String user_account, String user_password, String request_app_id, String request_http_id, String request_user_id) {
        user_password = generatePassword(user_password);

        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.USER_TYPE, user_type);
        sqlMap.put(User.USER_ACCOUNT, user_account);
        sqlMap.put(User.USER_PASSWORD, user_password);
        SqlPara sqlPara = Db.getSqlPara("user.findByApp_idAndUser_typeAndUser_accountAndUser_password", sqlMap);

        logSql(request_app_id, request_http_id, "table_user", "findByApp_idAndUser_typeAndUser_accountAndUser_password", sqlPara, request_user_id);

        List<User> userList = new User().find(sqlPara.getSql(), sqlPara.getPara());
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public Boolean save(String user_id, String app_id, String organization_id, String role_id, String user_level_id, String user_type, String user_account, String user_phone, String user_email, String user_password, String user_name, String user_avatar, String wechat_open_id, String wechat_union_id, String extend_id, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        user_password = generatePassword(user_password);

        Kv sqlMap = Kv.create();
        sqlMap.put(User.USER_ID, user_id);
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.ORGANIZATION_ID, organization_id);
        sqlMap.put(User.ROLE_ID, role_id);
        sqlMap.put(User.USER_LEVEL_ID, user_level_id);
        sqlMap.put(User.USER_TYPE, user_type);
        sqlMap.put(User.USER_ACCOUNT, user_account);
        sqlMap.put(User.USER_PHONE, user_phone);
        sqlMap.put(User.USER_EMAIL, user_email);
        sqlMap.put(User.USER_PASSWORD, user_password);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(User.USER_AVATAR, user_avatar);
        sqlMap.put(User.WECHAT_OPEN_ID, wechat_open_id);
        sqlMap.put(User.WECHAT_UNION_ID, wechat_union_id);
        sqlMap.put(User.EXTEND_ID, extend_id);
        sqlMap.put(User.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(User.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(User.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(User.SYSTEM_VERSION, 0);
        sqlMap.put(User.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("user.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_user", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String user_id, String organization_id, String role_id, String user_level_id, String user_type, String user_account, String user_phone, String user_email, String user_password, String user_name, String user_avatar, String wechat_open_id, String wechat_union_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.USER_ID, user_id);
        sqlMap.put(User.ORGANIZATION_ID, organization_id);
        sqlMap.put(User.ROLE_ID, role_id);
        sqlMap.put(User.USER_LEVEL_ID, user_level_id);
        sqlMap.put(User.USER_TYPE, user_type);
        sqlMap.put(User.USER_ACCOUNT, user_account);
        sqlMap.put(User.USER_PHONE, user_phone);
        sqlMap.put(User.USER_EMAIL, user_email);
        sqlMap.put(User.USER_PASSWORD, user_password);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(User.USER_AVATAR, user_avatar);
        sqlMap.put(User.WECHAT_OPEN_ID, wechat_open_id);
        sqlMap.put(User.WECHAT_UNION_ID, wechat_union_id);
        sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(User.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(User.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("user.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_user", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByUser_idAndSystem_version(String user_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.USER_ID, user_id);
        sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(User.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(User.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("user.deleteByUser_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_user", "deleteBy", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}