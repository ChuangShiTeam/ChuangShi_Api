package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangVideoTaskDao;
import com.nowui.chuangshi.api.minhang.model.MinhangVideoTask;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangVideoTaskService extends Service {

    public static final MinhangVideoTaskService instance = new MinhangVideoTaskService();
    private final String MINHANG_VIDEO_TASK_ITEM_CACHE = "minhang_video_task_item_cache";
    private final MinhangVideoTaskDao minhangVideoTaskDao = new MinhangVideoTaskDao();

    public Integer adminCount(String app_id, String video_id, String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideoTask.SYSTEM_STATUS, true);
        cnd.and(MinhangVideoTask.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangVideoTask.VIDEO_ID, video_id);
        cnd.andAllowEmpty(MinhangVideoTask.TASK_ID, task_id);

        Integer count = minhangVideoTaskDao.count(cnd);
        return count;
    }

    public List<MinhangVideoTask> adminList(String app_id, String video_id, String task_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideoTask.SYSTEM_STATUS, true);
        cnd.and(MinhangVideoTask.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangVideoTask.VIDEO_ID, video_id);
        cnd.andAllowEmpty(MinhangVideoTask.TASK_ID, task_id);
        cnd.paginate(m, n);

        List<MinhangVideoTask> minhang_video_taskList = minhangVideoTaskDao.primaryKeyList(cnd);
        for (MinhangVideoTask minhang_video_task : minhang_video_taskList) {
            minhang_video_task.put(find(minhang_video_task.getVideo_task_id()));
        }
        return minhang_video_taskList;
    }

    public MinhangVideoTask find(String video_task_id) {
        MinhangVideoTask minhang_video_task = CacheUtil.get(MINHANG_VIDEO_TASK_ITEM_CACHE, video_task_id);

        if (minhang_video_task == null) {
            minhang_video_task = minhangVideoTaskDao.find(video_task_id);

            CacheUtil.put(MINHANG_VIDEO_TASK_ITEM_CACHE, video_task_id, minhang_video_task);
        }

        return minhang_video_task;
    }

    public Boolean save(MinhangVideoTask minhang_video_task, String system_create_user_id) {
        Boolean success = minhangVideoTaskDao.save(minhang_video_task, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangVideoTask minhang_video_task, String video_task_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideoTask.SYSTEM_STATUS, true);
        cnd.and(MinhangVideoTask.VIDEO_TASK_ID, video_task_id);
        cnd.and(MinhangVideoTask.SYSTEM_VERSION, system_version);

        Boolean success = minhangVideoTaskDao.update(minhang_video_task, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_VIDEO_TASK_ITEM_CACHE, video_task_id);
        }

        return success;
    }

    public Boolean delete(String video_task_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideoTask.SYSTEM_STATUS, true);
        cnd.and(MinhangVideoTask.VIDEO_TASK_ID, video_task_id);
        cnd.and(MinhangVideoTask.SYSTEM_VERSION, system_version);

        Boolean success = minhangVideoTaskDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_VIDEO_TASK_ITEM_CACHE, video_task_id);
        }

        return success;
    }

}