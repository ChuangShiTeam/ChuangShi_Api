package com.nowui.chuangshi.api.jiangling.dao;

import com.nowui.chuangshi.api.jiangling.model.JianglingGameMember;
import com.nowui.chuangshi.common.dao.Dao;

public class JianglingGameMemberDao extends Dao {

    public JianglingGameMemberDao() {
        setModel(new JianglingGameMember());
    }

}