package com.nowui.chuangshi.api.admin.service;

import com.nowui.chuangshi.api.admin.dao.AdminDao;
import com.nowui.chuangshi.api.admin.model.Admin;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class AdminService extends Service {

    public static final AdminService instance = new AdminService();
    private final String ADMIN_ITEM_CACHE = "admin_item_cache";
    private final AdminDao adminDao = new AdminDao();

    public Integer adminCount(String app_id, String user_name) {
        Cnd cnd = new Cnd();
        cnd.leftJoin(User.TABLE_USER, User.USER_ID, Admin.TABLE_ADMIN, Admin.USER_ID);
        cnd.where(Admin.TABLE_ADMIN + "." + Admin.SYSTEM_STATUS, true);
        cnd.and(Admin.TABLE_ADMIN + "." + Admin.APP_ID, app_id);
        cnd.andLikeAllowEmpty(User.TABLE_USER + "." + User.USER_NAME, user_name);

        Integer count = adminDao.count(cnd);
        return count;
    }

    public List<Admin> adminList(String app_id, String user_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.select(Admin.TABLE_ADMIN + "." + Admin.USER_ID);
        cnd.select(User.TABLE_USER + "." + User.USER_NAME);
        cnd.leftJoin(User.TABLE_USER, User.USER_ID, Admin.TABLE_ADMIN, Admin.USER_ID);
        cnd.where(Admin.TABLE_ADMIN + "." + Admin.SYSTEM_STATUS, true);
        cnd.and(Admin.TABLE_ADMIN + "." + Admin.APP_ID, app_id);
        cnd.andAllowEmpty(User.TABLE_USER + "." + User.USER_NAME, user_name);
        cnd.desc(Admin.TABLE_ADMIN + "." + Admin.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);

        List<Admin> adminList = adminDao.primaryKeyList(cnd);
        for (Admin admin : adminList) {
            admin.put(find(admin.getAdmin_id()));
        }
        return adminList;
    }

    public Admin find(String admin_id) {
        Admin admin = CacheUtil.get(ADMIN_ITEM_CACHE, admin_id);

        if (admin == null) {
            admin = adminDao.find(admin_id);
            User user = UserService.instance.find(admin.getUser_id());
            admin.put(User.USER_ACCOUNT, user.getUser_account());
            admin.put(User.USER_NAME, user.getUser_name());
            CacheUtil.put(ADMIN_ITEM_CACHE, admin_id, admin);        
        }

        return admin;
    }

    public Boolean save(Admin admin, String system_create_user_id) {
        Boolean success = adminDao.save(admin, system_create_user_id);
        return success;
    }

    public Boolean update(Admin admin, String admin_id, String system_update_user_id, Integer system_version) {
        admin.remove(Admin.USER_ID);

        Cnd cnd = new Cnd();
        cnd.where(Admin.SYSTEM_STATUS, true);
        cnd.and(Admin.ADMIN_ID, admin_id);
        cnd.and(Admin.SYSTEM_VERSION, system_version);

        Boolean success = adminDao.update(admin, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ADMIN_ITEM_CACHE, admin_id);
        }

        return success;
    }

    public Boolean delete(String admin_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Admin.SYSTEM_STATUS, true);
        cnd.and(Admin.ADMIN_ID, admin_id);
        cnd.and(Admin.SYSTEM_VERSION, system_version);

        Boolean success = adminDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ADMIN_ITEM_CACHE, admin_id);
        }

        return success;
    }

}