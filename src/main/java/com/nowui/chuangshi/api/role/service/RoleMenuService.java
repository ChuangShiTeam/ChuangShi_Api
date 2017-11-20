package com.nowui.chuangshi.api.role.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nowui.chuangshi.api.menu.model.Menu;
import com.nowui.chuangshi.api.menu.service.MenuService;
import com.nowui.chuangshi.api.role.dao.RoleMenuDao;
import com.nowui.chuangshi.api.role.model.Role;
import com.nowui.chuangshi.api.role.model.RoleMenu;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;

public class RoleMenuService extends Service {

    public static final RoleMenuService instance = new RoleMenuService();
    private final String ROLE_MENU_ITEM_CACHE = "role_menu_item_cache";
    private final RoleMenuDao roleMenuDao = new RoleMenuDao();

    public Integer adminCount(String app_id, String role_id, String menu_id) {
        Cnd cnd = new Cnd();
        cnd.where(RoleMenu.SYSTEM_STATUS, true);
        cnd.and(RoleMenu.APP_ID, app_id);
        cnd.andAllowEmpty(RoleMenu.ROLE_ID, role_id);
        cnd.andAllowEmpty(RoleMenu.MENU_ID, menu_id);

        Integer count = roleMenuDao.count(cnd);
        return count;
    }

    public List<RoleMenu> adminList(String app_id, String role_id, String menu_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(RoleMenu.SYSTEM_STATUS, true);
        cnd.and(RoleMenu.APP_ID, app_id);
        cnd.andAllowEmpty(RoleMenu.ROLE_ID, role_id);
        cnd.andAllowEmpty(RoleMenu.MENU_ID, menu_id);
        cnd.paginate(m, n);

        List<RoleMenu> role_menuList = roleMenuDao.primaryKeyList(cnd);
        for (RoleMenu role_menu : role_menuList) {
            role_menu.put(find(role_menu.getRole_menu_id()));
        }
        return role_menuList;
    }
    
    public List<RoleMenu> roleList(String role_id) {
        Cnd cnd = new Cnd();
        cnd.where(RoleMenu.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(RoleMenu.ROLE_ID, role_id);
        
        List<RoleMenu> role_menuList = roleMenuDao.primaryKeyList(cnd);
        for (RoleMenu role_menu : role_menuList) {
            role_menu.put(find(role_menu.getRole_menu_id()));
        }
        return role_menuList;
    }

    public RoleMenu find(String role_menu_id) {
        RoleMenu role_menu = CacheUtil.get(ROLE_MENU_ITEM_CACHE, role_menu_id);

        if (role_menu == null) {
            role_menu = roleMenuDao.find(role_menu_id);

            CacheUtil.put(ROLE_MENU_ITEM_CACHE, role_menu_id, role_menu);
        }

        return role_menu;
    }

    public Boolean save(RoleMenu role_menu, String system_create_user_id) {
        Boolean success = roleMenuDao.save(role_menu, system_create_user_id);
        return success;
    }

    public Boolean update(List<RoleMenu> roleMenuList, String app_id, String role_id, String request_user_id) {
        Role role = RoleService.instance.find(role_id);
        
        if (role == null) {
            throw new RuntimeException("角色不存在");
        }
        
        //查询旧的角色菜单列表
        List<RoleMenu> oldRoleMenuList = roleList(role_id);
        if (oldRoleMenuList != null && oldRoleMenuList.size() > 0) {
            //删除旧的角色菜单
            for (RoleMenu roleMenu : oldRoleMenuList) {
                this.delete(roleMenu.getRole_menu_id(), request_user_id, roleMenu.getSystem_version());
            }
        }
        if (roleMenuList == null || roleMenuList.size() == 0) {
            return true;
        }
        //保存新的角色菜单
        for (RoleMenu roleMenu : roleMenuList) {
            roleMenu.setApp_id(app_id);
            roleMenu.setRole_menu_id(Util.getRandomUUID());
            roleMenu.setRole_id(role.getRole_id());
            
            this.save(roleMenu, request_user_id);
        }
        return true;
    }

    public Boolean delete(String role_menu_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RoleMenu.SYSTEM_STATUS, true);
        cnd.and(RoleMenu.ROLE_MENU_ID, role_menu_id);
        cnd.and(RoleMenu.SYSTEM_VERSION, system_version);

        Boolean success = roleMenuDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ROLE_MENU_ITEM_CACHE, role_menu_id);
        }

        return success;
    }
    
    /**
     * 查询角色对应的菜单列表
     * @param app_id
     * @param role_id
     * @return
     */
    public List<Map<String, Object>> appIdAndRoleIdList(String app_id, String role_id) {
        //查询应用下所有的菜单列表
        List<Menu> menuList = MenuService.instance.appList(app_id);
        
        //查询角色对应菜单列表
        List<RoleMenu> roleMenuList = roleList(role_id);
        
        List<Map<String, Object>> resultList = menuChildrenList(menuList, roleMenuList, Constant.PARENT_ID);
        
        return resultList;
    }
    
    private List<Map<String, Object>> menuChildrenList(List<Menu> menuList,
            List<RoleMenu> roleMenuList, String parent_id) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        // 循环所有菜单
        for (Menu menu : menuList) {
            // 根据当前传入菜单id，是否属于其他菜单的父菜单，来判断是否拥有子菜单
            if (menu.getMenu_parent_id().equals(parent_id)) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put(Menu.MENU_ID, menu.getMenu_id());
                resultMap.put(Menu.MENU_NAME, menu.getMenu_name());
                
                resultMap.put(Constant.IS_SELECT, false);

                for (RoleMenu roleMenu : roleMenuList) {
                    if (menu.getMenu_id().equals(roleMenu.getMenu_id())) {
                        resultMap.put(Constant.IS_SELECT, true);
                    }
                }
                
                List<Map<String, Object>> childrenList = menuChildrenList(menuList, roleMenuList, menu.getMenu_id());
                
                resultMap.put(Constant.CHILDREN, childrenList);
                resultList.add(resultMap);
            }
        }
        return resultList;
    }

}