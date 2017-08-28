package com.nowui.chuangshi.api.website.service;

import com.nowui.chuangshi.api.website.cache.WebsiteMenuCache;
import com.nowui.chuangshi.api.website.model.WebsiteMenu;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebsiteMenuService extends Service {

    public static final WebsiteMenuService me = new WebsiteMenuService();

    public WebsiteMenuService() {
        setCache(new WebsiteMenuCache());
    }

    public List<Map<String, Object>> tree(String app_id) {
        List<WebsiteMenu> websiteMenuList = me.list(Cnd.where(WebsiteMenu.APP_ID, app_id).asc(WebsiteMenu.WEBSITE_MENU_SORT));

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

}