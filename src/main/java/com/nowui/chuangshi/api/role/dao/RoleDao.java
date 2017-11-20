package com.nowui.chuangshi.api.role.dao;

import com.nowui.chuangshi.api.role.model.Role;
import com.nowui.chuangshi.common.dao.Dao;

public class RoleDao extends Dao {

    public RoleDao() {
        setModel(new Role());
    }

}