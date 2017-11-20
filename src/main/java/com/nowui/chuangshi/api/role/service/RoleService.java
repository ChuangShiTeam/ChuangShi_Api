package com.nowui.chuangshi.api.role.service;

import java.util.List;

import com.nowui.chuangshi.api.role.dao.RoleDao;
import com.nowui.chuangshi.api.role.model.Role;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class RoleService extends Service {

    public static final RoleService instance = new RoleService();
    private final String ROLE_ITEM_CACHE = "role_item_cache";
    private final RoleDao roleDao = new RoleDao();

    public Integer adminCount(String app_id, String role_name, String role_code) {
        Cnd cnd = new Cnd();
        cnd.where(Role.SYSTEM_STATUS, true);
        cnd.and(Role.APP_ID, app_id);
        cnd.andLikeAllowEmpty(Role.ROLE_NAME, role_name);
        cnd.andLikeAllowEmpty(Role.ROLE_CODE, role_code);

        Integer count = roleDao.count(cnd);
        return count;
    }

    public List<Role> adminList(String app_id, String role_name, String role_code, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Role.SYSTEM_STATUS, true);
        cnd.and(Role.APP_ID, app_id);
        cnd.andLikeAllowEmpty(Role.ROLE_NAME, role_name);
        cnd.andLikeAllowEmpty(Role.ROLE_CODE, role_code);
        cnd.asc(Role.ROLE_SORT);
        cnd.paginate(m, n);

        List<Role> roleList = roleDao.primaryKeyList(cnd);
        for (Role role : roleList) {
            role.put(find(role.getRole_id()));
        }
        return roleList;
    }
    
    public List<Role> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(Role.SYSTEM_STATUS, true);
        cnd.and(Role.APP_ID, app_id);
        cnd.asc(Role.ROLE_SORT);

        List<Role> roleList = roleDao.primaryKeyList(cnd);
        for (Role role : roleList) {
            role.put(find(role.getRole_id()));
        }
        return roleList;
    }
    
    public Role find(String role_id) {
        Role role = CacheUtil.get(ROLE_ITEM_CACHE, role_id);

        if (role == null) {
            role = roleDao.find(role_id);

            CacheUtil.put(ROLE_ITEM_CACHE, role_id, role);
        }

        return role;
    }

    public Boolean save(Role role, String system_create_user_id) {
        Boolean success = roleDao.save(role, system_create_user_id);
        return success;
    }

    public Boolean update(Role role, String role_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Role.SYSTEM_STATUS, true);
        cnd.and(Role.ROLE_ID, role_id);
        cnd.and(Role.SYSTEM_VERSION, system_version);

        Boolean success = roleDao.update(role, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ROLE_ITEM_CACHE, role_id);
        }

        return success;
    }

    public Boolean delete(String role_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Role.SYSTEM_STATUS, true);
        cnd.and(Role.ROLE_ID, role_id);
        cnd.and(Role.SYSTEM_VERSION, system_version);

        Boolean success = roleDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ROLE_ITEM_CACHE, role_id);
        }

        return success;
    }
    
}