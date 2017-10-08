package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangQuestionAnswerDao;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestionAnswer;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangQuestionAnswerService extends Service {

    public static final MinhangQuestionAnswerService instance = new MinhangQuestionAnswerService();
    private final String MINHANG_QUESTION_ANSWER_ITEM_CACHE = "minhang_question_answer_item_cache";
    private final MinhangQuestionAnswerDao minhangQuestionAnswerDao = new MinhangQuestionAnswerDao();

    public Integer adminCount(String app_id, String question_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestionAnswer.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestionAnswer.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangQuestionAnswer.QUESTION_ID, question_id);

        Integer count = minhangQuestionAnswerDao.count(cnd);
        return count;
    }

    public List<MinhangQuestionAnswer> adminList(String app_id, String question_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestionAnswer.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestionAnswer.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangQuestionAnswer.QUESTION_ID, question_id);
        cnd.paginate(m, n);

        List<MinhangQuestionAnswer> minhang_question_answerList = minhangQuestionAnswerDao.primaryKeyList(cnd);
        for (MinhangQuestionAnswer minhang_question_answer : minhang_question_answerList) {
            minhang_question_answer.put(find(minhang_question_answer.getQuestion_answer_id()));
        }
        return minhang_question_answerList;
    }

    public MinhangQuestionAnswer find(String question_answer_id) {
        MinhangQuestionAnswer minhang_question_answer = CacheUtil.get(MINHANG_QUESTION_ANSWER_ITEM_CACHE, question_answer_id);

        if (minhang_question_answer == null) {
            minhang_question_answer = minhangQuestionAnswerDao.find(question_answer_id);

            CacheUtil.put(MINHANG_QUESTION_ANSWER_ITEM_CACHE, question_answer_id, minhang_question_answer);
        }

        return minhang_question_answer;
    }

    public Boolean save(MinhangQuestionAnswer minhang_question_answer, String system_create_user_id) {
        Boolean success = minhangQuestionAnswerDao.save(minhang_question_answer, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangQuestionAnswer minhang_question_answer, String question_answer_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestionAnswer.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestionAnswer.QUESTION_ANSWER_ID, question_answer_id);
        cnd.and(MinhangQuestionAnswer.SYSTEM_VERSION, system_version);

        Boolean success = minhangQuestionAnswerDao.update(minhang_question_answer, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_QUESTION_ANSWER_ITEM_CACHE, question_answer_id);
        }

        return success;
    }

    public Boolean delete(String question_answer_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestionAnswer.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestionAnswer.QUESTION_ANSWER_ID, question_answer_id);
        cnd.and(MinhangQuestionAnswer.SYSTEM_VERSION, system_version);

        Boolean success = minhangQuestionAnswerDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_QUESTION_ANSWER_ITEM_CACHE, question_answer_id);
        }

        return success;
    }

}