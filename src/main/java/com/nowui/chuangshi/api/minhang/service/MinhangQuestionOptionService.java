package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangQuestionOptionDao;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestionOption;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangQuestionOptionService extends Service {

    public static final MinhangQuestionOptionService instance = new MinhangQuestionOptionService();
    private final String MINHANG_QUESTION_OPTION_LIST_CACHE = "minhang_question_option_list_cache";
    private final MinhangQuestionOptionDao minhangQuestionOptionDao = new MinhangQuestionOptionDao();

    public List<MinhangQuestionOption> questionList(String question_id) {
        List<MinhangQuestionOption> minhang_question_optionList = CacheUtil.get(MINHANG_QUESTION_OPTION_LIST_CACHE, question_id);
        
        if (minhang_question_optionList == null) {
            Cnd cnd = new Cnd();
            cnd.where(MinhangQuestionOption.SYSTEM_STATUS, true);
            cnd.andAllowEmpty(MinhangQuestionOption.QUESTION_ID, question_id);
            
            minhang_question_optionList = minhangQuestionOptionDao.list(cnd);
            CacheUtil.put(MINHANG_QUESTION_OPTION_LIST_CACHE, question_id, minhang_question_optionList);
        }
        
        return minhang_question_optionList;
    }

    public Boolean save(MinhangQuestionOption minhang_question_option, String system_create_user_id) {
        Boolean success = minhangQuestionOptionDao.save(minhang_question_option, system_create_user_id);
        return success;
    }

    public Boolean questionDelete(String question_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestionOption.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestionOption.QUESTION_ID, question_id);
        cnd.and(MinhangQuestionOption.SYSTEM_VERSION, system_version);

        Boolean success = minhangQuestionOptionDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_QUESTION_OPTION_LIST_CACHE, question_id);
        }

        return success;
    }

}