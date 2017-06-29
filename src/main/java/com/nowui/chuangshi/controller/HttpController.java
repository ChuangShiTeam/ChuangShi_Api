package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Http;
import com.nowui.chuangshi.service.HttpService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class HttpController extends Controller {

    private final HttpService httpService = new HttpService();

    @ActionKey(Url.HTTP_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Http> resultList = httpService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Http result : resultList) {
            result.keep(Http.HTTP_ID, Http.HTTP_URL, Http.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.HTTP_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Http.HTTP_ID);

        Http model = getModel(Http.class);

        authenticateRequest_app_idAndRequest_user_id();

        Http http = httpService.findByHttp_id(model.getHttp_id());

        authenticateApp_id(http.getApp_id());
        authenticateSystem_create_user_id(http.getSystem_create_user_id());

        http.keep(Http.HTTP_ID, Http.SYSTEM_VERSION);

        renderSuccessJson(http);
    }

    @ActionKey(Url.HTTP_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Http.HTTP_URL, Http.HTTP_CODE, Http.HTTP_REQUEST, Http.HTTP_RESPONSE, Http.HTTP_TOKEN, Http.HTTP_PLATFORM, Http.HTTP_VERSION, Http.HTTP_IP_ADDRESS, Http.HTTP_RUN_TIME);

        Http model = getModel(Http.class);
        String http_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = httpService.save(http_id, request_app_id, model.getHttp_url(), model.getHttp_code(), model.getHttp_request(), model.getHttp_response(), model.getHttp_token(), model.getHttp_platform(), model.getHttp_version(), model.getHttp_ip_address(), model.getHttp_run_time(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.HTTP_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Http.HTTP_ID, Http.APP_ID, Http.HTTP_URL, Http.HTTP_CODE, Http.HTTP_REQUEST, Http.HTTP_RESPONSE, Http.HTTP_TOKEN, Http.HTTP_PLATFORM, Http.HTTP_VERSION, Http.HTTP_IP_ADDRESS, Http.HTTP_RUN_TIME, Http.SYSTEM_VERSION);

        Http model = getModel(Http.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Http http = httpService.findByHttp_id(model.getHttp_id());

        authenticateApp_id(http.getApp_id());
        authenticateSystem_create_user_id(http.getSystem_create_user_id());

        Boolean result = httpService.updateValidateSystem_version(model.getHttp_id(), model.getHttp_url(), model.getHttp_code(), model.getHttp_request(), model.getHttp_response(), model.getHttp_token(), model.getHttp_platform(), model.getHttp_version(), model.getHttp_ip_address(), model.getHttp_run_time(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.HTTP_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Http.HTTP_ID, Http.SYSTEM_VERSION);

        Http model = getModel(Http.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Http http = httpService.findByHttp_id(model.getHttp_id());
        authenticateApp_id(http.getApp_id());
        authenticateSystem_create_user_id(http.getSystem_create_user_id());

        Boolean result = httpService.deleteByHttp_idAndSystem_update_user_idValidateSystem_version(model.getHttp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.HTTP_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = httpService.countByApp_id(request_app_id);
        List<Http> resultList = httpService.listByApp_idAndLimit(request_app_id, getM(), getN());

        for (Http result : resultList) {
            result.keep(Http.HTTP_ID, Http.HTTP_URL, Http.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.HTTP_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Http.HTTP_ID);

        Http model = getModel(Http.class);

        authenticateRequest_app_idAndRequest_user_id();

        Http http = httpService.findByHttp_id(model.getHttp_id());

        authenticateApp_id(http.getApp_id());

        http.keep(Http.HTTP_ID, Http.SYSTEM_VERSION);

        renderSuccessJson(http);
    }

    @ActionKey(Url.HTTP_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.HTTP_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Http.HTTP_ID, Http.APP_ID, Http.HTTP_URL, Http.HTTP_CODE, Http.HTTP_REQUEST, Http.HTTP_RESPONSE, Http.HTTP_TOKEN, Http.HTTP_PLATFORM, Http.HTTP_VERSION, Http.HTTP_IP_ADDRESS, Http.HTTP_RUN_TIME, Http.SYSTEM_VERSION);

        Http model = getModel(Http.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Http http = httpService.findByHttp_id(model.getHttp_id());

        authenticateApp_id(http.getApp_id());

        Boolean result = httpService.updateValidateSystem_version(model.getHttp_id(), model.getHttp_url(), model.getHttp_code(), model.getHttp_request(), model.getHttp_response(), model.getHttp_token(), model.getHttp_platform(), model.getHttp_version(), model.getHttp_ip_address(), model.getHttp_run_time(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.HTTP_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Http.HTTP_ID, Http.SYSTEM_VERSION);

        Http model = getModel(Http.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Http http = httpService.findByHttp_id(model.getHttp_id());
        authenticateApp_id(http.getApp_id());

        Boolean result = httpService.deleteByHttp_idAndSystem_update_user_idValidateSystem_version(model.getHttp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.HTTP_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Http.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Http model = getModel(Http.class);

        Integer total = httpService.countByOrApp_id(model.getApp_id());
        List<Http> resultList = httpService.listByOrApp_idAndLimit(model.getApp_id(), getM(), getN());

        for (Http result : resultList) {
            result.keep(Http.HTTP_ID, Http.HTTP_URL, Http.HTTP_CODE, Http.HTTP_PLATFORM, Http.HTTP_VERSION, Http.HTTP_RUN_TIME, Http.SYSTEM_CREATE_TIME, Http.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.HTTP_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Http.HTTP_ID);

        Http model = getModel(Http.class);

        Http http = httpService.findByHttp_id(model.getHttp_id());

        http.keep(Http.HTTP_ID, Http.HTTP_URL, Http.HTTP_CODE, Http.HTTP_REQUEST, Http.HTTP_RESPONSE, Http.HTTP_TOKEN, Http.HTTP_PLATFORM, Http.HTTP_VERSION, Http.HTTP_IP_ADDRESS, Http.HTTP_RUN_TIME, Http.SYSTEM_VERSION);

        renderSuccessJson(http);
    }

    @ActionKey(Url.HTTP_SYSTEM_SAVE)
    public void systemSave() {
        save();
    }

    @ActionKey(Url.HTTP_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Http.HTTP_ID, Http.HTTP_URL, Http.HTTP_CODE, Http.HTTP_REQUEST, Http.HTTP_RESPONSE, Http.HTTP_TOKEN, Http.HTTP_PLATFORM, Http.HTTP_VERSION, Http.HTTP_IP_ADDRESS, Http.HTTP_RUN_TIME, Http.SYSTEM_VERSION);

        Http model = getModel(Http.class);
        String request_user_id = getRequest_user_id();

        Boolean result = httpService.updateValidateSystem_version(model.getHttp_id(), model.getHttp_url(), model.getHttp_code(), model.getHttp_request(), model.getHttp_response(), model.getHttp_token(), model.getHttp_platform(), model.getHttp_version(), model.getHttp_ip_address(), model.getHttp_run_time(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.HTTP_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Http.HTTP_ID, Http.SYSTEM_VERSION);

        Http model = getModel(Http.class);
        String request_user_id = getRequest_user_id();

        Boolean result = httpService.deleteByHttp_idAndSystem_update_user_idValidateSystem_version(model.getHttp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}