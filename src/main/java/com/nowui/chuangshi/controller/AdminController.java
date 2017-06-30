package com.nowui.chuangshi.controller;


import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;
import com.nowui.chuangshi.util.Util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AdminController extends Controller {

    private final UserService userService = new UserService();

    @ActionKey(Url.ADMIN_LOGIN)
    public void login() {
        validateRequest_app_id();
        validate(User.USER_ACCOUNT, User.USER_PASSWORD);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        User user = userService.findByApp_idAndUser_typeAndUser_accountAndUser_password(request_app_id, UserType.ADMIN.getKey(), model.getUser_account(), model.getUser_password(), request_app_id, request_http_id, request_user_id);

        if (user == null) {
            throw new RuntimeException("帐号或者密码不正确");
        }

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, user.getUser_id());
            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());
            result.put(Constant.TOKEN, AesUtil.aesEncrypt(jsonObject.toJSONString(), Config.private_key));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }

        renderSuccessJson(result);
    }

    @ActionKey(Url.ADMIN_ADMIN_SAVE)
    public void adminSave() {
        validateRequest_app_id();
        validate(User.USER_ACCOUNT, User.USER_PASSWORD, User.USER_NAME);

        authenticateRequest_app_idAndRequest_user_id();

        User model = getModel(User.class);
        String user_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        String user_type = UserType.ADMIN.getKey();

        Boolean result = userService.saveByUser_idAndApp_idAndUser_typeAndUser_accountAndUser_passwordAndUser_name(user_id, request_app_id, user_type, model.getUser_account(), model.getUser_password(), model.getUser_name(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.ADMIN_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(User.APP_ID, User.USER_TYPE, User.USER_ACCOUNT, User.USER_PASSWORD, User.USER_NAME);

        User model = getModel(User.class);
        String user_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = userService.saveByUser_idAndApp_idAndUser_typeAndUser_accountAndUser_passwordAndUser_name(user_id, model.getApp_id(), model.getUser_type(), model.getUser_account(), model.getUser_password(), model.getUser_name(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

}