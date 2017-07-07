package com.nowui.chuangshi.dao;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.util.Util;
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

    public Integer countByApp_idAndNotUser_idAndUser_account(String user_id, String app_id, String user_account) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.USER_ACCOUNT, user_account);
        SqlPara sqlPara = Db.getSqlPara("user.countByApp_idAndNotUser_idAndUser_account", sqlMap);

        logSql("user", "countByApp_idAndNotUser_idAndUser_account", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByApp_idOrLikeUser_name(String app_id, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("user.countByApp_idOrLikeUser_name", sqlMap);

        logSql("user", "countByApp_idOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeUser_name(String app_id, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("user.countByOrApp_idOrLikeUser_name", sqlMap);

        logSql("user", "countByOrApp_idOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<User> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("user.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("user", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new User().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<User> listByApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("user.listByApp_idOrLikeUser_nameAndLimit", sqlMap);

        logSql("user", "listByApp_idOrLikeUser_nameAndLimit", sqlPara);

        return new User().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<User> listByOrApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("user.listByOrApp_idOrLikeUser_nameAndLimit", sqlMap);

        logSql("user", "listByOrApp_idOrLikeUser_nameAndLimit", sqlPara);

        return new User().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public User findByUser_id(String user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.USER_ID, user_id);
        SqlPara sqlPara = Db.getSqlPara("user.findByUser_id", sqlMap);

        logSql("user", "findByUser_id", sqlPara);

        List<User> userList = new User().find(sqlPara.getSql(), sqlPara.getPara());
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public User findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id(String app_id, String user_type, String wechat_open_id, String wechat_union_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.USER_TYPE, user_type);
        sqlMap.put(User.WECHAT_OPEN_ID, wechat_open_id);
        sqlMap.put(User.WECHAT_UNION_ID, wechat_union_id);
        SqlPara sqlPara = Db.getSqlPara("user.findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id", sqlMap);

        logSql("user", "findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id", sqlPara);

        List<User> userList = new User().find(sqlPara.getSql(), sqlPara.getPara());
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public User findByApp_idAndUser_typeAndUser_accountAndUser_password(String app_id, String user_type, String user_account, String user_password) {
        user_password = generatePassword(user_password);

        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.USER_TYPE, user_type);
        sqlMap.put(User.USER_ACCOUNT, user_account);
        sqlMap.put(User.USER_PASSWORD, user_password);
        SqlPara sqlPara = Db.getSqlPara("user.findByApp_idAndUser_typeAndUser_accountAndUser_password", sqlMap);

        logSql("user", "findByApp_idAndUser_typeAndUser_accountAndUser_password", sqlPara);

        List<User> userList = new User().find(sqlPara.getSql(), sqlPara.getPara());
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public Boolean save(String user_id, String app_id, String object_id, String user_type, String user_name, String user_avatar, String user_account, String user_mobile, String user_email, String user_password, String wechat_open_id, String wechat_union_id, String system_create_user_id) {
        user_name = Util.getEmoji(user_name);
        user_password = generatePassword(user_password);

        Kv sqlMap = Kv.create();
        sqlMap.put(User.USER_ID, user_id);
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.OBJECT_ID, object_id);
        sqlMap.put(User.USER_TYPE, user_type);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(User.USER_AVATAR, user_avatar);
        sqlMap.put(User.USER_ACCOUNT, user_account);
        sqlMap.put(User.USER_MOBILE, user_mobile);
        sqlMap.put(User.USER_EMAIL, user_email);
        sqlMap.put(User.USER_PASSWORD, user_password);
        sqlMap.put(User.WECHAT_OPEN_ID, wechat_open_id);
        sqlMap.put(User.WECHAT_UNION_ID, wechat_union_id);
        sqlMap.put(User.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(User.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(User.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(User.SYSTEM_VERSION, 0);
        sqlMap.put(User.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("user.save", sqlMap);

        logSql("user", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean updateByUser_password(String user_id, String user_password, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.USER_ID, user_id);
        sqlMap.put(User.USER_PASSWORD, user_password);
        sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(User.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(User.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("user.updateByUser_password", sqlMap);

        logSql("user", "updateByUser_password", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByUser_idAndSystem_version(String user_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.USER_ID, user_id);
        sqlMap.put(User.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(User.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(User.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("user.deleteByUser_idAndSystem_version", sqlMap);

        logSql("user", "deleteByUser_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}