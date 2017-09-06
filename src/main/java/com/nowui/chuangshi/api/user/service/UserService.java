package com.nowui.chuangshi.api.user.service;

import com.nowui.chuangshi.api.user.dao.UserDao;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class UserService extends Service {

    public static final UserService instance = new UserService();
    private final String USER_ITEM_CACHE = "user_item_cache";
    private final UserDao userDao = new UserDao();

    public User find(String user_id) {
        User user = CacheUtil.get(USER_ITEM_CACHE, user_id);

        if (user == null) {
            user = userDao.find(user_id);

            CacheUtil.put(USER_ITEM_CACHE, user_id, user);
        }

        return user;
    }

    public User find(String app_id, String user_type, String wechat_open_id, String wechat_union_id) {
        Cnd cnd = Cnd.where(User.APP_ID, app_id);
        cnd.and(User.USER_TYPE, user_type);
        cnd.and(User.WECHAT_OPEN_ID, wechat_open_id);
        cnd.and(User.WECHAT_UNION_ID, wechat_union_id);

        User user = userDao.find(cnd);
        return user;
    }

}