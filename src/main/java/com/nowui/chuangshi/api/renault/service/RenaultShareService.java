package com.nowui.chuangshi.api.renault.service;

import com.nowui.chuangshi.api.renault.dao.RenaultShareDao;
import com.nowui.chuangshi.api.renault.model.RenaultShare;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class RenaultShareService extends Service {

    public static final RenaultShareService instance = new RenaultShareService();
    private final String RENAULT_SHARE_ITEM_CACHE = "renault_share_item_cache";
    private final RenaultShareDao renaultShareDao = new RenaultShareDao();

    public Integer adminCount(String app_id, String remark) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShare.SYSTEM_STATUS, true);
        cnd.and(RenaultShare.APP_ID, app_id);
        cnd.andAllowEmpty(RenaultShare.REMARK, remark);

        Integer count = renaultShareDao.count(cnd);
        return count;
    }

    public List<RenaultShare> adminList(String app_id, String remark, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShare.SYSTEM_STATUS, true);
        cnd.and(RenaultShare.APP_ID, app_id);
        cnd.andAllowEmpty(RenaultShare.REMARK, remark);
        cnd.paginate(m, n);

        List<RenaultShare> renault_shareList = renaultShareDao.primaryKeyList(cnd);
        for (RenaultShare renault_share : renault_shareList) {
            renault_share.put(find(renault_share.getShare_id()));
        }
        return renault_shareList;
    }

    public RenaultShare find(String share_id) {
        RenaultShare renault_share = CacheUtil.get(RENAULT_SHARE_ITEM_CACHE, share_id);

        if (renault_share == null) {
            renault_share = renaultShareDao.find(share_id);

            CacheUtil.put(RENAULT_SHARE_ITEM_CACHE, share_id, renault_share);
        }

        return renault_share;
    }

    public Boolean save(RenaultShare renault_share, String system_create_user_id) {
        Boolean success = renaultShareDao.save(renault_share, system_create_user_id);
        return success;
    }

    public Boolean update(RenaultShare renault_share, String share_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShare.SYSTEM_STATUS, true);
        cnd.and(RenaultShare.SHARE_ID, share_id);
        cnd.and(RenaultShare.SYSTEM_VERSION, system_version);

        Boolean success = renaultShareDao.update(renault_share, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_SHARE_ITEM_CACHE, share_id);
        }

        return success;
    }

    public Boolean delete(String share_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShare.SYSTEM_STATUS, true);
        cnd.and(RenaultShare.SHARE_ID, share_id);
        cnd.and(RenaultShare.SYSTEM_VERSION, system_version);

        Boolean success = renaultShareDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_SHARE_ITEM_CACHE, share_id);
        }

        return success;
    }

    public List<RenaultShare> mobileList(String app_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShare.SYSTEM_STATUS, true);
        cnd.and(RenaultShare.APP_ID, app_id);
        cnd.desc(RenaultShare.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);

        List<RenaultShare> renaultshareList = renaultShareDao.primaryKeyList(cnd);
        for (RenaultShare renaultshareCategory : renaultshareList) {
            renaultshareCategory.put(find(renaultshareCategory.getShare_id()));
        }
        return renaultshareList;
    }


}