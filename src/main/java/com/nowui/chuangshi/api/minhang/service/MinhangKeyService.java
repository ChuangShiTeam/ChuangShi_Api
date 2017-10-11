package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangKeyDao;
import com.nowui.chuangshi.api.minhang.model.MinhangKey;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangKeyService extends Service {

    public static final MinhangKeyService instance = new MinhangKeyService();
    private final String MINHANG_KEY_ITEM_CACHE = "minhang_key_item_cache";
    private final MinhangKeyDao minhangKeyDao = new MinhangKeyDao();

    public Integer adminCount(String app_id, String key_name) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangKey.SYSTEM_STATUS, true);
        cnd.and(MinhangKey.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangKey.KEY_NAME, key_name);

        Integer count = minhangKeyDao.count(cnd);
        return count;
    }

    public List<MinhangKey> adminList(String app_id, String key_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangKey.SYSTEM_STATUS, true);
        cnd.and(MinhangKey.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangKey.KEY_NAME, key_name);
        cnd.asc(MinhangKey.KEY_SORT);
        cnd.paginate(m, n);

        List<MinhangKey> minhang_keyList = minhangKeyDao.primaryKeyList(cnd);
        for (MinhangKey minhang_key : minhang_keyList) {
            minhang_key.put(find(minhang_key.getKey_id()));
        }
        return minhang_keyList;
    }
    
    public List<MinhangKey> appList(String app_id) {
    	Cnd cnd = new Cnd();
        cnd.where(MinhangKey.SYSTEM_STATUS, true);
        cnd.and(MinhangKey.APP_ID, app_id);
        cnd.asc(MinhangKey.KEY_SORT);

        List<MinhangKey> minhang_keyList = minhangKeyDao.primaryKeyList(cnd);
        for (MinhangKey minhang_key : minhang_keyList) {
            minhang_key.put(find(minhang_key.getKey_id()));
        }
        return minhang_keyList;
	}

    public MinhangKey find(String key_id) {
        MinhangKey minhang_key = CacheUtil.get(MINHANG_KEY_ITEM_CACHE, key_id);

        if (minhang_key == null) {
            minhang_key = minhangKeyDao.find(key_id);

            CacheUtil.put(MINHANG_KEY_ITEM_CACHE, key_id, minhang_key);
        }

        return minhang_key;
    }

    public Boolean save(MinhangKey minhang_key, String system_create_user_id) {
        Boolean success = minhangKeyDao.save(minhang_key, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangKey minhang_key, String key_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangKey.SYSTEM_STATUS, true);
        cnd.and(MinhangKey.KEY_ID, key_id);
        cnd.and(MinhangKey.SYSTEM_VERSION, system_version);

        Boolean success = minhangKeyDao.update(minhang_key, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_KEY_ITEM_CACHE, key_id);
        }

        return success;
    }

    public Boolean delete(String key_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangKey.SYSTEM_STATUS, true);
        cnd.and(MinhangKey.KEY_ID, key_id);
        cnd.and(MinhangKey.SYSTEM_VERSION, system_version);

        Boolean success = minhangKeyDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_KEY_ITEM_CACHE, key_id);
        }

        return success;
    }

}