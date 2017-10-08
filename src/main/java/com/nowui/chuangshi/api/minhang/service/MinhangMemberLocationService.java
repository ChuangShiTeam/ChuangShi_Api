package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangMemberLocationDao;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberLocation;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangMemberLocationService extends Service {

    public static final MinhangMemberLocationService instance = new MinhangMemberLocationService();
    private final String MINHANG_MEMBER_LOCATION_ITEM_CACHE = "minhang_member_location_item_cache";
    private final MinhangMemberLocationDao minhangMemberLocationDao = new MinhangMemberLocationDao();

    public Integer adminCount(String app_id, String member_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberLocation.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberLocation.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberLocation.MEMBER_ID, member_id);

        Integer count = minhangMemberLocationDao.count(cnd);
        return count;
    }

    public List<MinhangMemberLocation> adminList(String app_id, String member_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberLocation.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberLocation.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberLocation.MEMBER_ID, member_id);
        cnd.paginate(m, n);

        List<MinhangMemberLocation> minhang_member_locationList = minhangMemberLocationDao.primaryKeyList(cnd);
        for (MinhangMemberLocation minhang_member_location : minhang_member_locationList) {
            minhang_member_location.put(find(minhang_member_location.getMember_location_id()));
        }
        return minhang_member_locationList;
    }

    public MinhangMemberLocation find(String member_location_id) {
        MinhangMemberLocation minhang_member_location = CacheUtil.get(MINHANG_MEMBER_LOCATION_ITEM_CACHE, member_location_id);

        if (minhang_member_location == null) {
            minhang_member_location = minhangMemberLocationDao.find(member_location_id);

            CacheUtil.put(MINHANG_MEMBER_LOCATION_ITEM_CACHE, member_location_id, minhang_member_location);
        }

        return minhang_member_location;
    }

    public Boolean save(MinhangMemberLocation minhang_member_location, String system_create_user_id) {
        Boolean success = minhangMemberLocationDao.save(minhang_member_location, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangMemberLocation minhang_member_location, String member_location_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberLocation.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberLocation.MEMBER_LOCATION_ID, member_location_id);
        cnd.and(MinhangMemberLocation.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberLocationDao.update(minhang_member_location, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_LOCATION_ITEM_CACHE, member_location_id);
        }

        return success;
    }

    public Boolean delete(String member_location_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberLocation.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberLocation.MEMBER_LOCATION_ID, member_location_id);
        cnd.and(MinhangMemberLocation.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberLocationDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_LOCATION_ITEM_CACHE, member_location_id);
        }

        return success;
    }

}