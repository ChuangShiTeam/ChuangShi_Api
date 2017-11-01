package com.nowui.chuangshi.api.gezhouba.service;

import com.nowui.chuangshi.api.gezhouba.dao.GezhoubaStockinDetailDao;
import com.nowui.chuangshi.api.gezhouba.model.GezhoubaStockinDetail;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class GezhoubaStockinDetailService extends Service {

    public static final GezhoubaStockinDetailService instance = new GezhoubaStockinDetailService();
    private final String GEZHOUBA_STOCKIN_DETAIL_ITEM_CACHE = "gezhouba_stockin_detail_item_cache";
    private final GezhoubaStockinDetailDao gezhoubaStockinDetailDao = new GezhoubaStockinDetailDao();

    public Integer adminCount(String app_id, String product_name) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaStockinDetail.SYSTEM_STATUS, true);
        cnd.and(GezhoubaStockinDetail.APP_ID, app_id);
        cnd.andAllowEmpty(GezhoubaStockinDetail.PRODUCT_NAME, product_name);

        Integer count = gezhoubaStockinDetailDao.count(cnd);
        return count;
    }

    public List<GezhoubaStockinDetail> adminList(String app_id, String product_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaStockinDetail.SYSTEM_STATUS, true);
        cnd.and(GezhoubaStockinDetail.APP_ID, app_id);
        cnd.andAllowEmpty(GezhoubaStockinDetail.PRODUCT_NAME, product_name);
        cnd.paginate(m, n);

        List<GezhoubaStockinDetail> gezhouba_stockin_detailList = gezhoubaStockinDetailDao.primaryKeyList(cnd);
        for (GezhoubaStockinDetail gezhouba_stockin_detail : gezhouba_stockin_detailList) {
            gezhouba_stockin_detail.put(find(gezhouba_stockin_detail.getStockin_detail_id()));
        }
        return gezhouba_stockin_detailList;
    }

    public GezhoubaStockinDetail find(String stockin_detail_id) {
        GezhoubaStockinDetail gezhouba_stockin_detail = CacheUtil.get(GEZHOUBA_STOCKIN_DETAIL_ITEM_CACHE, stockin_detail_id);

        if (gezhouba_stockin_detail == null) {
            gezhouba_stockin_detail = gezhoubaStockinDetailDao.find(stockin_detail_id);

            CacheUtil.put(GEZHOUBA_STOCKIN_DETAIL_ITEM_CACHE, stockin_detail_id, gezhouba_stockin_detail);
        }

        return gezhouba_stockin_detail;
    }

    public Boolean save(GezhoubaStockinDetail gezhouba_stockin_detail, String system_create_user_id) {
        Boolean success = gezhoubaStockinDetailDao.save(gezhouba_stockin_detail, system_create_user_id);
        return success;
    }

    public Boolean update(GezhoubaStockinDetail gezhouba_stockin_detail, String stockin_detail_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaStockinDetail.SYSTEM_STATUS, true);
        cnd.and(GezhoubaStockinDetail.STOCKIN_DETAIL_ID, stockin_detail_id);
        cnd.and(GezhoubaStockinDetail.SYSTEM_VERSION, system_version);

        Boolean success = gezhoubaStockinDetailDao.update(gezhouba_stockin_detail, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GEZHOUBA_STOCKIN_DETAIL_ITEM_CACHE, stockin_detail_id);
        }

        return success;
    }

    public Boolean delete(String stockin_detail_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaStockinDetail.SYSTEM_STATUS, true);
        cnd.and(GezhoubaStockinDetail.STOCKIN_DETAIL_ID, stockin_detail_id);
        cnd.and(GezhoubaStockinDetail.SYSTEM_VERSION, system_version);

        Boolean success = gezhoubaStockinDetailDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GEZHOUBA_STOCKIN_DETAIL_ITEM_CACHE, stockin_detail_id);
        }

        return success;
    }

}