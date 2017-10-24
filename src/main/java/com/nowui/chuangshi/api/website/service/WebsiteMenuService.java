package com.nowui.chuangshi.api.website.service;

import com.nowui.chuangshi.api.website.dao.WebsiteMenuDao;
import com.nowui.chuangshi.api.website.model.WebsiteMenu;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;
import java.util.Map;

public class WebsiteMenuService extends Service {

    public static final WebsiteMenuService instance = new WebsiteMenuService();
    private final String WEB_SITE_ITEM_CACHE = "web_site_item_cache";
    private final WebsiteMenuDao websiteMenuDao = new WebsiteMenuDao();

    public List<Map<String, Object>> tree(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(WebsiteMenu.SYSTEM_STATUS, true);
        cnd.and(WebsiteMenu.APP_ID, app_id);
        cnd.asc(WebsiteMenu.WEBSITE_MENU_SORT);

        List<WebsiteMenu> websiteMenuList = websiteMenuDao.primaryKeyList(cnd);
        for (WebsiteMenu websiteMenu : websiteMenuList) {
            websiteMenu.put(find(websiteMenu.getWebsite_menu_id()));
        }

        String parent_id = "";
        List<Map<String, Object>> resultList = packageChildren(websiteMenuList, parent_id, WebsiteMenu.WEBSITE_MENU_PARENT_ID, WebsiteMenu.WEBSITE_MENU_ID, WebsiteMenu.WEBSITE_MENU_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_NAME, new String[]{WebsiteMenu.WEBSITE_MENU_URL, WebsiteMenu.WEBSITE_MENU_SORT});
        return resultList;
    }

    public List<Map<String, Object>> appTree(String app_id, String id_custom_name, String name_coustom_name) {
        Cnd cnd = new Cnd();
        cnd.where(WebsiteMenu.SYSTEM_STATUS, true);
        cnd.and(WebsiteMenu.APP_ID, app_id);
        cnd.asc(WebsiteMenu.WEBSITE_MENU_SORT);

        List<WebsiteMenu> websiteMenuList = websiteMenuDao.primaryKeyList(cnd);
        for (WebsiteMenu websiteMenu : websiteMenuList) {
            websiteMenu.put(find(websiteMenu.getWebsite_menu_id()));
        }

        String parent_id = "";
        List<Map<String, Object>> resultList = packageChildren(websiteMenuList, parent_id, WebsiteMenu.WEBSITE_MENU_PARENT_ID, WebsiteMenu.WEBSITE_MENU_ID, id_custom_name, WebsiteMenu.WEBSITE_MENU_NAME, name_coustom_name, new String[]{});
        return resultList;
    }

    public WebsiteMenu find(String website_menu_id) {
        WebsiteMenu websiteMenu = CacheUtil.get(WEB_SITE_ITEM_CACHE, website_menu_id);

        if (websiteMenu == null) {
            websiteMenu = websiteMenuDao.find(website_menu_id);

            CacheUtil.put(WEB_SITE_ITEM_CACHE, website_menu_id, websiteMenu);
        }

        return websiteMenu;
    }

    public Boolean save(WebsiteMenu websiteMenu, String system_create_user_id) {
        Boolean success = websiteMenuDao.save(websiteMenu, system_create_user_id);
        return success;
    }

    public Boolean update(WebsiteMenu websiteMenu, String website_menu_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(WebsiteMenu.SYSTEM_STATUS, true);
        cnd.and(WebsiteMenu.WEBSITE_MENU_ID, website_menu_id);
        cnd.and(WebsiteMenu.SYSTEM_VERSION, system_version);

        Boolean success = websiteMenuDao.update(websiteMenu, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(WEB_SITE_ITEM_CACHE, website_menu_id);
        }

        return success;
    }

    public Boolean delete(String website_menu_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(WebsiteMenu.SYSTEM_STATUS, true);
        cnd.and(WebsiteMenu.WEBSITE_MENU_ID, website_menu_id);
        cnd.and(WebsiteMenu.SYSTEM_VERSION, system_version);

        Boolean success = websiteMenuDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(WEB_SITE_ITEM_CACHE, website_menu_id);
        }

        return success;
    }

}