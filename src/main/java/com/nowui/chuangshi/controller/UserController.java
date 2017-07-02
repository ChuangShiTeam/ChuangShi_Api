package com.nowui.chuangshi.controller;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.UserService;

import java.util.List;

public class UserController extends Controller {

    private final UserService userService = new UserService();

    @ActionKey(Url.USER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = userService.countByApp_idOrLikeUser_name(request_app_id, model.getUser_name(), request_app_id, request_http_id, request_user_id);
        List<User> resultList = userService.listByApp_idOrLikeUser_nameAndLimit(request_app_id, model.getUser_name(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (User result : resultList) {
            result.keep(User.USER_ID, User.USER_NAME, User.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.USER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(User.USER_ID);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        User user = userService.findByUser_id(model.getUser_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(user.getApp_id());

        user.keep(User.USER_ID, User.USER_TYPE, User.USER_ACCOUNT, User.USER_PHONE, User.USER_EMAIL, User.USER_PASSWORD, User.USER_NAME, User.USER_AVATAR, User.WECHAT_OPEN_ID, User.WECHAT_UNION_ID, User.SYSTEM_VERSION);

        renderSuccessJson(user);
    }

    @ActionKey(Url.USER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(User.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = userService.countByOrApp_idOrLikeUser_name(model.getApp_id(), model.getUser_name(), request_app_id, request_http_id, request_user_id);
        List<User> resultList = userService.listByOrApp_idOrLikeUser_nameAndLimit(model.getApp_id(), model.getUser_name(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (User result : resultList) {
            result.keep(User.USER_ID, User.USER_NAME, User.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

}