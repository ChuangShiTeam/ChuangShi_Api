package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangQuestionAnswerDao;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestionAnswer;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangQuestionAnswerService extends Service {

    public static final MinhangQuestionAnswerService instance = new MinhangQuestionAnswerService();
    private final String MINHANG_QUESTION_ANSWER_LIST_CACHE = "minhang_question_answer_list_cache";
    private final MinhangQuestionAnswerDao minhangQuestionAnswerDao = new MinhangQuestionAnswerDao();

    public List<MinhangQuestionAnswer> questionList(String question_id) {
        List<MinhangQuestionAnswer> minhang_question_answerList = CacheUtil.get(MINHANG_QUESTION_ANSWER_LIST_CACHE, question_id);
        
        if (minhang_question_answerList == null) {
            Cnd cnd = new Cnd();
            cnd.where(MinhangQuestionAnswer.SYSTEM_STATUS, true);
            cnd.andAllowEmpty(MinhangQuestionAnswer.QUESTION_ID, question_id);

            minhang_question_answerList = minhangQuestionAnswerDao.list(cnd);
            CacheUtil.put(MINHANG_QUESTION_ANSWER_LIST_CACHE, question_id, minhang_question_answerList);
        }
        
        return minhang_question_answerList;
    }

    public Boolean save(MinhangQuestionAnswer minhang_question_answer, String system_create_user_id) {
        Boolean success = minhangQuestionAnswerDao.save(minhang_question_answer, system_create_user_id);
        return success;
    }

    public Boolean questionDelete(String question_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestionAnswer.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestionAnswer.QUESTION_ID, question_id);
        cnd.and(MinhangQuestionAnswer.SYSTEM_VERSION, system_version);

        Boolean success = minhangQuestionAnswerDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_QUESTION_ANSWER_LIST_CACHE, question_id);
        }

        return success;
    }

}