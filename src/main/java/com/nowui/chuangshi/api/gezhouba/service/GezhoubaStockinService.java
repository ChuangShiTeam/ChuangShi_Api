package com.nowui.chuangshi.api.gezhouba.service;

import com.nowui.chuangshi.api.gezhouba.dao.GezhoubaStockinDao;
import com.nowui.chuangshi.api.gezhouba.model.GezhoubaStockin;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class GezhoubaStockinService extends Service {

    public static final GezhoubaStockinService instance = new GezhoubaStockinService();
    private final String GEZHOUBA_STOCKIN_ITEM_CACHE = "gezhouba_stockin_item_cache";
    private final GezhoubaStockinDao gezhoubaStockinDao = new GezhoubaStockinDao();

    public Integer adminCount(String app_id, String stockin_no, String stockin_name) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaStockin.SYSTEM_STATUS, true);
        cnd.and(GezhoubaStockin.APP_ID, app_id);
        cnd.andAllowEmpty(GezhoubaStockin.STOCKIN_NO, stockin_no);
        cnd.andAllowEmpty(GezhoubaStockin.STOCKIN_NAME, stockin_name);

        Integer count = gezhoubaStockinDao.count(cnd);
        return count;
    }

    public List<GezhoubaStockin> adminList(String app_id, String stockin_no, String stockin_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaStockin.SYSTEM_STATUS, true);
        cnd.and(GezhoubaStockin.APP_ID, app_id);
        cnd.andAllowEmpty(GezhoubaStockin.STOCKIN_NO, stockin_no);
        cnd.andAllowEmpty(GezhoubaStockin.STOCKIN_NAME, stockin_name);
        cnd.paginate(m, n);

        List<GezhoubaStockin> gezhouba_stockinList = gezhoubaStockinDao.primaryKeyList(cnd);
        for (GezhoubaStockin gezhouba_stockin : gezhouba_stockinList) {
            gezhouba_stockin.put(find(gezhouba_stockin.getStockin_id()));
        }
        return gezhouba_stockinList;
    }

    public GezhoubaStockin find(String stockin_id) {
        GezhoubaStockin gezhouba_stockin = CacheUtil.get(GEZHOUBA_STOCKIN_ITEM_CACHE, stockin_id);

        if (gezhouba_stockin == null) {
            gezhouba_stockin = gezhoubaStockinDao.find(stockin_id);

            CacheUtil.put(GEZHOUBA_STOCKIN_ITEM_CACHE, stockin_id, gezhouba_stockin);
        }

        return gezhouba_stockin;
    }

    public Boolean save(GezhoubaStockin gezhouba_stockin, String system_create_user_id) {
        Boolean success = gezhoubaStockinDao.save(gezhouba_stockin, system_create_user_id);
        return success;
    }

    public Boolean update(GezhoubaStockin gezhouba_stockin, String stockin_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaStockin.SYSTEM_STATUS, true);
        cnd.and(GezhoubaStockin.STOCKIN_ID, stockin_id);
        cnd.and(GezhoubaStockin.SYSTEM_VERSION, system_version);

        Boolean success = gezhoubaStockinDao.update(gezhouba_stockin, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GEZHOUBA_STOCKIN_ITEM_CACHE, stockin_id);
        }

        return success;
    }

    public Boolean delete(String stockin_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaStockin.SYSTEM_STATUS, true);
        cnd.and(GezhoubaStockin.STOCKIN_ID, stockin_id);
        cnd.and(GezhoubaStockin.SYSTEM_VERSION, system_version);

        Boolean success = gezhoubaStockinDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GEZHOUBA_STOCKIN_ITEM_CACHE, stockin_id);
        }

        return success;
    }

}