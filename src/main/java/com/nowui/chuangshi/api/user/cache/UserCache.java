package com.nowui.chuangshi.api.user.cache;

import com.nowui.chuangshi.api.user.dao.UserDao;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.cache.Cache;

public class UserCache extends Cache {

    public static final String USER_ITEM_CACHE = "user_item_cache";

    public UserCache() {
        setDao(new UserDao());

        setItemCache(USER_ITEM_CACHE, User.USER_ID);
    }

}