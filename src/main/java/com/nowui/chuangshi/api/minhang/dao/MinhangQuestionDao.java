package com.nowui.chuangshi.api.minhang.dao;

import com.nowui.chuangshi.api.minhang.model.MinhangQuestion;
import com.nowui.chuangshi.common.dao.Dao;

public class MinhangQuestionDao extends Dao {

    public MinhangQuestionDao() {
        setModel(new MinhangQuestion());
    }

}