package com.nowui.chuangshi.api.admin.dao;

import com.nowui.chuangshi.api.admin.model.Admin;
import com.nowui.chuangshi.common.dao.Dao;

public class AdminDao extends Dao {

    public AdminDao() {
        setModel(new Admin());
    }

}