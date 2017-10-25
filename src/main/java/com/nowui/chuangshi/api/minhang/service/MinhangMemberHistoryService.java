package com.nowui.chuangshi.api.minhang.service;

import java.util.List;

import com.nowui.chuangshi.api.minhang.dao.MinhangMemberHistoryDao;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberHistory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class MinhangMemberHistoryService extends Service {

    public static final MinhangMemberHistoryService instance = new MinhangMemberHistoryService();
    private final String MINHANG_MEMBER_HISTORY_ITEM_CACHE = "minhang_member_history_item_cache";
    private final MinhangMemberHistoryDao minhangMemberHistoryDao = new MinhangMemberHistoryDao();

    public Integer adminCount(String app_id, String user_id, String member_history_name) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberHistory.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberHistory.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberHistory.USER_ID, user_id);
        cnd.andAllowEmpty(MinhangMemberHistory.MEMBER_HISTORY_NAME, member_history_name);

        Integer count = minhangMemberHistoryDao.count(cnd);
        return count;
    }

    public List<MinhangMemberHistory> adminList(String app_id, String user_id, String member_history_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberHistory.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberHistory.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberHistory.USER_ID, user_id);
        cnd.andAllowEmpty(MinhangMemberHistory.MEMBER_HISTORY_NAME, member_history_name);
        cnd.paginate(m, n);

        List<MinhangMemberHistory> minhang_member_historyList = minhangMemberHistoryDao.primaryKeyList(cnd);
        for (MinhangMemberHistory minhang_member_history : minhang_member_historyList) {
            minhang_member_history.put(find(minhang_member_history.getMember_history_id()));
        }
        return minhang_member_historyList;
    }
    
    /**
     * 用户纪念册列表
     * @param user_id
     * @return
     */
    public List<MinhangMemberHistory> userList(String user_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberHistory.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(MinhangMemberHistory.USER_ID, user_id);
        cnd.desc(MinhangMemberHistory.SYSTEM_CREATE_TIME);

        List<MinhangMemberHistory> minhang_member_historyList = minhangMemberHistoryDao.primaryKeyList(cnd);
        for (MinhangMemberHistory minhang_member_history : minhang_member_historyList) {
            minhang_member_history.put(find(minhang_member_history.getMember_history_id()));
        }
        return minhang_member_historyList;
    }
    
    public MinhangMemberHistory find(String member_history_id) {
        MinhangMemberHistory minhang_member_history = CacheUtil.get(MINHANG_MEMBER_HISTORY_ITEM_CACHE, member_history_id);

        if (minhang_member_history == null) {
            minhang_member_history = minhangMemberHistoryDao.find(member_history_id);

            CacheUtil.put(MINHANG_MEMBER_HISTORY_ITEM_CACHE, member_history_id, minhang_member_history);
        }

        return minhang_member_history;
    }
    
    public MinhangMemberHistory itineraryFind(String member_itinerary_id) {
    	
    	Cnd cnd = new Cnd();
        cnd.where(MinhangMemberHistory.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(MinhangMemberHistory.MEMBER_ITINERARY_ID, member_itinerary_id);

        List<MinhangMemberHistory> minhang_member_historyList = minhangMemberHistoryDao.primaryKeyList(cnd);
        
        if (minhang_member_historyList == null || minhang_member_historyList.size() == 0) {
        	return null;
        }
        
    	return find(minhang_member_historyList.get(0).getMember_history_id());
    }

    public Boolean save(MinhangMemberHistory minhang_member_history, String system_create_user_id) {
        Boolean success = minhangMemberHistoryDao.save(minhang_member_history, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangMemberHistory minhang_member_history, String member_history_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberHistory.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberHistory.MEMBER_HISTORY_ID, member_history_id);
        cnd.and(MinhangMemberHistory.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberHistoryDao.update(minhang_member_history, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_HISTORY_ITEM_CACHE, member_history_id);
        }

        return success;
    }

    public Boolean delete(String member_history_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberHistory.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberHistory.MEMBER_HISTORY_ID, member_history_id);
        cnd.and(MinhangMemberHistory.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberHistoryDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_HISTORY_ITEM_CACHE, member_history_id);
        }

        return success;
    }
    
}