package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangLocationDao;
import com.nowui.chuangshi.api.minhang.model.MinhangLocation;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangLocationService extends Service {

    public static final MinhangLocationService instance = new MinhangLocationService();
    private final String MINHANG_LOCATION_ITEM_CACHE = "minhang_location_item_cache";
    private final MinhangLocationDao minhangLocationDao = new MinhangLocationDao();

    public Integer adminCount(String app_id, String location_title) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangLocation.SYSTEM_STATUS, true);
        cnd.and(MinhangLocation.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangLocation.LOCATION_TITLE, location_title);

        Integer count = minhangLocationDao.count(cnd);
        return count;
    }

    public List<MinhangLocation> adminList(String app_id, String location_title, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangLocation.SYSTEM_STATUS, true);
        cnd.and(MinhangLocation.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangLocation.LOCATION_TITLE, location_title);
        cnd.paginate(m, n);

        List<MinhangLocation> minhang_locationList = minhangLocationDao.primaryKeyList(cnd);
        for (MinhangLocation minhang_location : minhang_locationList) {
            minhang_location.put(find(minhang_location.getLocation_id()));
        }
        return minhang_locationList;
    }
    
    public List<MinhangLocation> taskList(String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangLocation.SYSTEM_STATUS, true);
        cnd.and(MinhangLocation.TASK_ID, task_id);

        List<MinhangLocation> minhang_locationList = minhangLocationDao.primaryKeyList(cnd);
        for (MinhangLocation minhang_location : minhang_locationList) {
            minhang_location.put(find(minhang_location.getLocation_id()));
        }
        return minhang_locationList;
    }

    public MinhangLocation find(String location_id) {
        MinhangLocation minhang_location = CacheUtil.get(MINHANG_LOCATION_ITEM_CACHE, location_id);

        if (minhang_location == null) {
            minhang_location = minhangLocationDao.find(location_id);

            CacheUtil.put(MINHANG_LOCATION_ITEM_CACHE, location_id, minhang_location);
        }

        return minhang_location;
    }

    public Boolean save(MinhangLocation minhang_location, String system_create_user_id) {
        Boolean success = minhangLocationDao.save(minhang_location, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangLocation minhang_location, String location_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangLocation.SYSTEM_STATUS, true);
        cnd.and(MinhangLocation.LOCATION_ID, location_id);
        cnd.and(MinhangLocation.SYSTEM_VERSION, system_version);

        Boolean success = minhangLocationDao.update(minhang_location, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_LOCATION_ITEM_CACHE, location_id);
        }

        return success;
    }

    public Boolean delete(String location_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangLocation.SYSTEM_STATUS, true);
        cnd.and(MinhangLocation.LOCATION_ID, location_id);
        cnd.and(MinhangLocation.SYSTEM_VERSION, system_version);

        Boolean success = minhangLocationDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_LOCATION_ITEM_CACHE, location_id);
        }

        return success;
    }

}