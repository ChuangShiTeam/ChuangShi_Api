package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.UserCache;
import com.nowui.chuangshi.model.User;

import java.util.Date;
import java.util.List;

public class UserService extends Service {

    private UserCache userCache = new UserCache();

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return userCache.countByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return userCache.countByOrApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<User> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return userCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<User> listByApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return userCache.listByApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<User> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return userCache.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);
    }

    public User findByUser_id(String user_id, String request_app_id, String request_http_id, String request_user_id) {
        return userCache.findByUser_id(user_id, request_app_id, request_http_id, request_user_id);
    }

    public User findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id(String app_id, String user_type, String wechat_open_id, String wechat_union_id, String request_app_id, String request_http_id, String request_user_id) {
        return userCache.findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id(app_id, user_type, wechat_open_id, wechat_union_id, request_app_id, request_http_id, request_user_id);
    }

    public User findByApp_idAndUser_typeAndUser_accountAndUser_password(String app_id, String user_type, String user_account, String user_password, String request_app_id, String request_http_id, String request_user_id) {
        return userCache.findByApp_idAndUser_typeAndUser_accountAndUser_password(app_id, user_type, user_account, user_password, request_app_id, request_http_id, request_user_id);
    }

    private Boolean save(String user_id, String app_id, String organization_id, String role_id, String user_level_id, String user_type, String user_account, String user_phone, String user_email, String user_password, String user_name, String user_avatar, String wechat_open_id, String wechat_union_id, String extend_id, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return userCache.save(user_id, app_id, organization_id, role_id, user_level_id, user_type, user_account, user_phone, user_email, user_password, user_name, user_avatar, wechat_open_id, wechat_union_id, extend_id, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean saveByUser_idAndApp_idAndOrganization_idAndole_idAndUser_level_idAndUser_typeAndUser_nameAndUser_avatarAndWechat_open_idAndWechat_union_id(String user_id, String app_id, String organization_id, String role_id, String user_level_id, String user_type, String user_name, String user_avatar, String wechat_open_id, String wechat_union_id, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        String user_account = "";
        String user_phone = "";
        String user_email = "";
        String user_password = "";
        String extend_id = "";

        return save(user_id, app_id, organization_id, role_id, user_level_id, user_type, user_account, user_phone, user_email, user_password, user_name, user_avatar, wechat_open_id, wechat_union_id, extend_id, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean saveByUser_idAndApp_idAndUser_typeAndUser_accountAndUser_passwordAndUser_name(String user_id, String app_id, String user_type, String user_account, String user_password, String user_name, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        String organization_id = "";
        String role_id = "";
        String user_level_id = "";
        String user_phone = "";
        String user_email = "";
        String user_avatar = "";
        String wechat_open_id = "";
        String wechat_union_id = "";
        String extend_id = "";

        return save(user_id, app_id, organization_id, role_id, user_level_id, user_type, user_account, user_phone, user_email, user_password, user_name, user_avatar, wechat_open_id, wechat_union_id, extend_id, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String user_id, String organization_id, String role_id, String user_level_id, String user_type, String user_account, String user_phone, String user_email, String user_password, String user_name, String user_avatar, String wechat_open_id, String wechat_union_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return userCache.updateValidateSystem_version(user_id, organization_id, role_id, user_level_id, user_type, user_account, user_phone, user_email, user_password, user_name, user_avatar, wechat_open_id, wechat_union_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByUser_idAndSystem_update_user_idValidateSystem_version(String user_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return userCache.deleteByUser_idAndSystem_update_user_idValidateSystem_version(user_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}