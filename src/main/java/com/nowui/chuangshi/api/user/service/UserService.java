package com.nowui.chuangshi.api.user.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.nowui.chuangshi.api.menu.service.MenuService;
import com.nowui.chuangshi.api.user.dao.UserDao;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

public class UserService extends Service {

    public static final UserService instance = new UserService();
    private final String USER_ITEM_CACHE = "user_item_cache";
    private final UserDao userDao = new UserDao();

    public Integer userAccountCount(String user_id, String app_id, String user_account) {
        Cnd cnd = new Cnd();
        cnd.where(User.SYSTEM_STATUS, true);
        cnd.andNot(User.USER_ID, user_id);
        cnd.and(User.APP_ID, app_id);
        cnd.andAllowEmpty(User.USER_ACCOUNT, user_account);

        Integer count = userDao.count(cnd);
        return count;
    }

    public User find(String user_id) {
        User user = CacheUtil.get(USER_ITEM_CACHE, user_id);

        if (user == null) {
            user = userDao.find(user_id);

            CacheUtil.put(USER_ITEM_CACHE, user_id, user);
        }

        return user;
    }
    
    public User appAndAdminFind(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(User.SYSTEM_STATUS, true);
        cnd.and(User.APP_ID, app_id);
        cnd.and(User.USER_TYPE, UserType.ADMIN.getKey());
        
        User user = userDao.find(cnd);
        return user;
    }

    public User wechatFind(String app_id, String user_type, String wechat_open_id, String wechat_union_id) {
        Cnd cnd = new Cnd();
        cnd.where(User.SYSTEM_STATUS, true);
        cnd.and(User.APP_ID, app_id);
        cnd.and(User.USER_TYPE, user_type);
        cnd.and(User.WECHAT_OPEN_ID, wechat_open_id);
        cnd.and(User.WECHAT_UNION_ID, wechat_union_id);

        User user = userDao.find(cnd);
        return user;
    }
    
    public User userAccountFind(String app_id, String user_account) {
        Cnd cnd = new Cnd();
        cnd.where(User.SYSTEM_STATUS, true);
        cnd.and(User.APP_ID, app_id);
        cnd.and(User.USER_ACCOUNT, user_account);

        User user = userDao.find(cnd);
        return user;
    }

    public User userAccountFind(String app_id, String user_type, String user_account, String user_password) {
        Cnd cnd = new Cnd();
        cnd.where(User.SYSTEM_STATUS, true);
        cnd.and(User.APP_ID, app_id);
        cnd.and(User.USER_TYPE, user_type);
        cnd.and(User.USER_ACCOUNT, user_account);
        cnd.and(User.USER_PASSWORD, Util.generatePassword(user_password));

        User user = userDao.find(cnd);
        return user;
    }

    public Boolean wechatSave(String user_id, String app_id, String object_id, String user_type, String user_name, String user_avatar, String wechat_open_id, String wechat_union_id, String system_create_user_id) {
        String user_account = "";
        String user_password = "";
        String user_mobile = "";
        String user_email = "";

        Boolean success = sava(user_id, app_id, object_id, user_type, user_name, user_avatar, user_account, user_mobile, user_email, user_password, wechat_open_id, wechat_union_id, system_create_user_id);
        return success;
    }

    public Boolean userAccountSave(String user_id, String app_id, String object_id, String user_type, String user_name, String user_account, String user_password, String system_create_user_id) {
        User user = userAccountFind(app_id, user_account);

        if (user != null) {
            throw new RuntimeException(user_account + "已经存在");
        }

        String user_avatar = "";
        String user_mobile = "";
        String user_email = "";
        String wechat_open_id = "";
        String wechat_union_id = "";

        Boolean success = sava(user_id, app_id, object_id, user_type, user_name, user_avatar, user_account, user_mobile, user_email, user_password, wechat_open_id, wechat_union_id, system_create_user_id);
        return success;
    }
    
    public Boolean userAccountAndAvatarSave(String user_id, String app_id, String object_id, String user_type, String user_name, String user_account, String user_password, String user_avatar, String system_create_user_id) {
        String user_mobile = "";
        String user_email = "";
        String wechat_open_id = "";
        String wechat_union_id = "";

        Boolean success = sava(user_id, app_id, object_id, user_type, user_name, user_avatar, user_account, user_mobile, user_email, user_password, wechat_open_id, wechat_union_id, system_create_user_id);
        return success;
    }

