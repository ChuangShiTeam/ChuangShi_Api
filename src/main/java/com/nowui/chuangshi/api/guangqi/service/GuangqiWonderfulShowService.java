package com.nowui.chuangshi.api.guangqi.service;

import com.nowui.chuangshi.api.guangqi.dao.GuangqiWonderfulShowDao;
import com.nowui.chuangshi.api.guangqi.model.GuangqiWonderfulShow;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class GuangqiWonderfulShowService extends Service {

    public static final GuangqiWonderfulShowService instance = new GuangqiWonderfulShowService();
    private final String GUANGQI_WONDERFUL_SHOW_ITEM_CACHE = "guangqi_wonderful_show_item_cache";
    private final GuangqiWonderfulShowDao guangqiWonderfulShowDao = new GuangqiWonderfulShowDao();

    public Integer adminCount(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiWonderfulShow.SYSTEM_STATUS, true);
        cnd.and(GuangqiWonderfulShow.APP_ID, app_id);

        Integer count = guangqiWonderfulShowDao.count(cnd);
        return count;
    }

    public List<GuangqiWonderfulShow> adminList(String app_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiWonderfulShow.SYSTEM_STATUS, true);
        cnd.and(GuangqiWonderfulShow.APP_ID, app_id);
        cnd.asc(GuangqiWonderfulShow.WONDERFUL_SHOW_SORT);
        cnd.paginate(m, n);

        List<GuangqiWonderfulShow> guangqi_wonderful_showList = guangqiWonderfulShowDao.primaryKeyList(cnd);
        for (GuangqiWonderfulShow guangqi_wonderful_show : guangqi_wonderful_showList) {
            guangqi_wonderful_show.put(find(guangqi_wonderful_show.getWonderful_show_id()));
        }
        return guangqi_wonderful_showList;
    }
    
    public List<GuangqiWonderfulShow> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiWonderfulShow.SYSTEM_STATUS, true);
        cnd.and(GuangqiWonderfulShow.APP_ID, app_id);
        cnd.asc(GuangqiWonderfulShow.WONDERFUL_SHOW_SORT);

        List<GuangqiWonderfulShow> guangqi_wonderful_showList = guangqiWonderfulShowDao.primaryKeyList(cnd);
        for (GuangqiWonderfulShow guangqi_wonderful_show : guangqi_wonderful_showList) {
            guangqi_wonderful_show.put(find(guangqi_wonderful_show.getWonderful_show_id()));
        }
        return guangqi_wonderful_showList;
    }

    public GuangqiWonderfulShow find(String wonderful_show_id) {
        GuangqiWonderfulShow guangqi_wonderful_show = CacheUtil.get(GUANGQI_WONDERFUL_SHOW_ITEM_CACHE, wonderful_show_id);

        if (guangqi_wonderful_show == null) {
            guangqi_wonderful_show = guangqiWonderfulShowDao.find(wonderful_show_id);

            CacheUtil.put(GUANGQI_WONDERFUL_SHOW_ITEM_CACHE, wonderful_show_id, guangqi_wonderful_show);
        }

        return guangqi_wonderful_show;
    }

    public Boolean save(GuangqiWonderfulShow guangqi_wonderful_show, String system_create_user_id) {
        Boolean success = guangqiWonderfulShowDao.save(guangqi_wonderful_show, system_create_user_id);
        return success;
    }

    public Boolean update(GuangqiWonderfulShow guangqi_wonderful_show, String wonderful_show_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiWonderfulShow.SYSTEM_STATUS, true);
        cnd.and(GuangqiWonderfulShow.WONDERFUL_SHOW_ID, wonderful_show_id);
        cnd.and(GuangqiWonderfulShow.SYSTEM_VERSION, system_version);

        Boolean success = guangqiWonderfulShowDao.update(guangqi_wonderful_show, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_WONDERFUL_SHOW_ITEM_CACHE, wonderful_show_id);
        }

        return success;
    }

    public Boolean delete(String wonderful_show_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiWonderfulShow.SYSTEM_STATUS, true);
        cnd.and(GuangqiWonderfulShow.WONDERFUL_SHOW_ID, wonderful_show_id);
        cnd.and(GuangqiWonderfulShow.SYSTEM_VERSION, system_version);

        Boolean success = guangqiWonderfulShowDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_WONDERFUL_SHOW_ITEM_CACHE, wonderful_show_id);
        }

        return success;
    }

}