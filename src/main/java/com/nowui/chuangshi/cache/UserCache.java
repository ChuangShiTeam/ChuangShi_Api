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

    public Integer countByApp_idAndNotUser_idAndUser_account(String user_id, String app_id, String user_account) {
        return userDao.countByApp_idAndNotUser_idAndUser_account(user_id, app_id, user_account);
    }

    public Integer countByApp_idOrLikeUser_name(String app_id, String user_name) {
        return userDao.countByApp_idOrLikeUser_name(app_id, user_name);
    }

    public Integer countByOrApp_idOrLikeUser_name(String app_id, String user_name) {
        return userDao.countByOrApp_idOrLikeUser_name(app_id, user_name);
    }

    public List<User> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<User> userList = userDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (User user : userList) {
            user.put(findByUser_id(user.getUser_id()));
        }

        return userList;
    }

    public List<User> listByApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        List<User> userList = userDao.listByApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);

        for (User user : userList) {
            user.put(findByUser_id(user.getUser_id()));
        }

        return userList;
    }

    public List<User> listByOrApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        List<User> userList = userDao.listByOrApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);

        for (User user : userList) {
            user.put(findByUser_id(user.getUser_id()));
        }

        return userList;
    }

    public User findByUser_id(String user_id) {
        User user = CacheUtil.get(USER_BY_USER_ID_CACHE, user_id);

        if (user == null) {
            user = userDao.findByUser_id(user_id);

            CacheUtil.put(USER_BY_USER_ID_CACHE, user_id, user);
        }

        return user;
    }

    public User findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id(String app_id, String user_type, String wechat_open_id, String wechat_union_id) {
        User user = userDao.findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id(app_id, user_type, wechat_open_id, wechat_union_id);

        if (user != null) {
            CacheUtil.put(USER_BY_USER_ID_CACHE, user.getUser_id(), user);
        }

        return user;
    }

    public User findByApp_idAndUser_typeAndUser_accountAndUser_password(String app_id, String user_type, String user_account, String user_password) {
        User user = userDao.findByApp_idAndUser_typeAndUser_accountAndUser_password(app_id, user_type, user_account, user_password);

        if (user != null) {
            CacheUtil.put(USER_BY_USER_ID_CACHE, user.getUser_id(), user);
        }

        return user;
    }

    public Boolean save(String user_id, String app_id, String object_id, String user_type, String user_name, String user_avatar, String user_account, String user_mobile, String user_email, String user_password, String wechat_open_id, String wechat_union_id, String system_create_user_id) {
        return userDao.save(user_id, app_id, object_id, user_type, user_name, user_avatar, user_account, user_mobile, user_email, user_password, wechat_open_id, wechat_union_id, system_create_user_id);
    }

    public Boolean updateByUser_password(String user_id, String user_password, String system_update_user_id) {
        boolean result = userDao.updateByUser_password(user_id, user_password, system_update_user_id);

        if (result) {
            CacheUtil.remove(USER_BY_USER_ID_CACHE, user_id);
        }

        return result;
    }

    public Boolean updateByUser_name(String user_id, String user_name, String system_update_user_id) {
        boolean result = userDao.updateByUser_name(user_id, user_name, system_update_user_id);

        if (result) {
            CacheUtil.remove(USER_BY_USER_ID_CACHE, user_id);
        }

        return result;
    }

    public Boolean deleteByUser_idAndSystem_update_user_idValidateSystem_version(String user_id, String system_update_user_id, Integer system_version) {
        User user = findByUser_id(user_id);
        if (!user.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = userDao.deleteByUser_idAndSystem_version(user_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(USER_BY_USER_ID_CACHE, user_id);
        }

        return result;
    }

}