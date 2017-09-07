package com.nowui.chuangshi.api.page.service;

import com.nowui.chuangshi.api.page.dao.PageDao;
import com.nowui.chuangshi.api.page.model.Page;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class PageService extends Service {

    public static final PageService instance = new PageService();
    private final String PAGE_ITEM_CACHE = "page_item_cache";
    private final PageDao pageDao = new PageDao();

    public Integer adminCount(String app_id, String page_name) {
        Cnd cnd = new Cnd();
        cnd.where(Page.SYSTEM_STATUS, true);
        cnd.and(Page.APP_ID, app_id);
        cnd.andAllowEmpty(Page.PAGE_NAME, page_name);

        Integer count = pageDao.count(cnd);
        return count;
    }

    public List<Page> adminList(String app_id, String page_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Page.SYSTEM_STATUS, true);
        cnd.and(Page.APP_ID, app_id);
        cnd.andAllowEmpty(Page.PAGE_NAME, page_name);
        cnd.asc(Page.PAGE_SORT);
        cnd.paginate(m, n);

        List<Page> pageList = pageDao.primaryKeyList(cnd);
        for (Page page : pageList) {
            page.put(find(page.getPage_id()));
        }
        return pageList;
    }

    public List<Page> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(Page.SYSTEM_STATUS, true);
        cnd.and(Page.APP_ID, app_id);

        List<Page> pageList = pageDao.list(cnd);
        for (Page page : pageList) {
            page.put(find(page.getPage_id()));
        }
        return pageList;
    }

    public Page find(String page_id) {
        Page page = CacheUtil.get(PAGE_ITEM_CACHE, page_id);

        if (page == null) {
            page = pageDao.find(page_id);

            CacheUtil.put(PAGE_ITEM_CACHE, page_id, page);
        }

        return page;
    }

    public Boolean save(Page page, String system_create_user_id) {
        Boolean success = pageDao.save(page, system_create_user_id);
        return success;
    }

    public Boolean update(Page page, String page_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Page.SYSTEM_STATUS, true);
        cnd.and(Page.PAGE_ID, page_id);
        cnd.and(Page.SYSTEM_VERSION, system_version);

        Boolean success = pageDao.update(page, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PAGE_ITEM_CACHE, page_id);
        }

        return success;
    }

    public Boolean delete(String page_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Page.SYSTEM_STATUS, true);
        cnd.and(Page.PAGE_ID, page_id);
        cnd.and(Page.SYSTEM_VERSION, system_version);

        Boolean success = pageDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PAGE_ITEM_CACHE, page_id);
        }

        return success;
    }

}