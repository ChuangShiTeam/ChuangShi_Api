package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.UserLevelCache;
import com.nowui.chuangshi.model.UserLevel;

import java.util.Date;
import java.util.List;

public class UserLevelService extends Service {

    private UserLevelCache userLevelCache = new UserLevelCache();

    public Integer countByApp_idOrLikeUser_level_name(String app_id, String user_level_name, String request_app_id, String request_http_id, String request_user_id) {
        return userLevelCache.countByApp_idOrLikeUser_level_name(app_id, user_level_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idOrLikeUser_level_name(String app_id, String user_level_name, String request_app_id, String request_http_id, String request_user_id) {
        return userLevelCache.countByOrApp_idOrLikeUser_level_name(app_id, user_level_name, request_app_id, request_http_id, request_user_id);
    }

    public List<UserLevel> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return userLevelCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<UserLevel> listByApp_idOrLikeUser_level_nameAndLimit(String app_id, String user_level_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return userLevelCache.listByApp_idOrLikeUser_level_nameAndLimit(app_id, user_level_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<UserLevel> listByOrApp_idOrLikeUser_level_nameAndLimit(String app_id, String user_level_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return userLevelCache.listByOrApp_idOrLikeUser_level_nameAndLimit(app_id, user_level_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public UserLevel findByUser_level_id(String user_level_id, String request_app_id, String request_http_id, String request_user_id) {
        return userLevelCache.findByUser_level_id(user_level_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String user_level_id, String app_id, String user_level_name, Integer user_level_value, Integer user_level_sort, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return userLevelCache.save(user_level_id, app_id, user_level_name, user_level_value, user_level_sort, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String user_level_id, String user_level_name, Integer user_level_value, Integer user_level_sort, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return userLevelCache.updateValidateSystem_version(user_level_id, user_level_name, user_level_value, user_level_sort, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByUser_level_idAndSystem_update_user_idValidateSystem_version(String user_level_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return userLevelCache.deleteByUser_level_idAndSystem_update_user_idValidateSystem_version(user_level_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}