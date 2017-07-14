package com.nowui.chuangshi.controller;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Http;
import com.nowui.chuangshi.service.HttpService;

import java.util.List;

public class HttpController extends Controller {

    private final HttpService httpService = new HttpService();

    @ActionKey(Url.HTTP_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = httpService.countByApp_id(request_app_id);
        List<Http> resultList = httpService.listByApp_idAndLimit(request_app_id, getM(), getN());

        for (Http result : resultList) {
            result.keep(Http.HTTP_ID, Http.HTTP_URL, Http.HTTP_CODE, Http.HTTP_PLATFORM, Http.HTTP_VERSION, Http.HTTP_IP_ADDRESS, Http.HTTP_RUN_TIME, Http.SYSTEM_CREATE_TIME, Http.SYSTEM_VERSION);
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

    @ActionKey(Url.HTTP_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Http.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Http model = getModel(Http.class);

        Integer total = httpService.countByOrApp_id(model.getApp_id());
        List<Http> resultList = httpService.listByOrApp_idAndLimit(model.getApp_id(), getM(), getN());

        for (Http result : resultList) {
            result.keep(Http.HTTP_ID, Http.HTTP_URL, Http.HTTP_CODE, Http.HTTP_PLATFORM, Http.HTTP_VERSION, Http.HTTP_IP_ADDRESS, Http.HTTP_RUN_TIME, Http.SYSTEM_CREATE_TIME, Http.SYSTEM_VERSION);
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

}