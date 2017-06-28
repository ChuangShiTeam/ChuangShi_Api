package com.nowui.chuangshi.controller;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.UserType;

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
        result.put(Constant.TOKEN, user.getUser_id());

        renderSuccessJson(result);
    }

}