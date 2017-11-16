package com.nowui.chuangshi.api.app.dao;

import com.nowui.chuangshi.api.app.model.AppConfig;
import com.nowui.chuangshi.common.dao.Dao;

public class AppConfigDao extends Dao {

    public AppConfigDao() {
        setModel(new AppConfig());
    }

}