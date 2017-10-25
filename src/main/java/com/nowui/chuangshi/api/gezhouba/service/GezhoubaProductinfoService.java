package com.nowui.chuangshi.api.gezhouba.service;

import com.nowui.chuangshi.api.gezhouba.dao.GezhoubaProductinfoDao;
import com.nowui.chuangshi.api.gezhouba.model.GezhoubaProductinfo;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class GezhoubaProductinfoService extends Service {

    public static final GezhoubaProductinfoService instance = new GezhoubaProductinfoService();
    private final String GEZHOUBA_PRODUCTINFO_ITEM_CACHE = "gezhouba_productinfo_item_cache";
    private final GezhoubaProductinfoDao gezhoubaProductinfoDao = new GezhoubaProductinfoDao();

    public Integer adminCount(String app_id, String product_name) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaProductinfo.SYSTEM_STATUS, true);
        cnd.and(GezhoubaProductinfo.APP_ID, app_id);
        cnd.andAllowEmpty(GezhoubaProductinfo.PRODUCT_NAME, product_name);

        Integer count = gezhoubaProductinfoDao.count(cnd);
        return count;
    }

    public List<GezhoubaProductinfo> adminList(String app_id, String product_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaProductinfo.SYSTEM_STATUS, true);
        cnd.and(GezhoubaProductinfo.APP_ID, app_id);
        cnd.andAllowEmpty(GezhoubaProductinfo.PRODUCT_NAME, product_name);
        cnd.paginate(m, n);

        List<GezhoubaProductinfo> gezhouba_productinfoList = gezhoubaProductinfoDao.primaryKeyList(cnd);
        for (GezhoubaProductinfo gezhouba_productinfo : gezhouba_productinfoList) {
            gezhouba_productinfo.put(find(gezhouba_productinfo.getProduct_id()));
        }
        return gezhouba_productinfoList;
    }

    public GezhoubaProductinfo find(String product_id) {
        GezhoubaProductinfo gezhouba_productinfo = CacheUtil.get(GEZHOUBA_PRODUCTINFO_ITEM_CACHE, product_id);

        if (gezhouba_productinfo == null) {
            gezhouba_productinfo = gezhoubaProductinfoDao.find(product_id);

            CacheUtil.put(GEZHOUBA_PRODUCTINFO_ITEM_CACHE, product_id, gezhouba_productinfo);
        }

        return gezhouba_productinfo;
    }

    public Boolean save(GezhoubaProductinfo gezhouba_productinfo, String system_create_user_id) {
        Boolean success = gezhoubaProductinfoDao.save(gezhouba_productinfo, system_create_user_id);
        return success;
    }

    public Boolean update(GezhoubaProductinfo gezhouba_productinfo, String product_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaProductinfo.SYSTEM_STATUS, true);
        cnd.and(GezhoubaProductinfo.PRODUCT_ID, product_id);
        cnd.and(GezhoubaProductinfo.SYSTEM_VERSION, system_version);

        Boolean success = gezhoubaProductinfoDao.update(gezhouba_productinfo, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GEZHOUBA_PRODUCTINFO_ITEM_CACHE, product_id);
        }

        return success;
    }

    public Boolean delete(String product_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GezhoubaProductinfo.SYSTEM_STATUS, true);
        cnd.and(GezhoubaProductinfo.PRODUCT_ID, product_id);
        cnd.and(GezhoubaProductinfo.SYSTEM_VERSION, system_version);

        Boolean success = gezhoubaProductinfoDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GEZHOUBA_PRODUCTINFO_ITEM_CACHE, product_id);
        }

        return success;
    }

}