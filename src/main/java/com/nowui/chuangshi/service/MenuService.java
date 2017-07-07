package com.nowui.chuangshi.service;

import com.alibaba.fastjson.JSONArray;
import com.nowui.chuangshi.cache.MenuCache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Menu;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuService extends Service {

    private MenuCache menuCache = new MenuCache();

    private List<Map<String, Object>> getChildren(List<Menu> menuList, String menu_parent_id, String... keys) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Menu menu : menuList) {
            if (menu.getMenu_parent_id().equals(menu_parent_id)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(Menu.MENU_ID, menu.getMenu_id());
                map.put(Menu.MENU_NAME, menu.getMenu_name());

                for (String key : keys) {
                    map.put(key, menu.get(key));
                }

                List<Map<String, Object>> childrenList = getChildren(menuList, menu.getMenu_id(), keys);
                if (childrenList.size() > 0) {
                    map.put(Constant.CHILDREN, childrenList);
                }
                list.add(map);
            }
        }
        return list;
    }

    public List<Map<String, Object>> treeByApp_id(String app_id) {
        List<Menu> menuList =  menuCache.listByApp_id(app_id);

        return getChildren(menuList, Constant.PARENT_ID, Menu.MENU_IMAGE, Menu.MENU_URL);
    }

    public List<Map<String, Object>> treeByApp_idOrLikeMenu_name(String app_id, String menu_name) {
        List<Menu> menuList =  menuCache.listByApp_idOrLikeMenu_name(app_id, menu_name);

        return getChildren(menuList, Constant.PARENT_ID, Menu.MENU_IMAGE, Menu.MENU_URL, Menu.MENU_SORT, Menu.SYSTEM_VERSION);
    }

    public List<Map<String, Object>> treeByOrApp_idOrLikeMenu_name(String app_id, String menu_name) {
        List<Menu> menuList = menuCache.listByOrApp_idOrLikeMenu_name(app_id, menu_name);

        return getChildren(menuList, Constant.PARENT_ID, Menu.MENU_IMAGE, Menu.MENU_URL, Menu.MENU_SORT, Menu.SYSTEM_VERSION);
    }

    public Menu findByMenu_id(String menu_id) {
        return menuCache.findByMenu_id(menu_id);
    }

    public Boolean save(String menu_id, String app_id, String menu_parent_id, String menu_name, String menu_image, String menu_url, Integer menu_sort, String system_create_user_id) {
        String menu_parent_path = "";

        if (ValidateUtil.isNullOrEmpty(menu_parent_id)) {
            menu_parent_id = Constant.PARENT_ID;

            JSONArray jsonArray = new JSONArray();
            jsonArray.add(menu_parent_id);

            menu_parent_path = jsonArray.toJSONString();
        } else {
            Menu parent = menuCache.findByMenu_id(menu_parent_id);

            JSONArray jsonArray = JSONArray.parseArray(parent.getMenu_parent_path());
            jsonArray.add(menu_parent_id);

            menu_parent_path = jsonArray.toJSONString();
        }
        
        return menuCache.save(menu_id, app_id, menu_parent_id, menu_name, menu_image, menu_url, menu_sort, menu_parent_path, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String menu_id, String menu_parent_id, String menu_name, String menu_image, String menu_url, Integer menu_sort, String system_update_user_id, Integer system_version) {
        Menu menu = menuCache.findByMenu_id(menu_id);

        String menu_parent_path = menu.getMenu_parent_path();

        if (!menu.getMenu_parent_id().equals(menu_parent_id)) {
            Menu parent = menuCache.findByMenu_id(menu_parent_id);

            JSONArray jsonArray = JSONArray.parseArray(parent.getMenu_parent_path());
            jsonArray.add(menu_parent_id);

            menu_parent_path = jsonArray.toJSONString();
        }

        return menuCache.updateValidateSystem_version(menu_id, menu_parent_id, menu_name, menu_image, menu_url, menu_sort, menu_parent_path, system_update_user_id, system_version);
    }

    public Boolean deleteByMenu_idAndSystem_update_user_idValidateSystem_version(String menu_id, String system_update_user_id, Integer system_version) {
        Boolean result = menuCache.deleteByMenu_idAndSystem_update_user_idValidateSystem_version(menu_id, system_update_user_id, system_version);

        if (result) {
            menuCache.deleteByMenu_parent_id(menu_id, system_update_user_id);
        }

        return result;
    }

}