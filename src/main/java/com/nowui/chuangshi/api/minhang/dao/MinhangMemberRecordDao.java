package com.nowui.chuangshi.api.minhang.dao;

import com.nowui.chuangshi.api.minhang.model.MinhangMemberRecord;
import com.nowui.chuangshi.common.dao.Dao;

public class MinhangMemberRecordDao extends Dao {

    public MinhangMemberRecordDao() {
        setModel(new MinhangMemberRecord());
    }

}