package com.nowui.chuangshi.api.jiangling.dao;

import com.nowui.chuangshi.api.jiangling.model.JianglingMember;
import com.nowui.chuangshi.common.dao.Dao;

public class JianglingMemberDao extends Dao {

    public JianglingMemberDao() {
        setModel(new JianglingMember());
    }

}