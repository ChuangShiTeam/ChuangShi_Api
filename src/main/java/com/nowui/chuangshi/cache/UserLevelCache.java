package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.UserLevelDao;
import com.nowui.chuangshi.model.UserLevel;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class UserLevelCache extends Cache {

    public static final String USER_LEVEL_BY_USER_LEVEL_ID_CACHE = "user_level_by_user_level_id_cache";

    private UserLevelDao userLevelDao = new UserLevelDao();

    public Integer countByApp_idOrLikeUser_level_name(String app_id, String user_level_name, String request_app_id, String request_http_id, String request_user_id) {
        return userLevelDao.countByApp_idOrLikeUser_level_name(app_id, user_level_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idOrLikeUser_level_name(String app_id, String user_level_name, String request_app_id, String request_http_id, String request_user_id) {
        return userLevelDao.countByOrApp_idOrLikeUser_level_name(app_id, user_level_name, request_app_id, request_http_id, request_user_id);
    }

    public List<UserLevel> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<UserLevel> user_levelList = userLevelDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);

        for (UserLevel user_level : user_levelList) {
            user_level.put(findByUser_level_id(user_level.getUser_level_id(), request_app_id, request_http_id, request_user_id));
        }

        return user_levelList;
    }

    public List<UserLevel> listByApp_idOrLikeUser_level_nameAndLimit(String app_id, String user_level_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<UserLevel> user_levelList = userLevelDao.listByApp_idOrLikeUser_level_nameAndLimit(app_id, user_level_name, m, n, request_app_id, request_http_id, request_user_id);

        for (UserLevel user_level : user_levelList) {
            user_level.put(findByUser_level_id(user_level.getUser_level_id(), request_app_id, request_http_id, request_user_id));
        }

        return user_levelList;
    }

    public List<UserLevel> listByOrApp_idOrLikeUser_level_nameAndLimit(String app_id, String user_level_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<UserLevel> user_levelList = userLevelDao.listByOrApp_idOrLikeUser_level_nameAndLimit(app_id, user_level_name, m, n, request_app_id, request_http_id, request_user_id);

        for (UserLevel user_level : user_levelList) {
            user_level.put(findByUser_level_id(user_level.getUser_level_id(), request_app_id, request_http_id, request_user_id));
        }

        return user_levelList;
    }

    public UserLevel findByUser_level_id(String user_level_id, String request_app_id, String request_http_id, String request_user_id) {
        UserLevel user_level = CacheUtil.get(USER_LEVEL_BY_USER_LEVEL_ID_CACHE, user_level_id);

        if (user_level == null) {
            user_level = userLevelDao.findByUser_level_id(user_level_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(USER_LEVEL_BY_USER_LEVEL_ID_CACHE, user_level_id, user_level);
        }

        return user_level;
    }

    public Boolean save(String user_level_id, String app_id, String user_level_name, Integer user_level_value, Integer user_level_sort, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return userLevelDao.save(user_level_id, app_id, user_level_name, user_level_value, user_level_sort, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String user_level_id, String user_level_name, Integer user_level_value, Integer user_level_sort, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        UserLevel user_level = findByUser_level_id(user_level_id, request_app_id, request_http_id, request_user_id);
        if (!user_level.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = userLevelDao.update(user_level_id, user_level_name, user_level_value, user_level_sort, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(USER_LEVEL_BY_USER_LEVEL_ID_CACHE, user_level_id);
        }

        return result;
    }

    public Boolean deleteByUser_level_idAndSystem_update_user_idValidateSystem_version(String user_level_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        UserLevel user_level = findByUser_level_id(user_level_id, request_app_id, request_http_id, request_user_id);
        if (!user_level.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = userLevelDao.deleteByUser_level_idAndSystem_version(user_level_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(USER_LEVEL_BY_USER_LEVEL_ID_CACHE, user_level_id);
        }

        return result;
    }

}