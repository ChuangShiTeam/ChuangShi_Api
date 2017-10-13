package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangTimelineEventDao;
import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.api.minhang.model.MinhangTimelineEvent;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangTimelineEventService extends Service {

    public static final MinhangTimelineEventService instance = new MinhangTimelineEventService();
    private final String MINHANG_TIMELINE_EVENT_ITEM_CACHE = "minhang_timeline_event_item_cache";
    private final MinhangTimelineEventDao minhangTimelineEventDao = new MinhangTimelineEventDao();

    public Integer adminCount(String app_id, String timeline_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTimelineEvent.SYSTEM_STATUS, true);
        cnd.and(MinhangTimelineEvent.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangTimelineEvent.TIMELINE_ID, timeline_id);

        Integer count = minhangTimelineEventDao.count(cnd);
        return count;
    }

    public List<MinhangTimelineEvent> adminList(String app_id, String timeline_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTimelineEvent.SYSTEM_STATUS, true);
        cnd.and(MinhangTimelineEvent.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangTimelineEvent.TIMELINE_ID, timeline_id);
        cnd.paginate(m, n);

        List<MinhangTimelineEvent> minhang_timeline_eventList = minhangTimelineEventDao.primaryKeyList(cnd);
        for (MinhangTimelineEvent minhang_timeline_event : minhang_timeline_eventList) {
            minhang_timeline_event.put(find(minhang_timeline_event.getTimeline_event_id()));
        }
        return minhang_timeline_eventList;
    }
    
    public List<MinhangTimelineEvent> timeLineList(String timeline_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTimelineEvent.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(MinhangTimelineEvent.TIMELINE_ID, timeline_id);

        List<MinhangTimelineEvent> minhang_timeline_eventList = minhangTimelineEventDao.primaryKeyList(cnd);
        for (MinhangTimelineEvent minhang_timeline_event : minhang_timeline_eventList) {
            minhang_timeline_event.put(find(minhang_timeline_event.getTimeline_event_id()));
            minhang_timeline_event.put(MinhangTask.TASK_QRCODE_URL, MinhangTaskService.instance.find(minhang_timeline_event.getTask_id()));
        }
        return minhang_timeline_eventList;
    }

    public MinhangTimelineEvent find(String timeline_event_id) {
        MinhangTimelineEvent minhang_timeline_event = CacheUtil.get(MINHANG_TIMELINE_EVENT_ITEM_CACHE, timeline_event_id);

        if (minhang_timeline_event == null) {
            minhang_timeline_event = minhangTimelineEventDao.find(timeline_event_id);

            CacheUtil.put(MINHANG_TIMELINE_EVENT_ITEM_CACHE, timeline_event_id, minhang_timeline_event);
        }

        return minhang_timeline_event;
    }

    public Boolean save(MinhangTimelineEvent minhang_timeline_event, String system_create_user_id) {
        Boolean success = minhangTimelineEventDao.save(minhang_timeline_event, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangTimelineEvent minhang_timeline_event, String timeline_event_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTimelineEvent.SYSTEM_STATUS, true);
        cnd.and(MinhangTimelineEvent.TIMELINE_EVENT_ID, timeline_event_id);
        cnd.and(MinhangTimelineEvent.SYSTEM_VERSION, system_version);

        Boolean success = minhangTimelineEventDao.update(minhang_timeline_event, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_TIMELINE_EVENT_ITEM_CACHE, timeline_event_id);
        }

        return success;
    }

    public Boolean delete(String timeline_event_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTimelineEvent.SYSTEM_STATUS, true);
        cnd.and(MinhangTimelineEvent.TIMELINE_EVENT_ID, timeline_event_id);
        cnd.and(MinhangTimelineEvent.SYSTEM_VERSION, system_version);

        Boolean success = minhangTimelineEventDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_TIMELINE_EVENT_ITEM_CACHE, timeline_event_id);
        }

        return success;
    }

}