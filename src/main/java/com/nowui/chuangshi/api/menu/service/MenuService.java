package com.nowui.chuangshi.api.menu.service;

import com.nowui.chuangshi.api.menu.dao.MenuDao;
import com.nowui.chuangshi.api.menu.model.Menu;
import com.nowui.chuangshi.api.role.model.Role;
import com.nowui.chuangshi.api.role.model.RoleMenu;
import com.nowui.chuangshi.api.user.model.UserRole;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuService extends Service {

    public static final MenuService instance = new MenuService();
    private final String MENU_ITEM_CACHE = "menu_item_cache";
    private final MenuDao menuDao = new MenuDao();

    public Integer adminCount(String app_id, String menu_name) {
        Cnd cnd = new Cnd();
        cnd.where(Menu.SYSTEM_STATUS, true);
        cnd.and(Menu.APP_ID, app_id);
        cnd.andAllowEmpty(Menu.MENU_NAME, menu_name);

        Integer count = menuDao.count(cnd);
        return count;
    }

    public List<Menu> adminList(String app_id, String menu_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Menu.SYSTEM_STATUS, true);
        cnd.and(Menu.APP_ID, app_id);
        cnd.andAllowEmpty(Menu.MENU_NAME, menu_name);
        cnd.paginate(m, n);

        List<Menu> menuList = menuDao.primaryKeyList(cnd);
        for (Menu menu : menuList) {
            menu.put(find(menu.getMenu_id()));
        }
        return menuList;
    }

    public List<Map<String, Object>> menuList(String app_id) {
        List<Menu> menuList = appList(app_id);

        List<Map<String, Object>> resultList = packageChildren(menuList, Constant.PARENT_ID, Menu.MENU_PARENT_ID, Menu.MENU_ID, Menu.MENU_ID, Menu.MENU_NAME, Menu.MENU_NAME, new String[]{Menu.MENU_IMAGE, Menu.MENU_URL});

        return resultList;
    }
    
    public List<Map<String, Object>> userList(String user_id) {
        Cnd cnd = new Cnd();
        cnd.leftJoin(RoleMenu.TABLE_ROLE_MENU, RoleMenu.MENU_ID, Menu.TABLE_MENU, Menu.MENU_ID);
        cnd.leftJoin(UserRole.TABLE_USER_ROLE, UserRole.ROLE_ID, RoleMenu.TABLE_ROLE_MENU, RoleMenu.ROLE_ID);
        cnd.where(Menu.TABLE_MENU + "." + Menu.SYSTEM_STATUS, true);
        cnd.and(UserRole.TABLE_USER_ROLE + "." + UserRole.SYSTEM_STATUS, true);
        cnd.and(RoleMenu.TABLE_ROLE_MENU + "." + RoleMenu.SYSTEM_STATUS, true);
        cnd.and(UserRole.TABLE_USER_ROLE + "." + UserRole.USER_ID, user_id);
        cnd.asc(Menu.TABLE_MENU + "." + Menu.MENU_SORT);
        
        List<Menu> menuList = menuDao.primaryKeyList(cnd);
        menuList = menuList.stream().distinct().collect(Collectors.toList());
        
        for (Menu menu : menuList) {
            menu.put(find(menu.getMenu_id()));
        }
        
        List<Map<String, Object>> resultList = packageChildren(menuList, Constant.PARENT_ID, Menu.MENU_PARENT_ID, Menu.MENU_ID, Menu.MENU_ID, Menu.MENU_NAME, Menu.MENU_NAME, new String[]{Menu.MENU_IMAGE, Menu.MENU_URL});
        
        return resultList;
    }
    
    public List<Menu> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(Menu.SYSTEM_STATUS, true);
        cnd.and(Menu.APP_ID, app_id);
        cnd.asc(Menu.MENU_SORT);

        List<Menu> menuList = menuDao.list(cnd);
        
        return menuList;
    }

    public Menu find(String menu_id) {
        Menu menu = CacheUtil.get(MENU_ITEM_CACHE, menu_id);

        if (menu == null) {
            menu = menuDao.find(menu_id);

            CacheUtil.put(MENU_ITEM_CACHE, menu_id, menu);
        }

        return menu;
    }

    public Boolean save(Menu menu, String system_create_user_id) {
        Boolean success = menuDao.save(menu, system_create_user_id);
        return success;
    }

    public Boolean update(Menu menu, String menu_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Menu.SYSTEM_STATUS, true);
        cnd.and(Menu.MENU_ID, menu_id);
        cnd.and(Menu.SYSTEM_VERSION, system_version);

        Boolean success = menuDao.update(menu, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MENU_ITEM_CACHE, menu_id);
        }

        return success;
    }

    public Boolean delete(String menu_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Menu.SYSTEM_STATUS, true);
        cnd.and(Menu.MENU_ID, menu_id);
        cnd.and(Menu.SYSTEM_VERSION, system_version);

        Boolean success = menuDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MENU_ITEM_CACHE, menu_id);
        }

        return success;
    }

}