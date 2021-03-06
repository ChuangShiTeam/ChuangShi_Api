package com.nowui.chuangshi.api.minhang.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nowui.chuangshi.api.minhang.dao.MinhangMemberTaskDao;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberTask;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class MinhangMemberTaskService extends Service {

    public static final MinhangMemberTaskService instance = new MinhangMemberTaskService();
    private final String MINHANG_MEMBER_TASK_ITEM_CACHE = "minhang_member_task_item_cache";
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
    
    public List<MinhangMemberTask> itineraryList(String member_itinerary_id) {
    	Cnd cnd = new Cnd();
        cnd.where(MinhangMemberTask.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(MinhangMemberTask.MEMBER_ITINERARY_ID, member_itinerary_id);
        cnd.asc(MinhangMemberTask.SYSTEM_CREATE_TIME);

        List<MinhangMemberTask> minhang_member_taskList = minhangMemberTaskDao.primaryKeyList(cnd);
        
        List<String> minhang_member_task_idList = minhang_member_taskList.stream()
                                                        .map(minhang_member_task -> minhang_member_task.getMember_task_id())
                                                        .collect(Collectors.toList());
        if (minhang_member_task_idList == null || minhang_member_task_idList.size() == 0) {
            return null;
        }
        List<MinhangMemberTask> list = new ArrayList<>();
        for (String minhang_member_task_id : minhang_member_task_idList) {
            list.add(find(minhang_member_task_id));
        }
        return list;
    }
    
    public List<MinhangMemberTask> historyList(String member_history_id) {
    	Cnd cnd = new Cnd();
        cnd.where(MinhangMemberTask.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(MinhangMemberTask.MEMBER_HISTORY_ID, member_history_id);
        cnd.asc(MinhangMemberTask.SYSTEM_CREATE_TIME);

        List<MinhangMemberTask> minhang_member_taskList = minhangMemberTaskDao.primaryKeyList(cnd);
        
        List<String>minhang_member_task_idList = minhang_member_taskList.stream()
                                                        .map(minhang_member_task -> minhang_member_task.getMember_task_id())
                                                        .collect(Collectors.toList());
        
        if (minhang_member_task_idList == null || minhang_member_task_idList.size() == 0) {
            return null;
        }
        List<MinhangMemberTask> list = new ArrayList<>();
        for (String minhang_member_task_id : minhang_member_task_idList) {
            list.add(find(minhang_member_task_id));
        }
        return list;
    }
    
    public List<MinhangMemberTask> keyAndItineraryList(String key_id, String member_history_id) {
    	
    	List<MinhangMemberTask> list = itineraryList(member_history_id);
    	
    	if (list != null && list.size() > 0) {
    		return list.stream().filter(minhangMemberTask -> key_id.equals(minhangMemberTask.getKey_id())).collect(Collectors.toList());
    	}
    	
    	return null;
    }
    
    public List<MinhangMemberTask> taskList(String task_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberTask.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(MinhangMemberTask.TASK_ID, task_id);
        cnd.desc(MinhangMemberTask.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);
        
        List<MinhangMemberTask> minhang_member_taskList = minhangMemberTaskDao.primaryKeyList(cnd);
        for (MinhangMemberTask minhang_member_task : minhang_member_taskList) {
            minhang_member_task.put(find(minhang_member_task.getMember_task_id()));
        }
        return minhang_member_taskList;
    }
    
    public MinhangMemberTask taskAndItineraryFind(String task_id, String member_itinerary_id) {
    	List<MinhangMemberTask> minhang_member_taskList = itineraryList(member_itinerary_id);
        
    	if (minhang_member_taskList != null && minhang_member_taskList.size() > 0) {
    	    for (MinhangMemberTask minhangMemberTask : minhang_member_taskList) {
                if (task_id.equals(minhangMemberTask.getTask_id())) {
                    return minhangMemberTask;
                }
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

}