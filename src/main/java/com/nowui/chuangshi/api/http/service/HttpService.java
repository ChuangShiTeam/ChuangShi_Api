package com.nowui.chuangshi.api.http.service;

import com.nowui.chuangshi.api.http.dao.HttpDao;
import com.nowui.chuangshi.api.http.model.Http;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class HttpService extends Service {

    public static final HttpService instance = new HttpService();
    private final String HTTP_ITEM_CACHE = "http_item_cache";
    private final HttpDao httpDao = new HttpDao();

    public Integer adminCount(String app_id, String http_url, String http_code) {
        Cnd cnd = new Cnd();
        cnd.where(Http.SYSTEM_STATUS, true);
        cnd.and(Http.APP_ID, app_id);
        cnd.andAllowEmpty(Http.HTTP_URL, http_url);
        cnd.andAllowEmpty(Http.HTTP_CODE, http_code);

        Integer count = httpDao.count(cnd);
        return count;
    }

    public Integer httpUrlCount(String app_id, String http_url) {
        Cnd cnd = new Cnd();
        cnd.where(Http.SYSTEM_STATUS, true);
        cnd.and(Http.APP_ID, app_id);
        cnd.and(Http.HTTP_URL, http_url);

        Integer count = httpDao.count(cnd);
        return count;
    }

    public List<Http> adminList(String app_id, String http_url, String http_code, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Http.SYSTEM_STATUS, true);
        cnd.and(Http.APP_ID, app_id);
        cnd.andAllowEmpty(Http.HTTP_URL, http_url);
        cnd.andAllowEmpty(Http.HTTP_CODE, http_code);
        cnd.paginate(m, n);

        List<Http> httpList = httpDao.primaryKeyList(cnd);
        for (Http http : httpList) {
            http.put(find(http.getHttp_id()));
        }
        return httpList;
    }

    public Http find(String http_id) {
        Http http = CacheUtil.get(HTTP_ITEM_CACHE, http_id);

        if (http == null) {
            http = httpDao.find(http_id);

            CacheUtil.put(HTTP_ITEM_CACHE, http_id, http);
        }

        return http;
    }

    public Boolean save(Http http) {
        Boolean success = httpDao.save(http);
        return success;
    }

    public Boolean save(Http http, String system_create_user_id) {
        Boolean success = httpDao.save(http, system_create_user_id);
        return success;
    }

    public Boolean update(Http http, String http_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Http.SYSTEM_STATUS, true);
        cnd.and(Http.HTTP_ID, http_id);
        cnd.and(Http.SYSTEM_VERSION, system_version);

        Boolean success = httpDao.update(http, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(HTTP_ITEM_CACHE, http_id);
        }

        return success;
    }

    public Boolean delete(String http_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Http.SYSTEM_STATUS, true);
        cnd.and(Http.HTTP_ID, http_id);
        cnd.and(Http.SYSTEM_VERSION, system_version);

        Boolean success = httpDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(HTTP_ITEM_CACHE, http_id);
        }

        return success;
    }

}