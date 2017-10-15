package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangPosterDao;
import com.nowui.chuangshi.api.minhang.model.MinhangPoster;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangPosterService extends Service {

    public static final MinhangPosterService instance = new MinhangPosterService();
    private final String MINHANG_POSTER_ITEM_CACHE = "minhang_poster_item_cache";
    private final MinhangPosterDao minhangPosterDao = new MinhangPosterDao();

    public Integer adminCount(String app_id, String poster_title) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPoster.SYSTEM_STATUS, true);
        cnd.and(MinhangPoster.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangPoster.POSTER_TITLE, poster_title);

        Integer count = minhangPosterDao.count(cnd);
        return count;
    }

    public List<MinhangPoster> adminList(String app_id, String poster_title, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPoster.SYSTEM_STATUS, true);
        cnd.and(MinhangPoster.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangPoster.POSTER_TITLE, poster_title);
        cnd.paginate(m, n);

        List<MinhangPoster> minhang_posterList = minhangPosterDao.primaryKeyList(cnd);
        for (MinhangPoster minhang_poster : minhang_posterList) {
            minhang_poster.put(find(minhang_poster.getPoster_id()));
        }
        return minhang_posterList;
    }
    
    public List<MinhangPoster> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPoster.SYSTEM_STATUS, true);
        cnd.and(MinhangPoster.APP_ID, app_id);

        List<MinhangPoster> minhang_posterList = minhangPosterDao.primaryKeyList(cnd);
        for (MinhangPoster minhang_poster : minhang_posterList) {
            minhang_poster.put(find(minhang_poster.getPoster_id()));
        }
        return minhang_posterList;
    }

    public MinhangPoster find(String poster_id) {
        MinhangPoster minhang_poster = CacheUtil.get(MINHANG_POSTER_ITEM_CACHE, poster_id);

        if (minhang_poster == null) {
            minhang_poster = minhangPosterDao.find(poster_id);

            CacheUtil.put(MINHANG_POSTER_ITEM_CACHE, poster_id, minhang_poster);
        }

        return minhang_poster;
    }
    
    public List<MinhangPoster> taskFind(String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPoster.SYSTEM_STATUS, true);
        cnd.and(MinhangPoster.TASK_ID, task_id);

        List<MinhangPoster> minhang_posterList = minhangPosterDao.primaryKeyList(cnd);
        for (MinhangPoster minhang_poster : minhang_posterList) {
            minhang_poster.put(find(minhang_poster.getPoster_id()));
        }
        return minhang_posterList;
    }

    public Boolean save(MinhangPoster minhang_poster, String system_create_user_id) {
        Boolean success = minhangPosterDao.save(minhang_poster, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangPoster minhang_poster, String poster_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPoster.SYSTEM_STATUS, true);
        cnd.and(MinhangPoster.POSTER_ID, poster_id);
        cnd.and(MinhangPoster.SYSTEM_VERSION, system_version);

        Boolean success = minhangPosterDao.update(minhang_poster, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_POSTER_ITEM_CACHE, poster_id);
        }

        return success;
    }

    public Boolean delete(String poster_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPoster.SYSTEM_STATUS, true);
        cnd.and(MinhangPoster.POSTER_ID, poster_id);
        cnd.and(MinhangPoster.SYSTEM_VERSION, system_version);

        Boolean success = minhangPosterDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_POSTER_ITEM_CACHE, poster_id);
        }

        return success;
    }

}