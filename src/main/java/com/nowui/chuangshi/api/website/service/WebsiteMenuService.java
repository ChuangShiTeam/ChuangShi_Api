package com.nowui.chuangshi.api.website.service;

import com.nowui.chuangshi.api.website.dao.WebsiteMenuDao;
import com.nowui.chuangshi.api.website.model.WebsiteMenu;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebsiteMenuService extends Service {

    public static final WebsiteMenuService instance = new WebsiteMenuService();
    private final String WEB_SITE_ITEM_CACHE = "web_site_item_cache";
    private final WebsiteMenuDao websiteMenuDao = new WebsiteMenuDao();

    public List<Map<String, Object>> tree(String app_id) {
        List<WebsiteMenu> websiteMenuList = websiteMenuDao.list(Cnd.where(WebsiteMenu.APP_ID, app_id).asc(WebsiteMenu.WEBSITE_MENU_SORT));

        List<Map<String, Object>> resultList = getChildren(websiteMenuList, "", WebsiteMenu.WEBSITE_MENU_URL, WebsiteMenu.WEBSITE_MENU_SORT);

        return resultList;
    }

    private List<Map<String, Object>> getChildren(List<WebsiteMenu> websiteMenuList, String website_menu_parent_id, String... keys) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (WebsiteMenu websiteMenu : websiteMenuList) {
            if (websiteMenu.getWebsite_menu_parent_id().equals(website_menu_parent_id)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(WebsiteMenu.WEBSITE_MENU_ID, websiteMenu.getWebsite_menu_id());
                map.put(WebsiteMenu.WEBSITE_MENU_NAME, websiteMenu.getWebsite_menu_name());

                for (String key : keys) {
                    map.put(key, websiteMenu.get(key));
                }

                List<Map<String, Object>> childrenList = getChildren(websiteMenuList, websiteMenu.getWebsite_menu_id(), keys);
                // 没有下级返回空数组
                map.put(Constant.CHILDREN, childrenList);
                /*
                 * if (childrenList.size() > 0) { map.put(Constant.CHILDREN,
                 * childrenList); }
                 */

                list.add(map);
            }
        }
        return list;
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
        Boolean result = websiteMenuDao.save(websiteMenu);
        return result;
    }

    public Boolean update(WebsiteMenu websiteMenu, String website_menu_id, Integer system_version) {
        Boolean result = websiteMenuDao.update(websiteMenu, Cnd.where(WebsiteMenu.WEBSITE_MENU_ID, website_menu_id).and(WebsiteMenu.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(WEB_SITE_ITEM_CACHE, website_menu_id);
        }

        return result;
    }

    public Boolean delete(String website_menu_id, Integer system_version) {
        Boolean result = websiteMenuDao.delete(Cnd.where(WebsiteMenu.WEBSITE_MENU_ID, website_menu_id).and(WebsiteMenu.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(WEB_SITE_ITEM_CACHE, website_menu_id);
        }

        return result;
    }

}