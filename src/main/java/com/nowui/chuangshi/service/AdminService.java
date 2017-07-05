package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.AdminCache;
import com.nowui.chuangshi.model.Admin;

import java.util.Date;
import java.util.List;

public class AdminService extends Service {

    private AdminCache adminCache = new AdminCache();

    public Integer countByApp_idOrLikeUser_name(String app_id, String user_name) {
        return adminCache.countByApp_idOrLikeUser_name(app_id, user_name);
    }

    public Integer countByOrApp_idOrLikeUser_name(String app_id, String user_name) {
        return adminCache.countByOrApp_idOrLikeUser_name(app_id, user_name);
    }

    public List<Admin> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return adminCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Admin> listByApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        return adminCache.listByApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);
    }

    public List<Admin> listByOrApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        return adminCache.listByOrApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);
    }

    public Admin findByAdmin_id(String admin_id) {
        return adminCache.findByAdmin_id(admin_id);
    }

    public Boolean save(String admin_id, String app_id, String user_id, String system_create_user_id) {
        return adminCache.save(admin_id, app_id, user_id, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String admin_id, String system_update_user_id, Integer system_version) {
        return adminCache.updateValidateSystem_version(admin_id, system_update_user_id, system_version);
    }

    public Boolean deleteByAdmin_idAndSystem_update_user_idValidateSystem_version(String admin_id, String system_update_user_id, Integer system_version) {
        return adminCache.deleteByAdmin_idAndSystem_update_user_idValidateSystem_version(admin_id, system_update_user_id, system_version);
    }

}