package com.nowui.chuangshi.api.minhang.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.nowui.chuangshi.api.minhang.dao.MinhangPartyHistoryDao;
import com.nowui.chuangshi.api.minhang.model.MinhangPartyHistory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class MinhangPartyHistoryService extends Service {

    public static final MinhangPartyHistoryService instance = new MinhangPartyHistoryService();
    private final String MINHANG_PARTY_HISTORY_ITEM_CACHE = "minhang_party_history_item_cache";
    private final MinhangPartyHistoryDao minhangPartyHistoryDao = new MinhangPartyHistoryDao();

    public Integer adminCount(String app_id, String book_code) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPartyHistory.SYSTEM_STATUS, true);
        cnd.and(MinhangPartyHistory.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangPartyHistory.BOOK_CODE, book_code);

        Integer count = minhangPartyHistoryDao.count(cnd);
        return count;
    }

    public List<MinhangPartyHistory> adminList(String app_id, String book_code, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPartyHistory.SYSTEM_STATUS, true);
        cnd.and(MinhangPartyHistory.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangPartyHistory.BOOK_CODE, book_code);
        cnd.paginate(m, n);

        List<MinhangPartyHistory> minhang_party_historyList = minhangPartyHistoryDao.primaryKeyList(cnd);
        for (MinhangPartyHistory minhang_party_history : minhang_party_historyList) {
            minhang_party_history.put(find(minhang_party_history.getParty_history_id()));
        }
        return minhang_party_historyList;
    }

    public MinhangPartyHistory find(String party_history_id) {
        MinhangPartyHistory minhang_party_history = CacheUtil.get(MINHANG_PARTY_HISTORY_ITEM_CACHE, party_history_id);

        if (minhang_party_history == null) {
            minhang_party_history = minhangPartyHistoryDao.find(party_history_id);

            CacheUtil.put(MINHANG_PARTY_HISTORY_ITEM_CACHE, party_history_id, minhang_party_history);
        }

        return minhang_party_history;
    }
    
    public List<MinhangPartyHistory> taskFind(String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPartyHistory.SYSTEM_STATUS, true);
        cnd.and(MinhangPartyHistory.TASK_ID, task_id);

        List<MinhangPartyHistory> minhang_party_historyList = minhangPartyHistoryDao.primaryKeyList(cnd);
        
        for (MinhangPartyHistory minhang_party_history : minhang_party_historyList) {
            minhang_party_history.put(find(minhang_party_history.getParty_history_id()));
        }        
        return minhang_party_historyList;
    }
    
    public MinhangPartyHistory randomFind(String app_id) {
    	Cnd cnd = new Cnd();
        cnd.where(MinhangPartyHistory.SYSTEM_STATUS, true);
        cnd.and(MinhangPartyHistory.APP_ID, app_id);

        List<MinhangPartyHistory> minhang_party_historyList = minhangPartyHistoryDao.primaryKeyList(cnd);
        
        if (minhang_party_historyList == null || minhang_party_historyList.size() == 0) {
        	return new MinhangPartyHistory();
        }
        
        MinhangPartyHistory minhang_party_history = minhang_party_historyList.get(new Random().nextInt(minhang_party_historyList.size()));
        
        return find(minhang_party_history.getParty_history_id());
    }

    public Boolean save(MinhangPartyHistory minhang_party_history, String system_create_user_id) {
        Boolean success = minhangPartyHistoryDao.save(minhang_party_history, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangPartyHistory minhang_party_history, String party_history_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPartyHistory.SYSTEM_STATUS, true);
        cnd.and(MinhangPartyHistory.PARTY_HISTORY_ID, party_history_id);
        cnd.and(MinhangPartyHistory.SYSTEM_VERSION, system_version);

        Boolean success = minhangPartyHistoryDao.update(minhang_party_history, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_PARTY_HISTORY_ITEM_CACHE, party_history_id);
        }

        return success;
    }

    public Boolean delete(String party_history_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangPartyHistory.SYSTEM_STATUS, true);
        cnd.and(MinhangPartyHistory.PARTY_HISTORY_ID, party_history_id);
        cnd.and(MinhangPartyHistory.SYSTEM_VERSION, system_version);

        Boolean success = minhangPartyHistoryDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_PARTY_HISTORY_ITEM_CACHE, party_history_id);
        }

        return success;
    }
    
    public MinhangPartyHistory prevHistory(String app_id, Date system_create_time) {
    	MinhangPartyHistory minhangPartyHistory = minhangPartyHistoryDao.prevHistory(app_id, system_create_time);
        if (minhangPartyHistory == null) {
            Cnd cnd = new Cnd();
            cnd.where(MinhangPartyHistory.SYSTEM_STATUS, true);
            cnd.and(MinhangPartyHistory.APP_ID, app_id);
            cnd.asc(MinhangPartyHistory.SYSTEM_CREATE_TIME);
            cnd.paginate(0, 1);
            
            List<MinhangPartyHistory> minhangPartyHistoryList = minhangPartyHistoryDao.list(cnd);
            if (minhangPartyHistoryList != null && minhangPartyHistoryList.size() > 0) {
            	minhangPartyHistory = minhangPartyHistoryList.get(0);
            }
        }
        return minhangPartyHistory;
    }
    
    public MinhangPartyHistory nextHistory(String app_id, Date system_create_time) {
    	MinhangPartyHistory minhangPartyHistory = minhangPartyHistoryDao.nextHistory(app_id, system_create_time);
        if (minhangPartyHistory == null) {
            Cnd cnd = new Cnd();
            cnd.where(MinhangPartyHistory.SYSTEM_STATUS, true);
            cnd.and(MinhangPartyHistory.APP_ID, app_id);
            cnd.desc(MinhangPartyHistory.SYSTEM_CREATE_TIME);
            cnd.paginate(0, 1);
            
            List<MinhangPartyHistory> minhangPartyHistoryList = minhangPartyHistoryDao.list(cnd);
            if (minhangPartyHistoryList != null && minhangPartyHistoryList.size() > 0) {
            	minhangPartyHistory = minhangPartyHistoryList.get(0);
            }
        }
        return minhangPartyHistory;
    }

}