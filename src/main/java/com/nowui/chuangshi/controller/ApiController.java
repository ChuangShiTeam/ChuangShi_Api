package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Api;
import com.nowui.chuangshi.service.ApiService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class ApiController extends Controller {

    private final ApiService apiService = new ApiService();

    @ActionKey(Url.API_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Api> resultList = apiService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Api result : resultList) {
            result.keep(Api.API_ID, Api.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.API_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Api.API_ID);

        Api model = getModel(Api.class);

        authenticateRequest_app_idAndRequest_user_id();

        Api api = apiService.findByApi_id(model.getApi_id());

        authenticateApp_id(api.getApp_id());
        authenticateSystem_create_user_id(api.getSystem_create_user_id());

        api.keep(Api.API_ID, Api.SYSTEM_VERSION);

        renderSuccessJson(api);
    }

    @ActionKey(Url.API_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Api.API_NAME, Api.API_URL);

        Api model = getModel(Api.class);
        String api_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = apiService.save(api_id, request_app_id, model.getApi_name(), model.getApi_url(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.API_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Api.API_ID, Api.API_NAME, Api.API_URL, Api.SYSTEM_VERSION);

        Api model = getModel(Api.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Api api = apiService.findByApi_id(model.getApi_id());

        authenticateApp_id(api.getApp_id());
        authenticateSystem_create_user_id(api.getSystem_create_user_id());

        Boolean result = apiService.updateValidateSystem_version(model.getApi_id(), model.getApi_name(), model.getApi_url(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.API_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Api.API_ID, Api.SYSTEM_VERSION);

        Api model = getModel(Api.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Api api = apiService.findByApi_id(model.getApi_id());

        authenticateApp_id(api.getApp_id());
        authenticateSystem_create_user_id(api.getSystem_create_user_id());

        Boolean result = apiService.deleteByApi_idAndSystem_update_user_idValidateSystem_version(model.getApi_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.API_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Api.API_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Api model = getModel(Api.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = apiService.countByApp_idOrLikeApi_name(request_app_id, model.getApi_name());
        List<Api> resultList = apiService.listByApp_idOrLikeApi_nameAndLimit(request_app_id, model.getApi_name(), getM(), getN());

        for (Api result : resultList) {
            result.keep(Api.API_ID, Api.API_NAME, Api.API_URL, Api.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.API_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Api.API_ID);

        Api model = getModel(Api.class);

        authenticateRequest_app_idAndRequest_user_id();

        Api api = apiService.findByApi_id(model.getApi_id());

        authenticateApp_id(api.getApp_id());

        api.keep(Api.APP_ID, Api.API_NAME, Api.API_URL, Api.SYSTEM_VERSION);

        renderSuccessJson(api);
    }

    @ActionKey(Url.API_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.API_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Api.API_ID, Api.API_NAME, Api.API_URL, Api.SYSTEM_VERSION);

        Api model = getModel(Api.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Api api = apiService.findByApi_id(model.getApi_id());

        authenticateApp_id(api.getApp_id());

        Boolean result = apiService.updateValidateSystem_version(model.getApi_id(), model.getApi_name(), model.getApi_url(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.API_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Api.API_ID, Api.SYSTEM_VERSION);

        Api model = getModel(Api.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Api api = apiService.findByApi_id(model.getApi_id());

        authenticateApp_id(api.getApp_id());

        Boolean result = apiService.deleteByApi_idAndSystem_update_user_idValidateSystem_version(model.getApi_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.API_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Api.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Api model = getModel(Api.class);

        Integer total = apiService.countByOrApp_idOrLikeApi_name(model.getApp_id(), model.getApi_name());
        List<Api> resultList = apiService.listByOrApp_idOrLikeApi_nameAndLimit(model.getApp_id(), model.getApi_name(), getM(), getN());

        for (Api result : resultList) {
            result.keep(Api.API_ID, Api.API_NAME, Api.API_URL, Api.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.API_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Api.API_ID);

        Api model = getModel(Api.class);

        Api api = apiService.findByApi_id(model.getApi_id());

        api.keep(Api.API_ID, Api.APP_ID, Api.API_NAME, Api.API_URL, Api.SYSTEM_VERSION);

        renderSuccessJson(api);
    }

    @ActionKey(Url.API_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Api.APP_ID, Api.API_NAME, Api.API_URL);

        authenticateRequest_app_idAndRequest_user_id();

        Api model = getModel(Api.class);
        String api_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = apiService.save(api_id, model.getApp_id(), model.getApi_name(), model.getApi_url(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.API_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Api.API_ID, Api.API_NAME, Api.API_URL, Api.SYSTEM_VERSION);

        Api model = getModel(Api.class);
        String request_user_id = getRequest_user_id();

        Boolean result = apiService.updateValidateSystem_version(model.getApi_id(), model.getApi_name(), model.getApi_url(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.API_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Api.API_ID, Api.SYSTEM_VERSION);

        Api model = getModel(Api.class);
        String request_user_id = getRequest_user_id();

        Boolean result = apiService.deleteByApi_idAndSystem_update_user_idValidateSystem_version(model.getApi_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}