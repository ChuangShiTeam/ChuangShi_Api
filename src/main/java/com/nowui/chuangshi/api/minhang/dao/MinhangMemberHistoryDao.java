package com.nowui.chuangshi.api.minhang.dao;

import com.nowui.chuangshi.api.minhang.model.MinhangMemberHistory;
import com.nowui.chuangshi.common.dao.Dao;

public class MinhangMemberHistoryDao extends Dao {

    public MinhangMemberHistoryDao() {
        setModel(new MinhangMemberHistory());
    }

}