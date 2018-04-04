package com.nowui.chuangshi.api.jiangling.dao;

import com.nowui.chuangshi.api.jiangling.model.JianglingNewGame;
import com.nowui.chuangshi.common.dao.Dao;

public class JianglingNewGameDao extends Dao {

    public JianglingNewGameDao() {
        setModel(new JianglingNewGame());
    }

}