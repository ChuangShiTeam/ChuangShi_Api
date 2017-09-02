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
        Integer count = pageDao.count(Cnd.where(Page.APP_ID, app_id).andAllowEmpty(Page.PAGE_NAME, page_name));
        return count;
    }

    public List<Page> adminList(String app_id, String page_name, Integer m, Integer n) {
        List<Page> pageList = pageDao.list(Cnd.where(Page.APP_ID, app_id).andAllowEmpty(Page.PAGE_NAME, page_name).paginate(m, n));
        return pageList;
    }

    public List<Page> appList(String app_id) {
        List<Page> pageList = pageDao.list(Cnd.where(Page.APP_ID, app_id));
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

    public Boolean save(Page page) {
        Boolean result = pageDao.save(page);
        return result;
    }

    public Boolean update(Page page, String page_id, Integer system_version) {
        Boolean result = pageDao.update(page, Cnd.where(Page.PAGE_ID, page_id).and(Page.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(PAGE_ITEM_CACHE, page_id);
        }

        return result;
    }

    public Boolean delete(String page_id, Integer system_version) {
        Boolean result = pageDao.delete(Cnd.where(Page.PAGE_ID, page_id).and(Page.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(PAGE_ITEM_CACHE, page_id);
        }

        return result;
    }

}