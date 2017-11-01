package com.nowui.chuangshi.api.renault.dao;

import com.nowui.chuangshi.api.renault.model.RenaultMember;
import com.nowui.chuangshi.common.dao.Dao;

public class RenaultMemberDao extends Dao {

    public RenaultMemberDao() {
        setModel(new RenaultMember());
    }

}