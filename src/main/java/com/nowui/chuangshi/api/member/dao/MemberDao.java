package com.nowui.chuangshi.api.member.dao;

import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.common.dao.Dao;

public class MemberDao extends Dao {

    public MemberDao() {
        setClazz(Member.class);
    }

}