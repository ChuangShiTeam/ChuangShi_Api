package com.nowui.chuangshi.api.jiangling.dao;

import com.nowui.chuangshi.api.jiangling.model.JianglingNewGameMember;
import com.nowui.chuangshi.common.dao.Dao;

public class JianglingNewGameMemberDao extends Dao {

    public JianglingNewGameMemberDao() {
        setModel(new JianglingNewGameMember());
    }

}