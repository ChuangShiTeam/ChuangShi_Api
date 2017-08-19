package com.nowui.chuangshi.api.page.dao;

import com.nowui.chuangshi.api.page.model.Page;
import com.nowui.chuangshi.common.dao.Dao;

public class PageDao extends Dao {

    public PageDao() {
        setModel(new Page());
    }

}