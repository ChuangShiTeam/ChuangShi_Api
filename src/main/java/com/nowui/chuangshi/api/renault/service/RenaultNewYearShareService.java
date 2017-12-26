package com.nowui.chuangshi.api.renault.service;

import java.util.List;

import com.nowui.chuangshi.api.renault.dao.RenaultNewYearShareDao;
import com.nowui.chuangshi.api.renault.model.RenaultNewYearShare;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class RenaultNewYearShareService extends Service {

    public static final RenaultNewYearShareService instance = new RenaultNewYearShareService();
    private final String RENAULT_NEW_YEAR_SHARE_ITEM_CACHE = "renault_new_year_share_item_cache";
    private final RenaultNewYearShareDao renaultNewYearShareDao = new RenaultNewYearShareDao();

    public Integer adminCount(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultNewYearShare.SYSTEM_STATUS, true);
        cnd.and(RenaultNewYearShare.APP_ID, app_id);

        Integer count = renaultNewYearShareDao.count(cnd);
        return count;
    }

    public List<RenaultNewYearShare> adminList(String app_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultNewYearShare.SYSTEM_STATUS, true);
        cnd.and(RenaultNewYearShare.APP_ID, app_id);
        cnd.paginate(m, n);

        List<RenaultNewYearShare> renault_new_year_shareList = renaultNewYearShareDao.primaryKeyList(cnd);
        for (RenaultNewYearShare renault_new_year_share : renault_new_year_shareList) {
            renault_new_year_share.put(find(renault_new_year_share.getApp_id()));
        }
        return renault_new_year_shareList;
    }

    public RenaultNewYearShare find(String app_id) {
        RenaultNewYearShare renault_new_year_share = CacheUtil.get(RENAULT_NEW_YEAR_SHARE_ITEM_CACHE, app_id);

        if (renault_new_year_share == null) {
            renault_new_year_share = renaultNewYearShareDao.find(app_id);

            CacheUtil.put(RENAULT_NEW_YEAR_SHARE_ITEM_CACHE, app_id, renault_new_year_share);
        }

        return renault_new_year_share;
    }
    

    public Boolean save(RenaultNewYearShare renault_new_year_share, String system_create_user_id) {
        Boolean success = renaultNewYearShareDao.save(renault_new_year_share, system_create_user_id);
        return success;
    }

    public Boolean update(RenaultNewYearShare renault_new_year_share, String app_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultNewYearShare.SYSTEM_STATUS, true);
        cnd.and(RenaultNewYearShare.APP_ID, app_id);
        cnd.and(RenaultNewYearShare.SYSTEM_VERSION, system_version);

        Boolean success = renaultNewYearShareDao.update(renault_new_year_share, system_update_user_id, system_version, cnd);

        if (success) {
            RenaultNewYearShare bean = new RenaultNewYearShare();
            bean.setApp_id(app_id);
            bean.setNew_year_share_num(renault_new_year_share.getNew_year_share_num());
            bean.setSystem_create_user_id(renault_new_year_share.getSystem_create_user_id());
            bean.setSystem_create_time(renault_new_year_share.getSystem_create_time());
            bean.setSystem_update_user_id(renault_new_year_share.getSystem_update_user_id());
            bean.setSystem_update_time(renault_new_year_share.getSystem_update_time());
            bean.setSystem_version(renault_new_year_share.getSystem_version());
            bean.setSystem_status(renault_new_year_share.getSystem_status());
            
            CacheUtil.put(RENAULT_NEW_YEAR_SHARE_ITEM_CACHE, app_id, bean);
        }

        return success;
    }

    public Boolean delete(String app_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultNewYearShare.SYSTEM_STATUS, true);
        cnd.and(RenaultNewYearShare.APP_ID, app_id);
        cnd.and(RenaultNewYearShare.SYSTEM_VERSION, system_version);

        Boolean success = renaultNewYearShareDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_NEW_YEAR_SHARE_ITEM_CACHE, app_id);
        }

        return success;
    }

}