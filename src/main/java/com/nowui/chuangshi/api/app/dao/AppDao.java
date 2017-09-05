package com.nowui.chuangshi.api.app.dao;

import com.nowui.chuangshi.api.app.model.App;
import com.nowui.chuangshi.common.dao.Dao;

public class AppDao extends Dao {

    public AppDao() {
        setModel(new App());
    }

}