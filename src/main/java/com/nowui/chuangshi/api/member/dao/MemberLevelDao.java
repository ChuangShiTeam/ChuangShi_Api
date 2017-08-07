package com.nowui.chuangshi.api.member.dao;

import com.nowui.chuangshi.api.member.model.MemberLevel;
import com.nowui.chuangshi.common.dao.Dao;

public class MemberLevelDao extends Dao {

    public MemberLevelDao() {
        setModel(new MemberLevel());
    }

}