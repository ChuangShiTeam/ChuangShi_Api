package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangMemberKeyDao;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberKey;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangMemberKeyService extends Service {

    public static final MinhangMemberKeyService instance = new MinhangMemberKeyService();
    private final String MINHANG_MEMBER_KEY_ITEM_CACHE = "minhang_member_key_item_cache";
    private final MinhangMemberKeyDao minhangMemberKeyDao = new MinhangMemberKeyDao();

    public Integer adminCount(String app_id, String member_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberKey.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberKey.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberKey.MEMBER_ID, member_id);

        Integer count = minhangMemberKeyDao.count(cnd);
        return count;
    }

    public List<MinhangMemberKey> adminList(String app_id, String member_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberKey.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberKey.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberKey.MEMBER_ID, member_id);
        cnd.paginate(m, n);

        List<MinhangMemberKey> minhang_member_keyList = minhangMemberKeyDao.primaryKeyList(cnd);
        for (MinhangMemberKey minhang_member_key : minhang_member_keyList) {
            minhang_member_key.put(find(minhang_member_key.getMember_key_id()));
        }
        return minhang_member_keyList;
    }

    public MinhangMemberKey find(String member_key_id) {
        MinhangMemberKey minhang_member_key = CacheUtil.get(MINHANG_MEMBER_KEY_ITEM_CACHE, member_key_id);

        if (minhang_member_key == null) {
            minhang_member_key = minhangMemberKeyDao.find(member_key_id);

            CacheUtil.put(MINHANG_MEMBER_KEY_ITEM_CACHE, member_key_id, minhang_member_key);
        }

        return minhang_member_key;
    }

    public Boolean save(MinhangMemberKey minhang_member_key, String system_create_user_id) {
        Boolean success = minhangMemberKeyDao.save(minhang_member_key, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangMemberKey minhang_member_key, String member_key_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberKey.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberKey.MEMBER_KEY_ID, member_key_id);
        cnd.and(MinhangMemberKey.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberKeyDao.update(minhang_member_key, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_KEY_ITEM_CACHE, member_key_id);
        }

        return success;
    }

    public Boolean delete(String member_key_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberKey.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberKey.MEMBER_KEY_ID, member_key_id);
        cnd.and(MinhangMemberKey.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberKeyDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_KEY_ITEM_CACHE, member_key_id);
        }

        return success;
    }

}