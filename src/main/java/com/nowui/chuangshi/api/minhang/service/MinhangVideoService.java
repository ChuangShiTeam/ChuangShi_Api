package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangVideoDao;
import com.nowui.chuangshi.api.minhang.model.MinhangVideo;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangVideoService extends Service {

    public static final MinhangVideoService instance = new MinhangVideoService();
    private final String MINHANG_VIDEO_ITEM_CACHE = "minhang_video_item_cache";
    private final MinhangVideoDao minhangVideoDao = new MinhangVideoDao();

    public Integer adminCount(String app_id, String video_title) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideo.SYSTEM_STATUS, true);
        cnd.and(MinhangVideo.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangVideo.VIDEO_TITLE, video_title);

        Integer count = minhangVideoDao.count(cnd);
        return count;
    }

    public List<MinhangVideo> adminList(String app_id, String video_title, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideo.SYSTEM_STATUS, true);
        cnd.and(MinhangVideo.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangVideo.VIDEO_TITLE, video_title);
        cnd.paginate(m, n);

        List<MinhangVideo> minhang_videoList = minhangVideoDao.primaryKeyList(cnd);
        for (MinhangVideo minhang_video : minhang_videoList) {
            minhang_video.put(find(minhang_video.getVideo_id()));
        }
        return minhang_videoList;
    }
    
    public Integer mobileCount(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideo.SYSTEM_STATUS, true);
        cnd.and(MinhangVideo.APP_ID, app_id);

        Integer count = minhangVideoDao.count(cnd);
        return count;
    }
    
    public List<MinhangVideo> mobileList(String app_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideo.SYSTEM_STATUS, true);
        cnd.and(MinhangVideo.APP_ID, app_id);
        cnd.asc(MinhangVideo.VIDEO_SORT);
        cnd.paginate(m, n);

        List<MinhangVideo> minhang_videoList = minhangVideoDao.primaryKeyList(cnd);
        for (MinhangVideo minhang_video : minhang_videoList) {
            minhang_video.put(find(minhang_video.getVideo_id()));
        }
        return minhang_videoList;
    }

    public MinhangVideo find(String video_id) {
        MinhangVideo minhang_video = CacheUtil.get(MINHANG_VIDEO_ITEM_CACHE, video_id);

        if (minhang_video == null) {
            minhang_video = minhangVideoDao.find(video_id);

            CacheUtil.put(MINHANG_VIDEO_ITEM_CACHE, video_id, minhang_video);
        }

        return minhang_video;
    }

    public Boolean save(MinhangVideo minhang_video, String system_create_user_id) {
        Boolean success = minhangVideoDao.save(minhang_video, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangVideo minhang_video, String video_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideo.SYSTEM_STATUS, true);
        cnd.and(MinhangVideo.VIDEO_ID, video_id);
        cnd.and(MinhangVideo.SYSTEM_VERSION, system_version);

        Boolean success = minhangVideoDao.update(minhang_video, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_VIDEO_ITEM_CACHE, video_id);
        }

        return success;
    }

    public Boolean delete(String video_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideo.SYSTEM_STATUS, true);
        cnd.and(MinhangVideo.VIDEO_ID, video_id);
        cnd.and(MinhangVideo.SYSTEM_VERSION, system_version);

        Boolean success = minhangVideoDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_VIDEO_ITEM_CACHE, video_id);
        }

        return success;
    }

}