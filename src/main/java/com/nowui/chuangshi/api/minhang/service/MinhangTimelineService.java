package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangTimelineDao;
import com.nowui.chuangshi.api.minhang.model.MinhangTimeline;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangTimelineService extends Service {

    public static final MinhangTimelineService instance = new MinhangTimelineService();
    private final String MINHANG_TIMELINE_ITEM_CACHE = "minhang_timeline_item_cache";
    private final MinhangTimelineDao minhangTimelineDao = new MinhangTimelineDao();

    public Integer adminCount(String app_id, String timeline_year) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTimeline.SYSTEM_STATUS, true);
        cnd.and(MinhangTimeline.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangTimeline.TIMELINE_YEAR, timeline_year);

        Integer count = minhangTimelineDao.count(cnd);
        return count;
    }

    public List<MinhangTimeline> adminList(String app_id, String timeline_year, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTimeline.SYSTEM_STATUS, true);
        cnd.and(MinhangTimeline.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangTimeline.TIMELINE_YEAR, timeline_year);
        cnd.desc(MinhangTimeline.TIMELINE_YEAR);
        cnd.paginate(m, n);

        List<MinhangTimeline> minhang_timelineList = minhangTimelineDao.primaryKeyList(cnd);
        for (MinhangTimeline minhang_timeline : minhang_timelineList) {
            minhang_timeline.put(find(minhang_timeline.getTimeline_id()));
        }
        return minhang_timelineList;
    }
    
    public List<MinhangTimeline> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTimeline.SYSTEM_STATUS, true);
        cnd.and(MinhangTimeline.APP_ID, app_id);
        cnd.desc(MinhangTimeline.TIMELINE_YEAR);

        List<MinhangTimeline> minhang_timelineList = minhangTimelineDao.primaryKeyList(cnd);
        for (MinhangTimeline minhang_timeline : minhang_timelineList) {
            minhang_timeline.put(find(minhang_timeline.getTimeline_id()));
        }
        return minhang_timelineList;
    }

    public MinhangTimeline find(String timeline_id) {
        MinhangTimeline minhang_timeline = CacheUtil.get(MINHANG_TIMELINE_ITEM_CACHE, timeline_id);

        if (minhang_timeline == null) {
            minhang_timeline = minhangTimelineDao.find(timeline_id);

            CacheUtil.put(MINHANG_TIMELINE_ITEM_CACHE, timeline_id, minhang_timeline);
        }

        return minhang_timeline;
    }

    public Boolean save(MinhangTimeline minhang_timeline, String system_create_user_id) {
        Boolean success = minhangTimelineDao.save(minhang_timeline, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangTimeline minhang_timeline, String timeline_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTimeline.SYSTEM_STATUS, true);
        cnd.and(MinhangTimeline.TIMELINE_ID, timeline_id);
        cnd.and(MinhangTimeline.SYSTEM_VERSION, system_version);

        Boolean success = minhangTimelineDao.update(minhang_timeline, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_TIMELINE_ITEM_CACHE, timeline_id);
        }

        return success;
    }

    public Boolean delete(String timeline_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTimeline.SYSTEM_STATUS, true);
        cnd.and(MinhangTimeline.TIMELINE_ID, timeline_id);
        cnd.and(MinhangTimeline.SYSTEM_VERSION, system_version);

        Boolean success = minhangTimelineDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_TIMELINE_ITEM_CACHE, timeline_id);
        }

        return success;
    }

}