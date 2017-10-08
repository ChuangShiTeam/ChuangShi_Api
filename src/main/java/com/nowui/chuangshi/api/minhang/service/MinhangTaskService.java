package com.nowui.chuangshi.api.minhang.service;

import java.util.List;

import com.nowui.chuangshi.api.minhang.dao.MinhangTaskDao;
import com.nowui.chuangshi.api.minhang.model.MinhangKey;
import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class MinhangTaskService extends Service {

    public static final MinhangTaskService instance = new MinhangTaskService();
    private final String MINHANG_TASK_ITEM_CACHE = "minhang_task_item_cache";
    private final MinhangTaskDao minhangTaskDao = new MinhangTaskDao();

    public Integer adminCount(String app_id, String key_id, String task_name, String task_type) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTask.SYSTEM_STATUS, true);
        cnd.and(MinhangTask.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangTask.KEY_ID, key_id);
        cnd.andAllowEmpty(MinhangTask.TASK_NAME, task_name);
        cnd.andAllowEmpty(MinhangTask.TASK_TYPE, task_type);

        Integer count = minhangTaskDao.count(cnd);
        return count;
    }

    public List<MinhangTask> adminList(String app_id, String key_id, String task_name, String task_type, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.select(MinhangKey.TABLE_MINHANG_KEY + "." + MinhangKey.KEY_NAME);
        cnd.leftJoin(MinhangKey.TABLE_MINHANG_KEY, MinhangKey.KEY_ID, MinhangTask.TABLE_MINHANG_TASK, MinhangTask.KEY_ID);
        cnd.where(MinhangTask.TABLE_MINHANG_TASK + "." + MinhangTask.SYSTEM_STATUS, true);
        cnd.and(MinhangTask.TABLE_MINHANG_TASK + "." + MinhangTask.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangTask.TABLE_MINHANG_TASK + "." + MinhangTask.KEY_ID, key_id);
        cnd.andAllowEmpty(MinhangTask.TABLE_MINHANG_TASK + "." + MinhangTask.TASK_NAME, task_name);
        cnd.andAllowEmpty(MinhangTask.TABLE_MINHANG_TASK + "." + MinhangTask.TASK_TYPE, task_type);
        cnd.paginate(m, n);

        List<MinhangTask> minhang_taskList = minhangTaskDao.primaryKeyList(cnd);
        for (MinhangTask minhang_task : minhang_taskList) {
            minhang_task.put(find(minhang_task.getTask_id()));
        }
        return minhang_taskList;
    }

    public MinhangTask find(String task_id) {
        MinhangTask minhang_task = CacheUtil.get(MINHANG_TASK_ITEM_CACHE, task_id);

        if (minhang_task == null) {
            minhang_task = minhangTaskDao.find(task_id);

            CacheUtil.put(MINHANG_TASK_ITEM_CACHE, task_id, minhang_task);
        }

        return minhang_task;
    }

    public Boolean save(MinhangTask minhang_task, String system_create_user_id) {
        Boolean success = minhangTaskDao.save(minhang_task, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangTask minhang_task, String task_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTask.SYSTEM_STATUS, true);
        cnd.and(MinhangTask.TASK_ID, task_id);
        cnd.and(MinhangTask.SYSTEM_VERSION, system_version);

        Boolean success = minhangTaskDao.update(minhang_task, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_TASK_ITEM_CACHE, task_id);
        }

        return success;
    }

    public Boolean delete(String task_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangTask.SYSTEM_STATUS, true);
        cnd.and(MinhangTask.TASK_ID, task_id);
        cnd.and(MinhangTask.SYSTEM_VERSION, system_version);

        Boolean success = minhangTaskDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_TASK_ITEM_CACHE, task_id);
        }

        return success;
    }

}