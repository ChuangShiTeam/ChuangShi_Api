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
        Cnd cnd = Cnd.where(WebsiteMenu.SYSTEM_STATUS, true);
        cnd.and(WebsiteMenu.APP_ID, app_id);
        cnd.asc(WebsiteMenu.WEBSITE_MENU_SORT);

        List<WebsiteMenu> websiteMenuList = websiteMenuDao.list(cnd);

        String parent_id = "";
        List<Map<String, Object>> resultList = getChildren(websiteMenuList, parent_id, WebsiteMenu.WEBSITE_MENU_PARENT_ID, WebsiteMenu.WEBSITE_MENU_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL, WebsiteMenu.WEBSITE_MENU_SORT);
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

    public Boolean save(WebsiteMenu websiteMenu) {
        Boolean success = websiteMenuDao.save(websiteMenu);
        return success;
    }

    public Boolean update(WebsiteMenu websiteMenu, String website_menu_id, Integer system_version) {
        Cnd cnd = Cnd.where(WebsiteMenu.SYSTEM_STATUS, true);
        cnd.and(WebsiteMenu.WEBSITE_MENU_ID, website_menu_id);
        cnd.and(WebsiteMenu.SYSTEM_VERSION, system_version);

        Boolean success = websiteMenuDao.update(websiteMenu, cnd);

        if (success) {
            CacheUtil.remove(WEB_SITE_ITEM_CACHE, website_menu_id);
        }

        return success;
    }

    public Boolean delete(String website_menu_id, Integer system_version) {
        Cnd cnd = Cnd.where(WebsiteMenu.SYSTEM_STATUS, true);
        cnd.and(WebsiteMenu.WEBSITE_MENU_ID, website_menu_id);
        cnd.and(WebsiteMenu.SYSTEM_VERSION, system_version);

        Boolean success = websiteMenuDao.delete(cnd);

        if (success) {
            CacheUtil.remove(WEB_SITE_ITEM_CACHE, website_menu_id);
        }

        return success;
    }

}