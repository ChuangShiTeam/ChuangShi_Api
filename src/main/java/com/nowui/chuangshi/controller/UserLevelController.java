package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.UserLevel;
import com.nowui.chuangshi.service.UserLevelService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class UserLevelController extends Controller {

    private final UserLevelService userLevelService = new UserLevelService();

    @ActionKey(Url.USER_LEVEL_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<UserLevel> resultList = userLevelService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN(), request_app_id, request_http_id, request_user_id);

        for (UserLevel result : resultList) {
            result.keep(UserLevel.USER_LEVEL_ID, UserLevel.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.USER_LEVEL_FIND)
    public void find() {
        validateRequest_app_id();
        validate(UserLevel.USER_LEVEL_ID);

        UserLevel model = getModel(UserLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        UserLevel user_level = userLevelService.findByUser_level_id(model.getUser_level_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(user_level.getApp_id());
        authenticateSystem_create_user_id(user_level.getSystem_create_user_id());

        user_level.keep(UserLevel.USER_LEVEL_ID, UserLevel.SYSTEM_VERSION);

        renderSuccessJson(user_level);
    }

    @ActionKey(Url.USER_LEVEL_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(UserLevel.USER_LEVEL_NAME, UserLevel.USER_LEVEL_VALUE, UserLevel.USER_LEVEL_SORT);

        UserLevel model = getModel(UserLevel.class);
        String user_level_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = userLevelService.save(user_level_id, request_app_id, model.getUser_level_name(), model.getUser_level_value(), model.getUser_level_sort(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_LEVEL_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(UserLevel.USER_LEVEL_ID, UserLevel.USER_LEVEL_NAME, UserLevel.USER_LEVEL_VALUE, UserLevel.USER_LEVEL_SORT, UserLevel.SYSTEM_VERSION);

        UserLevel model = getModel(UserLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        UserLevel user_level = userLevelService.findByUser_level_id(model.getUser_level_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(user_level.getApp_id());
        authenticateSystem_create_user_id(user_level.getSystem_create_user_id());

        Boolean result = userLevelService.updateValidateSystem_version(model.getUser_level_id(), model.getUser_level_name(), model.getUser_level_value(), model.getUser_level_sort(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_LEVEL_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(UserLevel.USER_LEVEL_ID, UserLevel.SYSTEM_VERSION);

        UserLevel model = getModel(UserLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        UserLevel user_level = userLevelService.findByUser_level_id(model.getUser_level_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(user_level.getApp_id());
        authenticateSystem_create_user_id(user_level.getSystem_create_user_id());

        Boolean result = userLevelService.deleteByUser_level_idAndSystem_update_user_idValidateSystem_version(model.getUser_level_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_LEVEL_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(UserLevel.USER_LEVEL_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        UserLevel model = getModel(UserLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = userLevelService.countByApp_idOrLikeUser_level_name(request_app_id, model.getUser_level_name(), request_app_id, request_http_id, request_user_id);
        List<UserLevel> resultList = userLevelService.listByApp_idOrLikeUser_level_nameAndLimit(request_app_id, model.getUser_level_name(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (UserLevel result : resultList) {
            result.keep(UserLevel.USER_LEVEL_ID, UserLevel.USER_LEVEL_NAME, UserLevel.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.USER_LEVEL_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(UserLevel.USER_LEVEL_ID);

        UserLevel model = getModel(UserLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        UserLevel user_level = userLevelService.findByUser_level_id(model.getUser_level_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(user_level.getApp_id());

        user_level.keep(UserLevel.USER_LEVEL_ID, UserLevel.USER_LEVEL_NAME, UserLevel.USER_LEVEL_VALUE, UserLevel.USER_LEVEL_SORT, UserLevel.SYSTEM_VERSION);

        renderSuccessJson(user_level);
    }

    @ActionKey(Url.USER_LEVEL_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.USER_LEVEL_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(UserLevel.USER_LEVEL_ID, UserLevel.USER_LEVEL_NAME, UserLevel.USER_LEVEL_VALUE, UserLevel.USER_LEVEL_SORT, UserLevel.SYSTEM_VERSION);

        UserLevel model = getModel(UserLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        UserLevel user_level = userLevelService.findByUser_level_id(model.getUser_level_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(user_level.getApp_id());

        Boolean result = userLevelService.updateValidateSystem_version(model.getUser_level_id(), model.getUser_level_name(), model.getUser_level_value(), model.getUser_level_sort(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_LEVEL_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(UserLevel.USER_LEVEL_ID, UserLevel.SYSTEM_VERSION);

        UserLevel model = getModel(UserLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        UserLevel user_level = userLevelService.findByUser_level_id(model.getUser_level_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(user_level.getApp_id());

        Boolean result = userLevelService.deleteByUser_level_idAndSystem_update_user_idValidateSystem_version(model.getUser_level_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_LEVEL_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(UserLevel.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        UserLevel model = getModel(UserLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = userLevelService.countByOrApp_idOrLikeUser_level_name(model.getApp_id(), model.getUser_level_name(), request_app_id, request_http_id, request_user_id);
        List<UserLevel> resultList = userLevelService.listByOrApp_idOrLikeUser_level_nameAndLimit(model.getApp_id(), model.getUser_level_name(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (UserLevel result : resultList) {
            result.keep(UserLevel.USER_LEVEL_ID, UserLevel.USER_LEVEL_NAME, UserLevel.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.USER_LEVEL_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(UserLevel.USER_LEVEL_ID);

        UserLevel model = getModel(UserLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        UserLevel user_level = userLevelService.findByUser_level_id(model.getUser_level_id(), request_app_id, request_http_id, request_user_id);

        user_level.keep(UserLevel.USER_LEVEL_ID, UserLevel.USER_LEVEL_NAME, UserLevel.USER_LEVEL_VALUE, UserLevel.USER_LEVEL_SORT, UserLevel.SYSTEM_VERSION);

        renderSuccessJson(user_level);
    }

    @ActionKey(Url.USER_LEVEL_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(UserLevel.APP_ID, UserLevel.USER_LEVEL_NAME, UserLevel.USER_LEVEL_VALUE, UserLevel.USER_LEVEL_SORT);

        UserLevel model = getModel(UserLevel.class);
        String user_level_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = userLevelService.save(user_level_id, model.getApp_id(), model.getUser_level_name(), model.getUser_level_value(), model.getUser_level_sort(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_LEVEL_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(UserLevel.USER_LEVEL_ID, UserLevel.USER_LEVEL_NAME, UserLevel.USER_LEVEL_VALUE, UserLevel.USER_LEVEL_SORT, UserLevel.SYSTEM_VERSION);

        UserLevel model = getModel(UserLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = userLevelService.updateValidateSystem_version(model.getUser_level_id(), model.getUser_level_name(), model.getUser_level_value(), model.getUser_level_sort(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.USER_LEVEL_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(UserLevel.USER_LEVEL_ID, UserLevel.SYSTEM_VERSION);

        UserLevel model = getModel(UserLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = userLevelService.deleteByUser_level_idAndSystem_update_user_idValidateSystem_version(model.getUser_level_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

}