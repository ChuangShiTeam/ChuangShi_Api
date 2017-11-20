package com.nowui.chuangshi.api.user.service;

import java.util.ArrayList;
import java.util.List;

import com.nowui.chuangshi.api.role.model.Role;
import com.nowui.chuangshi.api.role.service.RoleService;
import com.nowui.chuangshi.api.user.dao.UserRoleDao;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.model.UserRole;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;

public class UserRoleService extends Service {

    public static final UserRoleService instance = new UserRoleService();
    private final String USER_ROLE_ITEM_CACHE = "user_role_item_cache";
    private final UserRoleDao userRoleDao = new UserRoleDao();

    public Integer adminCount(String app_id, String user_id, String role_id, String user_type) {
        Cnd cnd = new Cnd();
        cnd.where(UserRole.SYSTEM_STATUS, true);
        cnd.and(UserRole.APP_ID, app_id);
        cnd.andAllowEmpty(UserRole.USER_ID, user_id);
        cnd.andAllowEmpty(UserRole.ROLE_ID, role_id);
        cnd.andAllowEmpty(UserRole.USER_TYPE, user_type);

        Integer count = userRoleDao.count(cnd);
        return count;
    }

    public List<UserRole> adminList(String app_id, String user_id, String role_id, String user_type, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(UserRole.SYSTEM_STATUS, true);
        cnd.and(UserRole.APP_ID, app_id);
        cnd.andAllowEmpty(UserRole.USER_ID, user_id);
        cnd.andAllowEmpty(UserRole.ROLE_ID, role_id);
        cnd.andAllowEmpty(UserRole.USER_TYPE, user_type);
        cnd.paginate(m, n);

        List<UserRole> user_roleList = userRoleDao.primaryKeyList(cnd);
        for (UserRole user_role : user_roleList) {
            user_role.put(find(user_role.getUser_role_id()));
        }
        return user_roleList;
    }
    
    public List<UserRole> userList(String user_id) {
        Cnd cnd = new Cnd();
        cnd.where(UserRole.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(UserRole.USER_ID, user_id);

        List<UserRole> user_roleList = userRoleDao.primaryKeyList(cnd);
        for (UserRole user_role : user_roleList) {
            user_role.put(find(user_role.getUser_role_id()));
        }
        return user_roleList;
    }

    public UserRole find(String user_role_id) {
        UserRole user_role = CacheUtil.get(USER_ROLE_ITEM_CACHE, user_role_id);

        if (user_role == null) {
            user_role = userRoleDao.find(user_role_id);

            CacheUtil.put(USER_ROLE_ITEM_CACHE, user_role_id, user_role);
        }

        return user_role;
    }

    public Boolean save(UserRole user_role, String system_create_user_id) {
        Boolean success = userRoleDao.save(user_role, system_create_user_id);
        return success;
    }

    public Boolean update(List<UserRole> userRoleList, String app_id, String user_id, String request_user_id) {
        User user = UserService.instance.find(user_id);
        
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        //查询旧的用户角色列表
        List<UserRole> oldUserRoleList = userList(user_id);
        if (oldUserRoleList != null && oldUserRoleList.size() > 0) {
            //删除旧的用户角色
            for (UserRole userRole : oldUserRoleList) {
                this.delete(userRole.getUser_role_id(), request_user_id, userRole.getSystem_version());
            }
        }
        if (userRoleList == null || userRoleList.size() == 0) {
            return true;
        }
        //保存新的用户角色
        for (UserRole userRole : userRoleList) {
            userRole.setApp_id(app_id);
            userRole.setUser_role_id(Util.getRandomUUID());
            userRole.setUser_id(user.getUser_id());
            userRole.setUser_type(user.getUser_type());
            
            this.save(userRole, request_user_id);
        }
        return true;
    }

    public Boolean delete(String user_role_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(UserRole.SYSTEM_STATUS, true);
        cnd.and(UserRole.USER_ROLE_ID, user_role_id);
        cnd.and(UserRole.SYSTEM_VERSION, system_version);

        Boolean success = userRoleDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(USER_ROLE_ITEM_CACHE, user_role_id);
        }

        return success;
    }
    
    /**
     * 查询应用下角色列表及选中用户所对应角色
     * @param app_id
     * @param user_id
     * @return
     */
    public List<Role> appIdAndUserIdList(String app_id, String user_id) {
        //查询应用下所有角色列表
        List<Role> roleList = RoleService.instance.appList(app_id);
        if (roleList == null || roleList.size() == 0) {
            return new ArrayList<>();
        }
        
        //查询用户关联的角色列表
        List<UserRole> userRoleList = userList(user_id);
        if (userRoleList != null && userRoleList.size() > 0) {
            for (Role role : roleList) {
                role.put(Constant.IS_SELECT, false);
                for (UserRole userRole : userRoleList) {
                    if (role.getRole_id().equals(userRole.getRole_id())) {
                        role.put(Constant.IS_SELECT, true);
                        break;
                    }
                }
            }
        }
        return roleList;
    }
    
}