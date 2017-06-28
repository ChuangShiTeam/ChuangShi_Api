package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.UserDao;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class UserCache extends Cache {

    public static final String USER_BY_USER_ID_CACHE = "user_by_user_id_cache";

    private UserDao userDao = new UserDao();

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return userDao.countByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return userDao.countByOrApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<User> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<User> userList = userDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);

        for (User user : userList) {
            user.put(findByUser_id(user.getUser_id(), request_app_id, request_http_id, request_user_id));
        }

        return userList;
    }

    public List<User> listByApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<User> userList = userDao.listByApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);

        for (User user : userList) {
            user.put(findByUser_id(user.getUser_id(), request_app_id, request_http_id, request_user_id));
        }

        return userList;
    }

    public List<User> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<User> userList = userDao.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);

        for (User user : userList) {
            user.put(findByUser_id(user.getUser_id(), request_app_id, request_http_id, request_user_id));
        }

        return userList;
    }

    public User findByUser_id(String user_id, String request_app_id, String request_http_id, String request_user_id) {
        User user = CacheUtil.get(USER_BY_USER_ID_CACHE, user_id);

        if (user == null) {
            user = userDao.findByUser_id(user_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(USER_BY_USER_ID_CACHE, user_id, user);
        }

        return user;
    }

    public User findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id(String app_id, String user_type, String wechat_open_id, String wechat_union_id, String request_app_id, String request_http_id, String request_user_id) {
        User user = userDao.findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id(app_id, user_type, wechat_open_id, wechat_union_id, request_app_id, request_http_id, request_user_id);

        if (user != null) {
            CacheUtil.put(USER_BY_USER_ID_CACHE, user.getUser_id(), user);
        }

        return user;
    }

    public User findByApp_idAndUser_typeAndUser_accountAndUser_password(String app_id, String user_type, String user_account, String user_password, String request_app_id, String request_http_id, String request_user_id) {
        User user = userDao.findByApp_idAndUser_typeAndUser_accountAndUser_password(app_id, user_type, user_account, user_password, request_app_id, request_http_id, request_user_id);

        if (user != null) {
            CacheUtil.put(USER_BY_USER_ID_CACHE, user.getUser_id(), user);
        }

        return user;
    }

    public Boolean save(String user_id, String app_id, String organization_id, String role_id, String user_level_id, String user_type, String user_account, String user_phone, String user_email, String user_password, String user_name, String user_avatar, String wechat_open_id, String wechat_union_id, String extend_id, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return userDao.save(user_id, app_id, organization_id, role_id, user_level_id, user_type, user_account, user_phone, user_email, user_password, user_name, user_avatar, wechat_open_id, wechat_union_id, extend_id, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String user_id, String organization_id, String role_id, String user_level_id, String user_type, String user_account, String user_phone, String user_email, String user_password, String user_name, String user_avatar, String wechat_open_id, String wechat_union_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        User user = findByUser_id(user_id, request_app_id, request_http_id, request_user_id);
        if (!user.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = userDao.update(user_id, organization_id, role_id, user_level_id, user_type, user_account, user_phone, user_email, user_password, user_name, user_avatar, wechat_open_id, wechat_union_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(USER_BY_USER_ID_CACHE, user_id);
        }

        return result;
    }

    public Boolean deleteByUser_idAndSystem_update_user_idValidateSystem_version(String user_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        User user = findByUser_id(user_id, request_app_id, request_http_id, request_user_id);
        if (!user.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = userDao.deleteByUser_idAndSystem_version(user_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(USER_BY_USER_ID_CACHE, user_id);
        }

        return result;
    }

}