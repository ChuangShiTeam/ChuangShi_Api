package com.nowui.chuangshi.api.user.dao;

import com.nowui.chuangshi.api.user.model.UserRole;
import com.nowui.chuangshi.common.dao.Dao;

public class UserRoleDao extends Dao {

    public UserRoleDao() {
        setModel(new UserRole());
    }

}