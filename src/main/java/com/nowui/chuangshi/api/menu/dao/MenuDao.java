package com.nowui.chuangshi.api.menu.dao;

import com.nowui.chuangshi.api.menu.model.Menu;
import com.nowui.chuangshi.common.dao.Dao;

public class MenuDao extends Dao {

    public MenuDao() {
        setModel(new Menu());
    }

}