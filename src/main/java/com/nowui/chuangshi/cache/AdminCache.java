package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.AdminDao;
import com.nowui.chuangshi.model.Admin;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class AdminCache extends Cache {

    public static final String ADMIN_BY_ADMIN_ID_CACHE = "admin_by_admin_id_cache";

    private AdminDao adminDao = new AdminDao();

    public Integer countByApp_idOrLikeUser_name(String app_id, String user_name, String request_app_id, String request_http_id, String request_user_id) {
        return adminDao.countByApp_idOrLikeUser_name(app_id, user_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idOrLikeUser_name(String app_id, String user_name, String request_app_id, String request_http_id, String request_user_id) {
        return adminDao.countByOrApp_idOrLikeUser_name(app_id, user_name, request_app_id, request_http_id, request_user_id);
    }

    public List<Admin> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<Admin> adminList = adminDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);

        for (Admin admin : adminList) {
            admin.put(findByAdmin_id(admin.getAdmin_id(), request_app_id, request_http_id, request_user_id));
        }

        return adminList;
    }

    public List<Admin> listByApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<Admin> adminList = adminDao.listByApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n, request_app_id, request_http_id, request_user_id);

        for (Admin admin : adminList) {
            admin.put(findByAdmin_id(admin.getAdmin_id(), request_app_id, request_http_id, request_user_id));
        }

        return adminList;
    }

    public List<Admin> listByOrApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<Admin> adminList = adminDao.listByOrApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n, request_app_id, request_http_id, request_user_id);

        for (Admin admin : adminList) {
            admin.put(findByAdmin_id(admin.getAdmin_id(), request_app_id, request_http_id, request_user_id));
        }

        return adminList;
    }

    public Admin findByAdmin_id(String admin_id, String request_app_id, String request_http_id, String request_user_id) {
        Admin admin = CacheUtil.get(ADMIN_BY_ADMIN_ID_CACHE, admin_id);

        if (admin == null) {
            admin = adminDao.findByAdmin_id(admin_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(ADMIN_BY_ADMIN_ID_CACHE, admin_id, admin);
        }

        return admin;
    }

    public Boolean save(String admin_id, String app_id, String user_id, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return adminDao.save(admin_id, app_id, user_id, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String admin_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Admin admin = findByAdmin_id(admin_id, request_app_id, request_http_id, request_user_id);
        if (!admin.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = adminDao.update(admin_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(ADMIN_BY_ADMIN_ID_CACHE, admin_id);
        }

        return result;
    }

    public Boolean deleteByAdmin_idAndSystem_update_user_idValidateSystem_version(String admin_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Admin admin = findByAdmin_id(admin_id, request_app_id, request_http_id, request_user_id);
        if (!admin.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = adminDao.deleteByAdmin_idAndSystem_version(admin_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(ADMIN_BY_ADMIN_ID_CACHE, admin_id);
        }

        return result;
    }

}