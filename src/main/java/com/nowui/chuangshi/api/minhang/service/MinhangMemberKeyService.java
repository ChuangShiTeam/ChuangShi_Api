package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangMemberKeyDao;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberKey;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MinhangMemberKeyService extends Service {

    public static final MinhangMemberKeyService instance = new MinhangMemberKeyService();
    private final String MINHANG_MEMBER_KEY_ITEM_CACHE = "minhang_member_key_item_cache";
    private final String MINHANG_MEMBER_KEY_ID_ITINERARY_LIST_CACHE = "minhang_member_key_id_itinerary_list_cache";
    private final MinhangMemberKeyDao minhangMemberKeyDao = new MinhangMemberKeyDao();

    public Integer adminCount(String app_id, String member_id, String key_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberKey.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberKey.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberKey.MEMBER_ID, member_id);
        cnd.andAllowEmpty(MinhangMemberKey.KEY_ID, key_id);

        Integer count = minhangMemberKeyDao.count(cnd);
        return count;
    }

    public List<MinhangMemberKey> adminList(String app_id, String member_id, String key_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberKey.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberKey.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberKey.MEMBER_ID, member_id);
        cnd.andAllowEmpty(MinhangMemberKey.KEY_ID, key_id);
        cnd.paginate(m, n);

        List<MinhangMemberKey> minhang_member_keyList = minhangMemberKeyDao.primaryKeyList(cnd);
        for (MinhangMemberKey minhang_member_key : minhang_member_keyList) {
            minhang_member_key.put(find(minhang_member_key.getMember_key_id()));
        }
        return minhang_member_keyList;
    }
    
    public MinhangMemberKey keyAndItineraryFind(String key_id, String member_itinerary_id) {
        List<MinhangMemberKey> minhang_member_keyList = itineraryList(member_itinerary_id);
        
        for (MinhangMemberKey minhangMemberKey : minhang_member_keyList) {
            if (key_id.equals(minhangMemberKey.getKey_id())) {
                return minhangMemberKey;
            }
        }
        return null;
    }
    
    public List<MinhangMemberKey> itineraryList(String member_itinerary_id) {
        List<String> minhang_member_key_idList = CacheUtil.get(MINHANG_MEMBER_KEY_ID_ITINERARY_LIST_CACHE, member_itinerary_id);
            
        if (minhang_member_key_idList == null) {
            Cnd cnd = new Cnd();
            cnd.where(MinhangMemberKey.SYSTEM_STATUS, true);
            cnd.andAllowEmpty(MinhangMemberKey.MEMBER_ITINERARY_ID, member_itinerary_id);

            List<MinhangMemberKey> minhang_member_keyList = minhangMemberKeyDao.primaryKeyList(cnd);
            
            minhang_member_key_idList = minhang_member_keyList.stream()
                                                            .map(minhang_member_key -> minhang_member_key.getMember_key_id())
                                                            .collect(Collectors.toList());
        }
        
        List<MinhangMemberKey> list = new ArrayList<>();
        for (String minhang_member_key_id : minhang_member_key_idList) {
            list.add(find(minhang_member_key_id));
        }
        return list;
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

}