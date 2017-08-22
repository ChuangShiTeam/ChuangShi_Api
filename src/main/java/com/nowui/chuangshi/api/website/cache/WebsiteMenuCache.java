package com.nowui.chuangshi.api.website.cache;

import com.nowui.chuangshi.api.website.dao.WebsiteMenuDao;
import com.nowui.chuangshi.api.website.model.WebsiteMenu;
import com.nowui.chuangshi.common.cache.Cache;

public class WebsiteMenuCache extends Cache {

    public static final String WEBSITE_MENU_ITEM_CACHE = "website_menu_item_cache";

    public WebsiteMenuCache() {
        setDao(new WebsiteMenuDao());

        setItemCache(WEBSITE_MENU_ITEM_CACHE, WebsiteMenu.WEBSITE_MENU_ID);
    }

}