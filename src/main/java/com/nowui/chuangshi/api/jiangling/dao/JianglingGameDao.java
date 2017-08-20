package com.nowui.chuangshi.api.jiangling.dao;

import com.nowui.chuangshi.api.jiangling.model.JianglingGame;
import com.nowui.chuangshi.common.dao.Dao;

public class JianglingGameDao extends Dao {

    public JianglingGameDao() {
        setModel(new JianglingGame());
    }

}