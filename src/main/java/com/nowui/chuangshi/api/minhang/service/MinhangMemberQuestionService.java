package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangMemberQuestionDao;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberQuestion;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangMemberQuestionService extends Service {

    public static final MinhangMemberQuestionService instance = new MinhangMemberQuestionService();
    private final String MINHANG_MEMBER_QUESTION_ITEM_CACHE = "minhang_member_question_item_cache";
    private final MinhangMemberQuestionDao minhangMemberQuestionDao = new MinhangMemberQuestionDao();

    public Integer adminCount(String app_id, String member_id, String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberQuestion.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberQuestion.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberQuestion.MEMBER_ID, member_id);
        cnd.andAllowEmpty(MinhangMemberQuestion.TASK_ID, task_id);

        Integer count = minhangMemberQuestionDao.count(cnd);
        return count;
    }

    public List<MinhangMemberQuestion> adminList(String app_id, String member_id, String task_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberQuestion.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberQuestion.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberQuestion.MEMBER_ID, member_id);
        cnd.andAllowEmpty(MinhangMemberQuestion.TASK_ID, task_id);
        cnd.paginate(m, n);

        List<MinhangMemberQuestion> minhang_member_questionList = minhangMemberQuestionDao.primaryKeyList(cnd);
        for (MinhangMemberQuestion minhang_member_question : minhang_member_questionList) {
            minhang_member_question.put(find(minhang_member_question.getMember_question_id()));
        }
        return minhang_member_questionList;
    }

    public MinhangMemberQuestion find(String member_question_id) {
        MinhangMemberQuestion minhang_member_question = CacheUtil.get(MINHANG_MEMBER_QUESTION_ITEM_CACHE, member_question_id);

        if (minhang_member_question == null) {
            minhang_member_question = minhangMemberQuestionDao.find(member_question_id);

            CacheUtil.put(MINHANG_MEMBER_QUESTION_ITEM_CACHE, member_question_id, minhang_member_question);
        }

        return minhang_member_question;
    }
    
    public List<MinhangMemberQuestion> itineraryList(String member_itinerary_id) {
    	Cnd cnd = new Cnd();
        cnd.where(MinhangMemberQuestion.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(MinhangMemberQuestion.MEMBER_ITINERARY_ID, member_itinerary_id);

        List<MinhangMemberQuestion> minhang_member_questionList = minhangMemberQuestionDao.primaryKeyList(cnd);
        
        for (MinhangMemberQuestion minhang_member_question : minhang_member_questionList) {
            minhang_member_question.put(find(minhang_member_question.getMember_question_id()));
        }
        return minhang_member_questionList;
    }

    public Boolean save(MinhangMemberQuestion minhang_member_question, String system_create_user_id) {
        Boolean success = minhangMemberQuestionDao.save(minhang_member_question, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangMemberQuestion minhang_member_question, String member_question_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberQuestion.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberQuestion.MEMBER_QUESTION_ID, member_question_id);
        cnd.and(MinhangMemberQuestion.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberQuestionDao.update(minhang_member_question, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_QUESTION_ITEM_CACHE, member_question_id);
        }

        return success;
    }

    public Boolean delete(String member_question_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberQuestion.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberQuestion.MEMBER_QUESTION_ID, member_question_id);
        cnd.and(MinhangMemberQuestion.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberQuestionDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_QUESTION_ITEM_CACHE, member_question_id);
        }

        return success;
    }

}