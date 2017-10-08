package com.nowui.chuangshi.api.minhang.dao;

import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.common.dao.Dao;

public class MinhangTaskDao extends Dao {

    public MinhangTaskDao() {
        setModel(new MinhangTask());
    }

}