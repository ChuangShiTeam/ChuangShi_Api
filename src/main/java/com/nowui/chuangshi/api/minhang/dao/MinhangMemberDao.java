package com.nowui.chuangshi.api.minhang.dao;

import com.nowui.chuangshi.api.minhang.model.MinhangMember;
import com.nowui.chuangshi.common.dao.Dao;

public class MinhangMemberDao extends Dao {

    public MinhangMemberDao() {
        setModel(new MinhangMember());
    }

}