package com.nowui.chuangshi.api.minhang.service;

import java.util.List;

import com.nowui.chuangshi.api.minhang.dao.MinhangMemberRecordDao;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberRecord;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class MinhangMemberRecordService extends Service {

    public static final MinhangMemberRecordService instance = new MinhangMemberRecordService();
    private final String MINHANG_MEMBER_RECORD_ITEM_CACHE = "minhang_member_record_item_cache";
    private final MinhangMemberRecordDao minhangMemberRecordDao = new MinhangMemberRecordDao();

    public Integer adminCount(String app_id, String member_id, String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberRecord.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberRecord.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberRecord.MEMBER_ID, member_id);
        cnd.andAllowEmpty(MinhangMemberRecord.TASK_ID, task_id);

        Integer count = minhangMemberRecordDao.count(cnd);
        return count;
    }

    public List<MinhangMemberRecord> adminList(String app_id, String member_id, String task_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberRecord.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberRecord.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberRecord.MEMBER_ID, member_id);
        cnd.andAllowEmpty(MinhangMemberRecord.TASK_ID, task_id);
        cnd.paginate(m, n);

        List<MinhangMemberRecord> minhang_member_recordList = minhangMemberRecordDao.primaryKeyList(cnd);
        for (MinhangMemberRecord minhang_member_record : minhang_member_recordList) {
            minhang_member_record.put(find(minhang_member_record.getMember_record_id()));
        }
        return minhang_member_recordList;
    }

    public MinhangMemberRecord find(String member_record_id) {
        MinhangMemberRecord minhang_member_record = CacheUtil.get(MINHANG_MEMBER_RECORD_ITEM_CACHE, member_record_id);

        if (minhang_member_record == null) {
            minhang_member_record = minhangMemberRecordDao.find(member_record_id);

            CacheUtil.put(MINHANG_MEMBER_RECORD_ITEM_CACHE, member_record_id, minhang_member_record);
        }

        return minhang_member_record;
    }
    
    public List<MinhangMemberRecord> itineraryList(String member_itinerary_id) {
    	Cnd cnd = new Cnd();
        cnd.where(MinhangMemberRecord.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberRecord.MEMBER_HISTORY_ID, member_itinerary_id);

        List<MinhangMemberRecord> minhang_member_recordList = minhangMemberRecordDao.primaryKeyList(cnd);
        for (MinhangMemberRecord minhang_member_record : minhang_member_recordList) {
            minhang_member_record.put(find(minhang_member_record.getMember_record_id()));
        }
        return minhang_member_recordList;
    }

    public Boolean save(MinhangMemberRecord minhang_member_record, String system_create_user_id) {
        Boolean success = minhangMemberRecordDao.save(minhang_member_record, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangMemberRecord minhang_member_record, String member_record_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberRecord.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberRecord.MEMBER_RECORD_ID, member_record_id);
        cnd.and(MinhangMemberRecord.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberRecordDao.update(minhang_member_record, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_RECORD_ITEM_CACHE, member_record_id);
        }

        return success;
    }

    public Boolean delete(String member_record_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberRecord.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberRecord.MEMBER_RECORD_ID, member_record_id);
        cnd.and(MinhangMemberRecord.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberRecordDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_RECORD_ITEM_CACHE, member_record_id);
        }

        return success;
    }

}