package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangQuestionDao;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestion;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangQuestionService extends Service {

    public static final MinhangQuestionService instance = new MinhangQuestionService();
    private final String MINHANG_QUESTION_ITEM_CACHE = "minhang_question_item_cache";
    private final MinhangQuestionDao minhangQuestionDao = new MinhangQuestionDao();

    public Integer adminCount(String app_id, String task_id, String question_title, String question_type) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestion.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestion.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangQuestion.TASK_ID, task_id);
        cnd.andAllowEmpty(MinhangQuestion.QUESTION_TITLE, question_title);
        cnd.andAllowEmpty(MinhangQuestion.QUESTION_TYPE, question_type);

        Integer count = minhangQuestionDao.count(cnd);
        return count;
    }

    public List<MinhangQuestion> adminList(String app_id, String task_id, String question_title, String question_type, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestion.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestion.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangQuestion.TASK_ID, task_id);
        cnd.andAllowEmpty(MinhangQuestion.QUESTION_TITLE, question_title);
        cnd.andAllowEmpty(MinhangQuestion.QUESTION_TYPE, question_type);
        cnd.paginate(m, n);

        List<MinhangQuestion> minhang_questionList = minhangQuestionDao.primaryKeyList(cnd);
        for (MinhangQuestion minhang_question : minhang_questionList) {
            minhang_question.put(find(minhang_question.getQuestion_id()));
        }
        return minhang_questionList;
    }
    
    public List<MinhangQuestion> taskList(String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestion.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestion.TASK_ID, task_id);

        List<MinhangQuestion> minhang_questionList = minhangQuestionDao.primaryKeyList(cnd);
        for (MinhangQuestion minhang_question : minhang_questionList) {
            minhang_question.put(find(minhang_question.getQuestion_id()));
            minhang_question.put(MinhangQuestion.QUESTION_OPTION_LIST, MinhangQuestionOptionService.instance.questionList(minhang_question.getQuestion_id()));
        }
        return minhang_questionList;
    }

    public MinhangQuestion find(String question_id) {
        MinhangQuestion minhang_question = CacheUtil.get(MINHANG_QUESTION_ITEM_CACHE, question_id);

        if (minhang_question == null) {
            minhang_question = minhangQuestionDao.find(question_id);
            
            minhang_question.put(MinhangQuestion.QUESTION_ANSWER_LIST, MinhangQuestionAnswerService.instance.questionList(question_id));
            minhang_question.put(MinhangQuestion.QUESTION_OPTION_LIST, MinhangQuestionOptionService.instance.questionList(question_id));

            CacheUtil.put(MINHANG_QUESTION_ITEM_CACHE, question_id, minhang_question);
        }

        return minhang_question;
    }

    public Boolean save(MinhangQuestion minhang_question, String system_create_user_id) {
        Boolean success = minhangQuestionDao.save(minhang_question, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangQuestion minhang_question, String question_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestion.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestion.QUESTION_ID, question_id);
        cnd.and(MinhangQuestion.SYSTEM_VERSION, system_version);

        Boolean success = minhangQuestionDao.update(minhang_question, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_QUESTION_ITEM_CACHE, question_id);
        }

        return success;
    }

    public Boolean delete(String question_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestion.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestion.QUESTION_ID, question_id);
        cnd.and(MinhangQuestion.SYSTEM_VERSION, system_version);

        Boolean success = minhangQuestionDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_QUESTION_ITEM_CACHE, question_id);
        }

        return success;
    }

}