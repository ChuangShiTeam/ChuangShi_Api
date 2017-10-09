package com.nowui.chuangshi.api.minhang.dao;

import com.nowui.chuangshi.api.minhang.model.MinhangPartyHistory;
import com.nowui.chuangshi.common.dao.Dao;

public class MinhangPartyHistoryDao extends Dao {

    public MinhangPartyHistoryDao() {
        setModel(new MinhangPartyHistory());
    }

}