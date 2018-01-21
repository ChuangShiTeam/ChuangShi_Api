package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangAffiantDao;
import com.nowui.chuangshi.api.minhang.model.MinhangAffiant;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangAffiantService extends Service {

    public static final MinhangAffiantService instance = new MinhangAffiantService();
    private final String MINHANG_AFFIANT_ITEM_CACHE = "minhang_affiant_item_cache";
    private final MinhangAffiantDao minhangAffiantDao = new MinhangAffiantDao();

    public Integer adminCount(String app_id, String affiant_name) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangAffiant.SYSTEM_STATUS, true);
        cnd.and(MinhangAffiant.APP_ID, app_id);
        cnd.andLikeAllowEmpty(MinhangAffiant.AFFIANT_NAME, affiant_name);

        Integer count = minhangAffiantDao.count(cnd);
        return count;
    }

    public List<MinhangAffiant> adminList(String app_id, String affiant_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangAffiant.SYSTEM_STATUS, true);
        cnd.and(MinhangAffiant.APP_ID, app_id);
        cnd.andLikeAllowEmpty(MinhangAffiant.AFFIANT_NAME, affiant_name);
        cnd.paginate(m, n);

        List<MinhangAffiant> minhang_affiantList = minhangAffiantDao.primaryKeyList(cnd);
        for (MinhangAffiant minhang_affiant : minhang_affiantList) {
            minhang_affiant.put(find(minhang_affiant.getAffiant_id()));
        }
        return minhang_affiantList;
    }
    
    public List<MinhangAffiant> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangAffiant.SYSTEM_STATUS, true);
        cnd.and(MinhangAffiant.APP_ID, app_id);
        cnd.asc(MinhangAffiant.AFFIANT_SORT);

        List<MinhangAffiant> minhang_affiantList = minhangAffiantDao.primaryKeyList(cnd);
        for (MinhangAffiant minhang_affiant : minhang_affiantList) {
            minhang_affiant.put(find(minhang_affiant.getAffiant_id()));
        }
        return minhang_affiantList;
    }

    public MinhangAffiant find(String affiant_id) {
        MinhangAffiant minhang_affiant = CacheUtil.get(MINHANG_AFFIANT_ITEM_CACHE, affiant_id);

        if (minhang_affiant == null) {
            minhang_affiant = minhangAffiantDao.find(affiant_id);

            CacheUtil.put(MINHANG_AFFIANT_ITEM_CACHE, affiant_id, minhang_affiant);
        }

        return minhang_affiant;
    }

    public Boolean save(MinhangAffiant minhang_affiant, String system_create_user_id) {
        Boolean success = minhangAffiantDao.save(minhang_affiant, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangAffiant minhang_affiant, String affiant_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangAffiant.SYSTEM_STATUS, true);
        cnd.and(MinhangAffiant.AFFIANT_ID, affiant_id);
        cnd.and(MinhangAffiant.SYSTEM_VERSION, system_version);

        Boolean success = minhangAffiantDao.update(minhang_affiant, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_AFFIANT_ITEM_CACHE, affiant_id);
        }

        return success;
    }

    public Boolean delete(String affiant_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangAffiant.SYSTEM_STATUS, true);
        cnd.and(MinhangAffiant.AFFIANT_ID, affiant_id);
        cnd.and(MinhangAffiant.SYSTEM_VERSION, system_version);

        Boolean success = minhangAffiantDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_AFFIANT_ITEM_CACHE, affiant_id);
        }

        return success;
    }

}