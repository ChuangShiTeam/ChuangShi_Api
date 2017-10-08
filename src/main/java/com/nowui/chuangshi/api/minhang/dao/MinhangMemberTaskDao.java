package com.nowui.chuangshi.api.minhang.dao;

import com.nowui.chuangshi.api.minhang.model.MinhangMemberTask;
import com.nowui.chuangshi.common.dao.Dao;

public class MinhangMemberTaskDao extends Dao {

    public MinhangMemberTaskDao() {
        setModel(new MinhangMemberTask());
    }

}