package com.nowui.chuangshi.api.minhang.service;

import java.util.List;

import com.nowui.chuangshi.api.minhang.dao.MinhangMemberItineraryDao;
import com.nowui.chuangshi.api.minhang.model.MinhangKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberItinerary;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberKey;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;

public class MinhangMemberItineraryService extends Service {

    public static final MinhangMemberItineraryService instance = new MinhangMemberItineraryService();
    private final String MINHANG_MEMBER_ITINERARY_ITEM_CACHE = "minhang_member_itinerary_item_cache";
    private final MinhangMemberItineraryDao minhangMemberItineraryDao = new MinhangMemberItineraryDao();

    public Integer adminCount(String app_id, String user_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberItinerary.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberItinerary.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberItinerary.USER_ID, user_id);

        Integer count = minhangMemberItineraryDao.count(cnd);
        return count;
    }

    public List<MinhangMemberItinerary> adminList(String app_id, String user_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberItinerary.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberItinerary.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberItinerary.USER_ID, user_id);
        cnd.paginate(m, n);

        List<MinhangMemberItinerary> minhang_member_itineraryList = minhangMemberItineraryDao.primaryKeyList(cnd);
        for (MinhangMemberItinerary minhang_member_itinerary : minhang_member_itineraryList) {
            minhang_member_itinerary.put(find(minhang_member_itinerary.getMember_itinerary_id()));
        }
        return minhang_member_itineraryList;
    }

    public MinhangMemberItinerary find(String member_itinerary_id) {
        MinhangMemberItinerary minhang_member_itinerary = CacheUtil.get(MINHANG_MEMBER_ITINERARY_ITEM_CACHE, member_itinerary_id);

        if (minhang_member_itinerary == null) {
            minhang_member_itinerary = minhangMemberItineraryDao.find(member_itinerary_id);

            CacheUtil.put(MINHANG_MEMBER_ITINERARY_ITEM_CACHE, member_itinerary_id, minhang_member_itinerary);
        }

        return minhang_member_itinerary;
    }

    public Boolean save(MinhangMemberItinerary minhang_member_itinerary, String system_create_user_id) {
        Boolean success = minhangMemberItineraryDao.save(minhang_member_itinerary, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangMemberItinerary minhang_member_itinerary, String member_itinerary_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberItinerary.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberItinerary.MEMBER_ITINERARY_ID, member_itinerary_id);
        cnd.and(MinhangMemberItinerary.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberItineraryDao.update(minhang_member_itinerary, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_ITINERARY_ITEM_CACHE, member_itinerary_id);
        }

        return success;
    }

    public Boolean delete(String member_itinerary_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberItinerary.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberItinerary.MEMBER_ITINERARY_ID, member_itinerary_id);
        cnd.and(MinhangMemberItinerary.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberItineraryDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_ITINERARY_ITEM_CACHE, member_itinerary_id);
        }

        return success;
    }
    
    /**
     * 用户寻钥之旅列表
     * @param user_id
     * @return
     */
    public List<MinhangMemberItinerary> userList(String user_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberItinerary.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(MinhangMemberItinerary.USER_ID, user_id);
        cnd.desc(MinhangMemberItinerary.SYSTEM_CREATE_TIME);

        List<MinhangMemberItinerary> minhang_member_itineraryList = minhangMemberItineraryDao.primaryKeyList(cnd);
        for (MinhangMemberItinerary minhang_member_itinerary : minhang_member_itineraryList) {
        	minhang_member_itinerary.put(find(minhang_member_itinerary.getMember_itinerary_id()));
        }
        return minhang_member_itineraryList;
    }
    
    /**
     * 查询用户最新的寻钥之旅信息
     * @param user_id
     * @return
     */
    public MinhangMemberItinerary userLatestFind(String user_id) {
        List<MinhangMemberItinerary> minhang_member_itineraryList = userList(user_id);
        if (minhang_member_itineraryList == null  || minhang_member_itineraryList.size() == 0) {
            return null;
        }
        return minhang_member_itineraryList.get(0);
    }
    
    /**
     * 用户开启寻钥之旅
     * @param user_id
     * @return
     */
    public MinhangMemberItinerary start(String user_id, String app_id) {
        User user = UserService.instance.find(user_id);
        
        MinhangMemberItinerary minhangMemberItinerary = new MinhangMemberItinerary();
        minhangMemberItinerary.setMember_itinerary_id(Util.getRandomUUID());
        minhangMemberItinerary.setApp_id(app_id);
        minhangMemberItinerary.setMember_id(user.getObject_id());
        minhangMemberItinerary.setUser_id(user_id);
        minhangMemberItinerary.setItinerary_is_complete(false);
        Boolean result = this.save(minhangMemberItinerary, user_id);
        if (result) {
            List<MinhangKey> minhangKeyList = MinhangKeyService.instance.appList(app_id);
            for (MinhangKey minhangKey : minhangKeyList) {
                MinhangMemberKey minhangMemberKey = new MinhangMemberKey();
                minhangMemberKey.setMember_key_id(Util.getRandomUUID());
                minhangMemberKey.setApp_id(minhangKey.getApp_id());
                minhangMemberKey.setMember_itinerary_id(minhangMemberItinerary.getMember_itinerary_id());
                minhangMemberKey.setKey_id(minhangKey.getKey_id());
                minhangMemberKey.setMember_id(user.getObject_id());
                minhangMemberKey.setUser_id(user_id);
                minhangMemberKey.setKey_is_activated(false);
                minhangMemberKey.setTask_quantity(minhangKey.getKey_activated_task_quantity());
                minhangMemberKey.setTask_complete_quantity(0);
                MinhangMemberKeyService.instance.save(minhangMemberKey, user_id);
            }
        }
        return minhangMemberItinerary;
    }
    
    /**
     * 用户重启寻钥之旅
     * @param user_id
     * @return
     */
    public Boolean restart(String user_id, String app_id) {
        //查询用户最近寻钥之旅
    	MinhangMemberItinerary bean = userLatestFind(user_id);
        
        if (bean != null && !bean.getItinerary_is_complete()) {
        	/*//判断用户最近的寻钥之旅有没有获取所有钥匙
        	List<MinhangMemberKey> minhang_member_keyList = MinhangMemberKeyService.instance.itineraryList(bean.getMember_itinerary_id());
        	if (minhang_member_keyList == null || minhang_member_keyList.size() == 0) {
        		throw new RuntimeException("寻钥之旅对应钥匙不存在");
        	}
        	for (MinhangMemberKey minhangMemberKey : minhang_member_keyList) {
        		if (!minhangMemberKey.getKey_is_activated()) {
        			throw new RuntimeException("用户未获取所有钥匙");
        		}
        	}*/
        	//上一次寻钥之旅结束，开启的新的寻钥之旅
        	
        	bean.setItinerary_is_complete(true);
        	Boolean flag = this.update(bean, bean.getMember_itinerary_id(), user_id, bean.getSystem_version());
        	
        	if (flag) {
        		User user = UserService.instance.find(user_id);
                
                MinhangMemberItinerary minhangMemberItinerary = new MinhangMemberItinerary();
                minhangMemberItinerary.setMember_itinerary_id(Util.getRandomUUID());
                minhangMemberItinerary.setApp_id(app_id);
                minhangMemberItinerary.setMember_id(user.getObject_id());
                minhangMemberItinerary.setUser_id(user_id);
                minhangMemberItinerary.setItinerary_is_complete(false);
                Boolean result = this.save(minhangMemberItinerary, user_id);
                if (result) {
                    List<MinhangKey> minhangKeyList = MinhangKeyService.instance.appList(app_id);
                    for (MinhangKey minhangKey : minhangKeyList) {
                        MinhangMemberKey minhangMemberKey = new MinhangMemberKey();
                        minhangMemberKey.setMember_key_id(Util.getRandomUUID());
                        minhangMemberKey.setApp_id(minhangKey.getApp_id());
                        minhangMemberKey.setMember_itinerary_id(minhangMemberItinerary.getMember_itinerary_id());
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
        	
        	return flag;
            
        }
        return false;
    }

}