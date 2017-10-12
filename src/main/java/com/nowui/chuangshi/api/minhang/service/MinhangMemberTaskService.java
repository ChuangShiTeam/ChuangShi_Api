package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangMemberTaskDao;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberTask;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MinhangMemberTaskService extends Service {

    public static final MinhangMemberTaskService instance = new MinhangMemberTaskService();
    private final String MINHANG_MEMBER_TASK_ITEM_CACHE = "minhang_member_task_item_cache";
    private final String MINHANG_MEMBER_TASK_ID_USER_LIST_CACHE = "minhang_member_task_id__user_list_cache";
    private final MinhangMemberTaskDao minhangMemberTaskDao = new MinhangMemberTaskDao();

    public Integer adminCount(String app_id, String member_id, String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberTask.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberTask.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberTask.MEMBER_ID, member_id);
        cnd.andAllowEmpty(MinhangMemberTask.TASK_ID, task_id);

        Integer count = minhangMemberTaskDao.count(cnd);
        return count;
    }

    public List<MinhangMemberTask> adminList(String app_id, String member_id, String task_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberTask.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberTask.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberTask.MEMBER_ID, member_id);
        cnd.andAllowEmpty(MinhangMemberTask.TASK_ID, task_id);
        cnd.paginate(m, n);

        List<MinhangMemberTask> minhang_member_taskList = minhangMemberTaskDao.primaryKeyList(cnd);
        for (MinhangMemberTask minhang_member_task : minhang_member_taskList) {
            minhang_member_task.put(find(minhang_member_task.getMember_task_id()));
        }
        return minhang_member_taskList;
    }

    public MinhangMemberTask find(String member_task_id) {
        MinhangMemberTask minhang_member_task = CacheUtil.get(MINHANG_MEMBER_TASK_ITEM_CACHE, member_task_id);

        if (minhang_member_task == null) {
            minhang_member_task = minhangMemberTaskDao.find(member_task_id);

            CacheUtil.put(MINHANG_MEMBER_TASK_ITEM_CACHE, member_task_id, minhang_member_task);
        }

        return minhang_member_task;
    }
    
    public List<MinhangMemberTask> userList(String user_id) {
    	List<String> minhang_member_task_idList = CacheUtil.get(MINHANG_MEMBER_TASK_ID_USER_LIST_CACHE, user_id);
        
        if (minhang_member_task_idList == null) {
            Cnd cnd = new Cnd();
            cnd.where(MinhangMemberTask.SYSTEM_STATUS, true);
            cnd.andAllowEmpty(MinhangMemberTask.USER_ID, user_id);

            List<MinhangMemberTask> minhang_member_taskList = minhangMemberTaskDao.primaryKeyList(cnd);
            
            minhang_member_task_idList = minhang_member_taskList.stream()
                                                            .map(minhang_member_task -> minhang_member_task.getMember_task_id())
                                                            .collect(Collectors.toList());
        }
        
        List<MinhangMemberTask> list = new ArrayList<>();
        for (String minhang_member_task_id : minhang_member_task_idList) {
            list.add(find(minhang_member_task_id));
        }
        return list;
    }
    
    public MinhangMemberTask userAndTaskFind(String user_id, String task_id) {
    	List<MinhangMemberTask> minhang_member_taskList = userList(user_id);
        
        for (MinhangMemberTask minhangMemberTask : minhang_member_taskList) {
            if (task_id.equals(minhangMemberTask.getTask_id())) {
                return minhangMemberTask;
            }
        }
        return null;
    	
    }

    public Boolean save(MinhangMemberTask minhang_member_task, String system_create_user_id) {
        Boolean success = minhangMemberTaskDao.save(minhang_member_task, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangMemberTask minhang_member_task, String member_task_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberTask.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberTask.MEMBER_TASK_ID, member_task_id);
        cnd.and(MinhangMemberTask.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberTaskDao.update(minhang_member_task, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_TASK_ITEM_CACHE, member_task_id);
        }

        return success;
    }

    public Boolean delete(String member_task_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberTask.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberTask.MEMBER_TASK_ID, member_task_id);
        cnd.and(MinhangMemberTask.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberTaskDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_TASK_ITEM_CACHE, member_task_id);
        }

        return success;
    }

}