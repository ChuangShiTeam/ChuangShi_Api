package com.nowui.chuangshi.api.uni.service;

import com.nowui.chuangshi.api.uni.dao.UniApplyDao;
import com.nowui.chuangshi.api.uni.model.UniApply;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class UniApplyService extends Service {

    public static final UniApplyService instance = new UniApplyService();
    private final String UNI_APPLY_ITEM_CACHE = "uni_apply_item_cache";
    private final UniApplyDao uniApplyDao = new UniApplyDao();

    public Integer adminCount(String app_id, String apply_name, String apply_mobile) {
        Cnd cnd = new Cnd();
        cnd.where(UniApply.SYSTEM_STATUS, true);
        cnd.and(UniApply.APP_ID, app_id);
        cnd.andAllowEmpty(UniApply.APPLY_NAME, apply_name);
        cnd.andAllowEmpty(UniApply.APPLY_MOBILE, apply_mobile);

        Integer count = uniApplyDao.count(cnd);
        return count;
    }

    public List<UniApply> adminList(String app_id, String apply_name, String apply_mobile, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(UniApply.SYSTEM_STATUS, true);
        cnd.and(UniApply.APP_ID, app_id);
        cnd.andAllowEmpty(UniApply.APPLY_NAME, apply_name);
        cnd.andAllowEmpty(UniApply.APPLY_MOBILE, apply_mobile);
        cnd.paginate(m, n);

        List<UniApply> uni_applyList = uniApplyDao.primaryKeyList(cnd);
        for (UniApply uni_apply : uni_applyList) {
            uni_apply.put(find(uni_apply.getApply_id()));
        }
        return uni_applyList;
    }
    
    public UniApply mobileFind(String app_id, String apply_mobile) {
        Cnd cnd = new Cnd();
        cnd.where(UniApply.SYSTEM_STATUS, true);
        cnd.and(UniApply.APP_ID, app_id);
        cnd.andAllowEmpty(UniApply.APPLY_MOBILE, apply_mobile);

        List<UniApply> uni_applyList = uniApplyDao.primaryKeyList(cnd);
        if (uni_applyList == null  || uni_applyList.size() == 0) {
            return null;
        }
        return find(uni_applyList.get(0).getApply_id());
    }

    public UniApply find(String apply_id) {
        UniApply uni_apply = CacheUtil.get(UNI_APPLY_ITEM_CACHE, apply_id);

        if (uni_apply == null) {
            uni_apply = uniApplyDao.find(apply_id);

            CacheUtil.put(UNI_APPLY_ITEM_CACHE, apply_id, uni_apply);
        }

        return uni_apply;
    }

    public Boolean save(UniApply uni_apply, String system_create_user_id) {
        Boolean success = uniApplyDao.save(uni_apply, system_create_user_id);
        return success;
    }

    public Boolean update(UniApply uni_apply, String apply_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(UniApply.SYSTEM_STATUS, true);
        cnd.and(UniApply.APPLY_ID, apply_id);
        cnd.and(UniApply.SYSTEM_VERSION, system_version);

        Boolean success = uniApplyDao.update(uni_apply, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(UNI_APPLY_ITEM_CACHE, apply_id);
        }

        return success;
    }

    public Boolean delete(String apply_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(UniApply.SYSTEM_STATUS, true);
        cnd.and(UniApply.APPLY_ID, apply_id);
        cnd.and(UniApply.SYSTEM_VERSION, system_version);

        Boolean success = uniApplyDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(UNI_APPLY_ITEM_CACHE, apply_id);
        }

        return success;
    }

}