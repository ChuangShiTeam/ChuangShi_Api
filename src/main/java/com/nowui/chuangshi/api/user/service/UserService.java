package com.nowui.chuangshi.api.user.service;

import com.nowui.chuangshi.api.user.cache.UserCache;
import com.nowui.chuangshi.common.service.Service;

public class UserService extends Service {

    public static final UserService me = new UserService();

    public UserService() {
        setCache(new UserCache());
    }

}