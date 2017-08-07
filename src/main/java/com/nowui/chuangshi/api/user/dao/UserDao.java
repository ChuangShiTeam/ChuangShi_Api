package com.nowui.chuangshi.api.user.dao;

import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.dao.Dao;

public class UserDao extends Dao {

    public UserDao() {
        setModel(new User());
    }

}