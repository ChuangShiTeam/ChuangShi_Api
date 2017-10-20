package com.nowui.chuangshi.api.minhang.service;

import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.api.minhang.dao.MinhangMemberHistoryDao;
import com.nowui.chuangshi.api.minhang.model.MinhangKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberHistory;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberKey;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;

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
    
    /**
     * 查询用户最新的纪念册信息
     * @param user_id
     * @return
     */
    public MinhangMemberHistory userLatestFind(String user_id) {
        List<MinhangMemberHistory> minhang_member_historyList = userList(user_id);
        if (minhang_member_historyList == null  || minhang_member_historyList.size() == 0) {
            return null;
        }
        return minhang_member_historyList.get(0);
    }
    
    /**
     * 查询用户已保存的纪念册列表
     * @param user_id
     * @return
     */
    public List<MinhangMemberHistory> userAndSaveList(String user_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberHistory.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(MinhangMemberHistory.USER_ID, user_id);
        cnd.and(MinhangMemberHistory.IS_SAVE_HISTORY, true);
        cnd.asc(MinhangMemberHistory.SYSTEM_CREATE_TIME);

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
    
    /**
     * 用户开启寻钥之旅
     * @param user_id
     * @return
     */
    public Boolean start(String user_id, String app_id) {
        //查询用户是否有过寻钥之旅
        List<MinhangMemberHistory> minhangMemberHistoryList = userList(user_id);
        
        if (minhangMemberHistoryList == null || minhangMemberHistoryList.size() == 0) {
            User user = UserService.instance.find(user_id);
            
            MinhangMemberHistory minhangMemberHistory = new MinhangMemberHistory();
            minhangMemberHistory.setMember_history_id(Util.getRandomUUID());
            minhangMemberHistory.setApp_id(app_id);
            minhangMemberHistory.setMember_id(user.getObject_id());
            minhangMemberHistory.setUser_id(user_id);
            minhangMemberHistory.setMember_history_name(DateUtil.getCNDateString(new Date()));
            Boolean result = this.save(minhangMemberHistory, user_id);
            if (result) {
                List<MinhangKey> minhangKeyList = MinhangKeyService.instance.appList(app_id);
                for (MinhangKey minhangKey : minhangKeyList) {
                    MinhangMemberKey minhangMemberKey = new MinhangMemberKey();
                    minhangMemberKey.setMember_key_id(Util.getRandomUUID());
                    minhangMemberKey.setApp_id(minhangKey.getApp_id());
                    minhangMemberKey.setMember_history_id(minhangMemberHistory.getMember_history_id());
                    minhangMemberKey.setKey_id(minhangKey.getKey_id());
                    minhangMemberKey.setMember_id(user.getObject_id());
                    minhangMemberKey.setUser_id(user_id);
                    minhangMemberKey.setKey_is_activated(false);
                    minhangMemberKey.setTask_quantity(minhangKey.getKey_activated_task_quantity());
                    minhangMemberKey.setTask_complete_quantity(0);
                    MinhangMemberKeyService.instance.save(minhangMemberKey, user_id);
                }
            }
            return result;
        }
        return false;
    }
    
    /**
     * 用户重启寻钥之旅
     * @param user_id
     * @return
     */
    public Boolean restart(String user_id, String app_id) {
        //查询用户是否有未完成寻钥之旅
        MinhangMemberHistory bean = userLatestFind(user_id);
        
        if (bean != null && bean.getIs_save_history()) {
            User user = UserService.instance.find(user_id);
            
            MinhangMemberHistory minhangMemberHistory = new MinhangMemberHistory();
            minhangMemberHistory.setMember_history_id(Util.getRandomUUID());
            minhangMemberHistory.setApp_id(app_id);
            minhangMemberHistory.setMember_id(user.getObject_id());
            minhangMemberHistory.setUser_id(user_id);
            minhangMemberHistory.setMember_history_name(DateUtil.getCNDateString(new Date()));
            Boolean result = this.save(minhangMemberHistory, user_id);
            if (result) {
                List<MinhangKey> minhangKeyList = MinhangKeyService.instance.appList(app_id);
                for (MinhangKey minhangKey : minhangKeyList) {
                    MinhangMemberKey minhangMemberKey = new MinhangMemberKey();
                    minhangMemberKey.setMember_key_id(Util.getRandomUUID());
                    minhangMemberKey.setApp_id(minhangKey.getApp_id());
                    minhangMemberKey.setMember_history_id(minhangMemberHistory.getMember_history_id());
                    minhangMemberKey.setKey_id(minhangKey.getKey_id());
                    minhangMemberKey.setMember_id(user.getObject_id());
                    minhangMemberKey.setUser_id(user_id);
                    minhangMemberKey.setKey_is_activated(false);
                    minhangMemberKey.setTask_quantity(minhangKey.getKey_activated_task_quantity());
                    minhangMemberKey.setTask_complete_quantity(0);
                    MinhangMemberKeyService.instance.save(minhangMemberKey, user_id);
                }
            }
            return result;
        }
        return false;
    }

}