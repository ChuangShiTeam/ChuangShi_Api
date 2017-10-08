package com.nowui.chuangshi.api.minhang.dao;

import com.nowui.chuangshi.api.minhang.model.MinhangMemberQuestion;
import com.nowui.chuangshi.common.dao.Dao;

public class MinhangMemberQuestionDao extends Dao {

    public MinhangMemberQuestionDao() {
        setModel(new MinhangMemberQuestion());
    }

}