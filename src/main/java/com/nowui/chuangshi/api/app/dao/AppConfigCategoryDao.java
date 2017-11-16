package com.nowui.chuangshi.api.app.dao;

import com.nowui.chuangshi.api.app.model.AppConfigCategory;
import com.nowui.chuangshi.common.dao.Dao;

public class AppConfigCategoryDao extends Dao {

    public AppConfigCategoryDao() {
        setModel(new AppConfigCategory());
    }

}