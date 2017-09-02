package com.nowui.chuangshi.api.user.service;

import com.nowui.chuangshi.api.user.dao.UserDao;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.util.CacheUtil;

public class UserService extends Service {

    public static final UserService instance = new UserService();
    private final String USER_ITEM_CACHE = "user_item_cache";
    private final UserDao userDao = new UserDao();

    public User find(String product_brand_id) {
        User user = CacheUtil.get(USER_ITEM_CACHE, product_brand_id);

        if (user == null) {
            user = userDao.find(product_brand_id);

            CacheUtil.put(USER_ITEM_CACHE, product_brand_id, user);
        }

        return user;
    }

}