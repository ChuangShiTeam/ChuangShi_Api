package com.nowui.chuangshi.api.gezhouba.service;

import com.nowui.chuangshi.api.gezhouba.dao.GezhoubaStockinfoDao;
import com.nowui.chuangshi.api.gezhouba.model.GezhoubaStockinfo;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class GezhoubaStockinfoService extends Service {

    public static final GezhoubaStockinfoService instance = new GezhoubaStockinfoService();
    private final String GEZHOUBA_STOCKINFO_ITEM_CACHE = "gezhouba_stockinfo_item_cache";
    private final GezhoubaStockinfoDao gezhoubaStockinfoDao = new GezhoubaStockinfoDao();

    public Integer adminCount(String app_id, String supplier_id, String product_id) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaStockinfo.SYSTEM_STATUS, true);
        cnd.and(GezhoubaStockinfo.APP_ID, app_id);
        cnd.andAllowEmpty(GezhoubaStockinfo.SUPPLIER_ID, supplier_id);
        cnd.andAllowEmpty(GezhoubaStockinfo.PRODUCT_ID, product_id);

        Integer count = gezhoubaStockinfoDao.count(cnd);
        return count;
    }

    public List<GezhoubaStockinfo> adminList(String app_id, String supplier_id, String product_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaStockinfo.SYSTEM_STATUS, true);
        cnd.and(GezhoubaStockinfo.APP_ID, app_id);
        cnd.andAllowEmpty(GezhoubaStockinfo.SUPPLIER_ID, supplier_id);
        cnd.andAllowEmpty(GezhoubaStockinfo.PRODUCT_ID, product_id);
        cnd.paginate(m, n);

        List<GezhoubaStockinfo> gezhouba_stockinfoList = gezhoubaStockinfoDao.primaryKeyList(cnd);
        for (GezhoubaStockinfo gezhouba_stockinfo : gezhouba_stockinfoList) {
            gezhouba_stockinfo.put(find(gezhouba_stockinfo.getStock_id()));
        }
        return gezhouba_stockinfoList;
    }

    public GezhoubaStockinfo find(String stock_id) {
        GezhoubaStockinfo gezhouba_stockinfo = CacheUtil.get(GEZHOUBA_STOCKINFO_ITEM_CACHE, stock_id);

        if (gezhouba_stockinfo == null) {
            gezhouba_stockinfo = gezhoubaStockinfoDao.find(stock_id);

            CacheUtil.put(GEZHOUBA_STOCKINFO_ITEM_CACHE, stock_id, gezhouba_stockinfo);
        }

        return gezhouba_stockinfo;
    }

    public Boolean save(GezhoubaStockinfo gezhouba_stockinfo, String system_create_user_id) {
        Boolean success = gezhoubaStockinfoDao.save(gezhouba_stockinfo, system_create_user_id);
        return success;
    }

    public Boolean update(GezhoubaStockinfo gezhouba_stockinfo, String stock_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaStockinfo.SYSTEM_STATUS, true);
        cnd.and(GezhoubaStockinfo.STOCK_ID, stock_id);
        cnd.and(GezhoubaStockinfo.SYSTEM_VERSION, system_version);

        Boolean success = gezhoubaStockinfoDao.update(gezhouba_stockinfo, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GEZHOUBA_STOCKINFO_ITEM_CACHE, stock_id);
        }

        return success;
    }

    public Boolean delete(String stock_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaStockinfo.SYSTEM_STATUS, true);
        cnd.and(GezhoubaStockinfo.STOCK_ID, stock_id);
        cnd.and(GezhoubaStockinfo.SYSTEM_VERSION, system_version);

        Boolean success = gezhoubaStockinfoDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GEZHOUBA_STOCKINFO_ITEM_CACHE, stock_id);
        }

        return success;
    }

}