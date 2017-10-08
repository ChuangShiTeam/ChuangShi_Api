package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangQuestionOptionDao;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestionOption;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangQuestionOptionService extends Service {

    public static final MinhangQuestionOptionService instance = new MinhangQuestionOptionService();
    private final String MINHANG_QUESTION_OPTION_ITEM_CACHE = "minhang_question_option_item_cache";
    private final MinhangQuestionOptionDao minhangQuestionOptionDao = new MinhangQuestionOptionDao();

    public Integer adminCount(String app_id, String question_id, String question_option_key) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestionOption.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestionOption.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangQuestionOption.QUESTION_ID, question_id);
        cnd.andAllowEmpty(MinhangQuestionOption.QUESTION_OPTION_KEY, question_option_key);

        Integer count = minhangQuestionOptionDao.count(cnd);
        return count;
    }

    public List<MinhangQuestionOption> adminList(String app_id, String question_id, String question_option_key, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestionOption.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestionOption.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangQuestionOption.QUESTION_ID, question_id);
        cnd.andAllowEmpty(MinhangQuestionOption.QUESTION_OPTION_KEY, question_option_key);
        cnd.paginate(m, n);

        List<MinhangQuestionOption> minhang_question_optionList = minhangQuestionOptionDao.primaryKeyList(cnd);
        for (MinhangQuestionOption minhang_question_option : minhang_question_optionList) {
            minhang_question_option.put(find(minhang_question_option.getQuestion_option_id()));
        }
        return minhang_question_optionList;
    }

    public MinhangQuestionOption find(String question_option_id) {
        MinhangQuestionOption minhang_question_option = CacheUtil.get(MINHANG_QUESTION_OPTION_ITEM_CACHE, question_option_id);

        if (minhang_question_option == null) {
            minhang_question_option = minhangQuestionOptionDao.find(question_option_id);

            CacheUtil.put(MINHANG_QUESTION_OPTION_ITEM_CACHE, question_option_id, minhang_question_option);
        }

        return minhang_question_option;
    }

    public Boolean save(MinhangQuestionOption minhang_question_option, String system_create_user_id) {
        Boolean success = minhangQuestionOptionDao.save(minhang_question_option, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangQuestionOption minhang_question_option, String question_option_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestionOption.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestionOption.QUESTION_OPTION_ID, question_option_id);
        cnd.and(MinhangQuestionOption.SYSTEM_VERSION, system_version);

        Boolean success = minhangQuestionOptionDao.update(minhang_question_option, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_QUESTION_OPTION_ITEM_CACHE, question_option_id);
        }

        return success;
    }

    public Boolean delete(String question_option_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangQuestionOption.SYSTEM_STATUS, true);
        cnd.and(MinhangQuestionOption.QUESTION_OPTION_ID, question_option_id);
        cnd.and(MinhangQuestionOption.SYSTEM_VERSION, system_version);

        Boolean success = minhangQuestionOptionDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_QUESTION_OPTION_ITEM_CACHE, question_option_id);
        }

        return success;
    }

}