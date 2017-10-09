package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangOathDao;
import com.nowui.chuangshi.api.minhang.model.MinhangOath;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangOathService extends Service {

    public static final MinhangOathService instance = new MinhangOathService();
    private final String MINHANG_OATH_ITEM_CACHE = "minhang_oath_item_cache";
    private final MinhangOathDao minhangOathDao = new MinhangOathDao();

    public Integer adminCount(String app_id, String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangOath.SYSTEM_STATUS, true);
        cnd.and(MinhangOath.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangOath.TASK_ID, task_id);

        Integer count = minhangOathDao.count(cnd);
        return count;
    }

    public List<MinhangOath> adminList(String app_id, String task_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangOath.SYSTEM_STATUS, true);
        cnd.and(MinhangOath.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangOath.TASK_ID, task_id);
        cnd.paginate(m, n);

        List<MinhangOath> minhang_oathList = minhangOathDao.primaryKeyList(cnd);
        for (MinhangOath minhang_oath : minhang_oathList) {
            minhang_oath.put(find(minhang_oath.getOath_id()));
        }
        return minhang_oathList;
    }

    public MinhangOath find(String oath_id) {
        MinhangOath minhang_oath = CacheUtil.get(MINHANG_OATH_ITEM_CACHE, oath_id);

        if (minhang_oath == null) {
            minhang_oath = minhangOathDao.find(oath_id);

            CacheUtil.put(MINHANG_OATH_ITEM_CACHE, oath_id, minhang_oath);
        }

        return minhang_oath;
    }

    public Boolean save(MinhangOath minhang_oath, String system_create_user_id) {
        Boolean success = minhangOathDao.save(minhang_oath, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangOath minhang_oath, String oath_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangOath.SYSTEM_STATUS, true);
        cnd.and(MinhangOath.OATH_ID, oath_id);
        cnd.and(MinhangOath.SYSTEM_VERSION, system_version);

        Boolean success = minhangOathDao.update(minhang_oath, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_OATH_ITEM_CACHE, oath_id);
        }

        return success;
    }

    public Boolean delete(String oath_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangOath.SYSTEM_STATUS, true);
        cnd.and(MinhangOath.OATH_ID, oath_id);
        cnd.and(MinhangOath.SYSTEM_VERSION, system_version);

        Boolean success = minhangOathDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_OATH_ITEM_CACHE, oath_id);
        }

        return success;
    }

}