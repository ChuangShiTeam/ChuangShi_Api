package com.nowui.chuangshi.api.page.cache;

import com.nowui.chuangshi.api.page.dao.PageDao;
import com.nowui.chuangshi.api.page.model.Page;
import com.nowui.chuangshi.common.cache.Cache;

public class PageCache extends Cache {

    public static final String PAGE_ITEM_CACHE = "page_item_cache";

    public PageCache() {
        setDao(new PageDao());

        setItemCache(PAGE_ITEM_CACHE, Page.PAGE_ID);
    }

}