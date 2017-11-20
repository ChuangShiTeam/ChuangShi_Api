package com.nowui.chuangshi.api.role.dao;

import com.nowui.chuangshi.api.role.model.RoleMenu;
import com.nowui.chuangshi.common.dao.Dao;

public class RoleMenuDao extends Dao {

    public RoleMenuDao() {
        setModel(new RoleMenu());
    }

}