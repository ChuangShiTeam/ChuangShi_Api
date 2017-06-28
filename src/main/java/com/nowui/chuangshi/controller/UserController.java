package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class UserController extends Controller {

    private final UserService userService = new UserService();

    @ActionKey(Url.USER_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        List<User> resultList = userService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN(), request_app_id, request_http_id, request_user_id);

        for (User result : resultList) {
            result.keep(User.USER_ID, User.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.USER_FIND)
    public void find() {
        validateRequest_app_id();
        validate(User.USER_ID);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        User user = userService.findByUser_id(model.getUser_id(), request_app_id, request_http_id, request_user_id);

        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(user.getApp_id());
        authenticateSystem_create_user_id(user.getSystem_create_user_id());

        user.keep(User.USER_ID, User.SYSTEM_VERSION);

        renderSuccessJson(user);
    }

    @ActionKey(Url.USER_SAVE)
    public void save() {
//        validateRequest_app_id();
//        validate(User.ORGANIZATION_ID, User.ROLE_ID, User.USER_LEVEL_ID, User.USER_TYPE, User.USER_ACCOUNT, User.USER_PHONE, User.USER_EMAIL, User.USER_PASSWORD, User.USER_NAME, User.USER_AVATAR, User.WECHAT_OPEN_ID, User.WECHAT_UNION_ID);
//
//        authenticateRequest_app_idAndRequest_user_id();
//
//        User model = getModel(User.class);
//        String user_id = Util.getRandomUUID();
//        String request_app_id = getRequest_app_id();
//        String request_http_id = getRequest_http_id();
//        String request_user_id = getRequest_user_id();
//
//        Boolean result = userService.save(user_id, request_app_id, model.getOrganization_id(), model.getRole_id(), model.getUser_level_id(), model.getUser_type(), model.getUser_account(), model.getUser_phone(), model.getUser_email(), model.getUser_password(), model.getUser_name(), model.getUser_avatar(), model.getWechat_open_id(), model.getWechat_union_id(), request_user_id, request_app_id, request_http_id, request_user_id);
//
//        renderSuccessJson(result);

        renderSuccessJson();
    }

    @ActionKey(Url.USER_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(User.USER_ID, User.ORGANIZATION_ID, User.ROLE_ID, User.USER_LEVEL_ID, User.USER_TYPE, User.USER_ACCOUNT, User.USER_PHONE, User.USER_EMAIL, User.USER_PASSWORD, User.USER_NAME, User.USER_AVATAR, User.WECHAT_OPEN_ID, User.WECHAT_UNION_ID, User.SYSTEM_VERSION);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        User user = userService.findByUser_id(model.getUser_id(), request_app_id, request_http_id, request_user_id);
        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(user.getApp_id());
        authenticateSystem_create_user_id(user.getSystem_create_user_id());

        Boolean result = userService.updateValidateSystem_version(model.getUser_id(), model.getOrganization_id(), model.getRole_id(), model.getUser_level_id(), model.getUser_type(), model.getUser_account(), model.getUser_phone(), model.getUser_email(), model.getUser_password(), model.getUser_name(), model.getUser_avatar(), model.getWechat_open_id(), model.getWechat_union_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(User.USER_ID, User.SYSTEM_VERSION);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        User user = userService.findByUser_id(model.getUser_id(), request_app_id, request_http_id, request_user_id);
        authenticateApp_id(user.getApp_id());
        authenticateSystem_create_user_id(user.getSystem_create_user_id());

        Boolean result = userService.deleteByUser_idAndSystem_update_user_idValidateSystem_version(model.getUser_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = userService.countByApp_id(request_app_id, request_app_id, request_http_id, request_user_id);
        List<User> resultList = userService.listByApp_idAndLimit(request_app_id, getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (User result : resultList) {
            result.keep(User.USER_ID, User.SYSTEM_VERSION);
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

        User user = userService.findByUser_id(model.getUser_id(), request_app_id, request_http_id, request_user_id);

        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(user.getApp_id());

        user.keep(User.USER_ID, User.SYSTEM_VERSION);

        renderSuccessJson(user);
    }

    @ActionKey(Url.USER_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.USER_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(User.USER_ID, User.ORGANIZATION_ID, User.ROLE_ID, User.USER_LEVEL_ID, User.USER_TYPE, User.USER_ACCOUNT, User.USER_PHONE, User.USER_EMAIL, User.USER_PASSWORD, User.USER_NAME, User.USER_AVATAR, User.WECHAT_OPEN_ID, User.WECHAT_UNION_ID, User.SYSTEM_VERSION);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        User user = userService.findByUser_id(model.getUser_id(), request_app_id, request_http_id, request_user_id);
        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(user.getApp_id());

        Boolean result = userService.updateValidateSystem_version(model.getUser_id(), model.getOrganization_id(), model.getRole_id(), model.getUser_level_id(), model.getUser_type(), model.getUser_account(), model.getUser_phone(), model.getUser_email(), model.getUser_password(), model.getUser_name(), model.getUser_avatar(), model.getWechat_open_id(), model.getWechat_union_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(User.USER_ID, User.SYSTEM_VERSION);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        User user = userService.findByUser_id(model.getUser_id(), request_app_id, request_http_id, request_user_id);
        authenticateApp_id(user.getApp_id());

        Boolean result = userService.deleteByUser_idAndSystem_update_user_idValidateSystem_version(model.getUser_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(User.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = userService.countByOrApp_id(model.getApp_id(), request_app_id, request_http_id, request_user_id);
        List<User> resultList = userService.listByOrApp_idAndLimit(model.getApp_id(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (User result : resultList) {
            result.keep(User.USER_ID, User.USER_NAME, User.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.USER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(User.USER_ID);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        User user = userService.findByUser_id(model.getUser_id(), request_app_id, request_http_id, request_user_id);

        user.keep(User.USER_ID, User.ORGANIZATION_ID, User.ROLE_ID, User.USER_LEVEL_ID, User.USER_TYPE, User.USER_ACCOUNT, User.USER_PHONE, User.USER_EMAIL, User.USER_PASSWORD, User.USER_NAME, User.USER_AVATAR, User.WECHAT_OPEN_ID, User.WECHAT_UNION_ID, User.SYSTEM_VERSION);

        renderSuccessJson(user);
    }

    @ActionKey(Url.USER_SYSTEM_ADMIN_SAVE)
    public void systemAdminSave() {
        validateRequest_app_id();
        validate(User.APP_ID, User.USER_TYPE, User.USER_ACCOUNT, User.USER_PASSWORD, User.USER_NAME);

        authenticateRequest_app_idAndRequest_user_id();

        User model = getModel(User.class);
        String user_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = userService.saveByUser_idAndApp_idAndUser_typeAndUser_accountAndUser_passwordAndUser_name(user_id, model.getApp_id(), model.getUser_type(), model.getUser_account(), model.getUser_password(), model.getUser_name(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(User.USER_ID, User.ORGANIZATION_ID, User.ROLE_ID, User.USER_LEVEL_ID, User.USER_TYPE, User.USER_ACCOUNT, User.USER_PHONE, User.USER_EMAIL, User.USER_PASSWORD, User.USER_NAME, User.USER_AVATAR, User.WECHAT_OPEN_ID, User.WECHAT_UNION_ID, User.SYSTEM_VERSION);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = userService.updateValidateSystem_version(model.getUser_id(), model.getOrganization_id(), model.getRole_id(), model.getUser_level_id(), model.getUser_type(), model.getUser_account(), model.getUser_phone(), model.getUser_email(), model.getUser_password(), model.getUser_name(), model.getUser_avatar(), model.getWechat_open_id(), model.getWechat_union_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(User.USER_ID, User.SYSTEM_VERSION);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = userService.deleteByUser_idAndSystem_update_user_idValidateSystem_version(model.getUser_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

}