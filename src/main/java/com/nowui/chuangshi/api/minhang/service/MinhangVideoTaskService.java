package com.nowui.chuangshi.api.minhang.service;

import java.util.ArrayList;
import java.util.List;

import com.nowui.chuangshi.api.minhang.dao.MinhangVideoTaskDao;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestion;
import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.api.minhang.model.MinhangVideoTask;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.type.MinhangTaskType;
import com.nowui.chuangshi.util.CacheUtil;

public class MinhangVideoTaskService extends Service {

    public static final MinhangVideoTaskService instance = new MinhangVideoTaskService();
    private final String MINHANG_VIDEO_TASK_ITEM_CACHE = "minhang_video_task_item_cache";
    private final MinhangVideoTaskDao minhangVideoTaskDao = new MinhangVideoTaskDao();

    public Integer adminCount(String app_id, String video_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideoTask.SYSTEM_STATUS, true);
        cnd.and(MinhangVideoTask.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangVideoTask.VIDEO_ID, video_id);

        Integer count = minhangVideoTaskDao.count(cnd);
        return count;
    }

    public List<MinhangVideoTask> adminList(String app_id, String video_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideoTask.SYSTEM_STATUS, true);
        cnd.and(MinhangVideoTask.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangVideoTask.VIDEO_ID, video_id);
        cnd.paginate(m, n);

        List<MinhangVideoTask> minhang_video_taskList = minhangVideoTaskDao.primaryKeyList(cnd);
        for (MinhangVideoTask minhang_video_task : minhang_video_taskList) {
            minhang_video_task.put(find(minhang_video_task.getVideo_task_id()));
        }
        return minhang_video_taskList;
    }
    
    public List<MinhangVideoTask> taskFind(String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideoTask.SYSTEM_STATUS, true);
        cnd.and(MinhangVideoTask.TASK_ID, task_id);
        
        List<MinhangVideoTask> minhang_video_taskList = minhangVideoTaskDao.primaryKeyList(cnd);
        for (MinhangVideoTask minhang_video_task : minhang_video_taskList) {
            minhang_video_task.put(find(minhang_video_task.getVideo_task_id()));
        }
        return minhang_video_taskList;
    }
    
    public List<MinhangVideoTask> videoList(String video_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangVideoTask.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(MinhangVideoTask.VIDEO_ID, video_id);

        List<MinhangVideoTask> minhang_video_taskList = minhangVideoTaskDao.primaryKeyList(cnd);
        for (MinhangVideoTask minhang_video_task : minhang_video_taskList) {
            minhang_video_task.put(find(minhang_video_task.getVideo_task_id()));
            MinhangTask minhangTask = MinhangTaskService.instance.find(minhang_video_task.getTask_id());
            minhang_video_task.put(MinhangTask.TASK_QRCODE_URL, minhangTask.getTask_qrcode_url());
            
            List<MinhangQuestion> minhang_question_list = new ArrayList<>();
            //查询任务对应的问题
            if (MinhangTaskType.QUESTION.getKey().equals(minhangTask.getTask_type())) {
                minhang_question_list = MinhangQuestionService.instance.taskList(minhangTask.getTask_id());
            }
            minhang_video_task.put(MinhangTask.QUESTION_LIST, minhang_question_list);
            minhang_video_task.put(MinhangTask.TASK_QRCODE_URL, MinhangTaskService.instance.find(minhang_video_task.getTask_id()).getTask_qrcode_url());
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