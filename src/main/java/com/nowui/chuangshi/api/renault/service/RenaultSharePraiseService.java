package com.nowui.chuangshi.api.renault.service;

import com.nowui.chuangshi.api.renault.dao.RenaultSharePraiseDao;
import com.nowui.chuangshi.api.renault.model.RenaultSharePraise;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class RenaultSharePraiseService extends Service {

    public static final RenaultSharePraiseService instance = new RenaultSharePraiseService();
    private final String RENAULT_SHARE_PRAISE_ITEM_CACHE = "renault_share_praise_item_cache";
    private final RenaultSharePraiseDao renaultSharePraiseDao = new RenaultSharePraiseDao();

    public Integer adminCount(String app_id, String share_id) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultSharePraise.SYSTEM_STATUS, true);
        cnd.and(RenaultSharePraise.APP_ID, app_id);
        cnd.andAllowEmpty(RenaultSharePraise.SHARE_ID, share_id);

        Integer count = renaultSharePraiseDao.count(cnd);
        return count;
    }

    public List<RenaultSharePraise> adminList(String app_id, String share_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultSharePraise.SYSTEM_STATUS, true);
        cnd.and(RenaultSharePraise.APP_ID, app_id);
        cnd.andAllowEmpty(RenaultSharePraise.SHARE_ID, share_id);
        cnd.paginate(m, n);

        List<RenaultSharePraise> renault_share_praiseList = renaultSharePraiseDao.primaryKeyList(cnd);
        for (RenaultSharePraise renault_share_praise : renault_share_praiseList) {
            renault_share_praise.put(find(renault_share_praise.getPraise_id()));
        }
        return renault_share_praiseList;
    }

    public RenaultSharePraise find(String praise_id) {
        RenaultSharePraise renault_share_praise = CacheUtil.get(RENAULT_SHARE_PRAISE_ITEM_CACHE, praise_id);

        if (renault_share_praise == null) {
            renault_share_praise = renaultSharePraiseDao.find(praise_id);

            CacheUtil.put(RENAULT_SHARE_PRAISE_ITEM_CACHE, praise_id, renault_share_praise);
        }

        return renault_share_praise;
    }

    public Boolean save(RenaultSharePraise renault_share_praise, String system_create_user_id) {
        Boolean success = renaultSharePraiseDao.save(renault_share_praise, system_create_user_id);
        return success;
    }

    public Boolean update(RenaultSharePraise renault_share_praise, String praise_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultSharePraise.SYSTEM_STATUS, true);
        cnd.and(RenaultSharePraise.PRAISE_ID, praise_id);
        cnd.and(RenaultSharePraise.SYSTEM_VERSION, system_version);

        Boolean success = renaultSharePraiseDao.update(renault_share_praise, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_SHARE_PRAISE_ITEM_CACHE, praise_id);
        }

        return success;
    }

    public Boolean delete(String praise_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultSharePraise.SYSTEM_STATUS, true);
        cnd.and(RenaultSharePraise.PRAISE_ID, praise_id);
        cnd.and(RenaultSharePraise.SYSTEM_VERSION, system_version);

        Boolean success = renaultSharePraiseDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_SHARE_PRAISE_ITEM_CACHE, praise_id);
        }

        return success;
    }

}