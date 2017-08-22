package com.nowui.chuangshi.api.website.dao;

import com.nowui.chuangshi.api.website.model.WebsiteMenu;
import com.nowui.chuangshi.common.dao.Dao;

public class WebsiteMenuDao extends Dao {

    public WebsiteMenuDao() {
        setModel(new WebsiteMenu());
    }

}