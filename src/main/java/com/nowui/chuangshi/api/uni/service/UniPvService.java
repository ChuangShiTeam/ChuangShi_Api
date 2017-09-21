package com.nowui.chuangshi.api.uni.service;

import java.util.List;

import com.nowui.chuangshi.api.uni.dao.UniPvDao;
import com.nowui.chuangshi.api.uni.model.UniPv;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class UniPvService extends Service {

    public static final UniPvService instance = new UniPvService();
    private final String UNI_PV_ITEM_CACHE = "uni_pv_item_cache";
    private final UniPvDao uniPvDao = new UniPvDao();

    public Integer adminCount(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(UniPv.SYSTEM_STATUS, true);
        cnd.and(UniPv.APP_ID, app_id);

        Integer count = uniPvDao.count(cnd);
        return count;
    }

    public List<UniPv> adminList(String app_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(UniPv.SYSTEM_STATUS, true);
        cnd.and(UniPv.APP_ID, app_id);
        cnd.paginate(m, n);

        List<UniPv> uni_pvList = uniPvDao.primaryKeyList(cnd);
        for (UniPv uni_pv : uni_pvList) {
            uni_pv.put(find(uni_pv.getApp_id()));
        }
        return uni_pvList;
    }

    public UniPv find(String app_id) {
        UniPv uni_pv = CacheUtil.get(UNI_PV_ITEM_CACHE, app_id);

        if (uni_pv == null) {
            uni_pv = uniPvDao.find(app_id);

            CacheUtil.put(UNI_PV_ITEM_CACHE, app_id, uni_pv);
        }

        return uni_pv;
    }

    public Boolean save(UniPv uni_pv, String system_create_user_id) {
        Boolean success = uniPvDao.save(uni_pv, system_create_user_id);
        return success;
    }

    public Boolean update(UniPv uni_pv, String app_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(UniPv.SYSTEM_STATUS, true);
        cnd.and(UniPv.APP_ID, app_id);
        cnd.and(UniPv.SYSTEM_VERSION, system_version);

        Boolean success = uniPvDao.update(uni_pv, system_update_user_id, system_version, cnd);

        if (success) {
            UniPv bean = new UniPv();
            bean.setApp_id(app_id);
            bean.setPv(uni_pv.getPv());
            bean.setSystem_create_user_id(uni_pv.getSystem_create_user_id());
            bean.setSystem_create_time(uni_pv.getSystem_create_time());
            bean.setSystem_update_user_id(uni_pv.getSystem_update_user_id());
            bean.setSystem_update_time(uni_pv.getSystem_update_time());
            bean.setSystem_version(uni_pv.getSystem_version());
            bean.setSystem_status(uni_pv.getSystem_status());
            
            CacheUtil.put(UNI_PV_ITEM_CACHE, app_id, bean);
        }

        return success;
    }

    public Boolean delete(String app_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(UniPv.SYSTEM_STATUS, true);
        cnd.and(UniPv.APP_ID, app_id);
        cnd.and(UniPv.SYSTEM_VERSION, system_version);

        Boolean success = uniPvDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(UNI_PV_ITEM_CACHE, app_id);
        }

        return success;
    }

}