    public Boolean sava(String user_id, String app_id, String object_id, String user_type, String user_name, String user_avatar, String user_account, String user_mobile, String user_email, String user_password, String wechat_open_id, String wechat_union_id, String system_create_user_id) {
        User user = new User();
        user.setUser_id(user_id);
        user.setApp_id(app_id);
        user.setObject_id(object_id);
        user.setUser_type(user_type);
        user.setUser_name(user_name);
        user.setUser_avatar(user_avatar);
        user.setUser_account(user_account);
        user.setUser_mobile(user_mobile);
        user.setUser_email(user_email);
        user.setUser_password(Util.generatePassword(user_password));
        user.setWechat_open_id(wechat_open_id);
        user.setWechat_union_id(wechat_union_id);

        Boolean success = userDao.save(user, system_create_user_id);
        return success;
    }

    public Boolean userNameUpdate(String user_id, String user_name, String system_update_user_id) {
        if (ValidateUtil.isNullOrEmpty(user_name)) {
            return false;
        }

        User user = new User();
        user.setUser_name(user_name);

        Cnd cnd = new Cnd();
        cnd.where(User.SYSTEM_STATUS, true);
        cnd.and(User.USER_ID, user_id);

        Boolean success = userDao.update(user, system_update_user_id, cnd);

        if (success) {
            CacheUtil.remove(USER_ITEM_CACHE, user_id);
        }

        return success;
    }
    
    public Boolean userAccountAndNameUpdate(String user_id, String user_account, String user_name, String system_update_user_id) {
        if (ValidateUtil.isNullOrEmpty(user_account)) {
            return false;
        }
        
        User user = new User();
        user.setUser_account(user_account);
        user.setUser_name(user_name);
        
        Cnd cnd = new Cnd();
        cnd.where(User.SYSTEM_STATUS, true);
        cnd.and(User.USER_ID, user_id);
        
        Boolean success = userDao.update(user, system_update_user_id, cnd);
        
        if (success) {
            CacheUtil.remove(USER_ITEM_CACHE, user_id);
        }
        
        return success;
    }

    public Boolean userPasswordUpdate(String user_id, String user_password, String system_update_user_id) {
        User user = new User();
        user.setUser_password(Util.generatePassword(user_password));

        Cnd cnd = new Cnd();
        cnd.where(User.SYSTEM_STATUS, true);
        cnd.and(User.USER_ID, user_id);

        Boolean success = userDao.update(user, system_update_user_id, cnd);

        if (success) {
            CacheUtil.remove(USER_ITEM_CACHE, user_id);
        }

        return success;
    }

    public Boolean systemUpdateTimeUpdate(String user_id, String system_update_user_id, Integer system_version) {

        Cnd cnd = new Cnd();
        cnd.where(User.SYSTEM_STATUS, true);
        cnd.and(User.USER_ID, user_id);
        cnd.and(User.SYSTEM_VERSION, system_version);
        
        Boolean success = userDao.update(new User(), system_update_user_id, cnd);

        if (success) {
            CacheUtil.remove(USER_ITEM_CACHE, user_id);
        }

        return success;
    }

    public Boolean delete(String user_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(User.SYSTEM_STATUS, true);
        cnd.and(User.USER_ID, user_id);
        cnd.and(User.SYSTEM_VERSION, system_version);

        Boolean success = userDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(USER_ITEM_CACHE, user_id);
        }

        return success;
    }
    
    public Boolean userTypeDelete(String user_type, String system_update_user_id) {
        Cnd cnd = new Cnd();
        cnd.where(User.SYSTEM_STATUS, true);
        cnd.and(User.USER_TYPE, user_type);

        Boolean success = userDao.delete(system_update_user_id, cnd);
        
        if (success) {
            CacheUtil.removeAll(USER_ITEM_CACHE);
        }
        
        return success;
    }
    
    public Boolean objectIdDelete(String object_id, String system_update_user_id) {
        Cnd cnd = new Cnd();
        cnd.where(User.SYSTEM_STATUS, true);
        cnd.and(User.OBJECT_ID, object_id);

        Boolean success = userDao.delete(system_update_user_id, cnd);
        
        if (success) {
            CacheUtil.removeAll(USER_ITEM_CACHE);
        }
        
        return success;
    }
    
    public List<Map<String, Object>> menuList(String user_id) {
        return MenuService.instance.userList(user_id);
    }

    public static void main(String[] args) {
        try {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, "ffb11c2d4a3043ec8eb28c8cca9d1fc8");
            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());

            System.out.println(AesUtil.aesEncrypt(jsonObject.toJSONString(), Config.private_key));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }
    }
    
}