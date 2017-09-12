package com.nowui.chuangshi.api.http.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.http.model.Http;
import com.nowui.chuangshi.api.http.service.HttpService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/http")
public class HttpController extends Controller {

    @ActionKey("/admin/http/list")
    public void list() {
        validateRequest(Http.HTTP_URL, Http.HTTP_CODE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Http model = getModel(Http.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = HttpService.instance.adminCount(request_app_id, model.getHttp_url(), model.getHttp_code());
        List<Http> resultList = HttpService.instance.adminList(request_app_id, model.getHttp_url(), model.getHttp_code(), getM(), getN());

        validateResponse(Http.HTTP_ID, Http.HTTP_URL, Http.HTTP_CODE, Http.HTTP_PLATFORM, Http.HTTP_VERSION, Http.HTTP_IP_ADDRESS, Http.HTTP_RUN_TIME, Http.SYSTEM_CREATE_TIME, Http.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/http/find")
    public void find() {
        validateRequest(Http.HTTP_ID);

        Http model = getModel(Http.class);

        Http result = HttpService.instance.find(model.getHttp_id());

        validateResponse(Http.HTTP_URL, Http.HTTP_CODE, Http.HTTP_REQUEST, Http.HTTP_RESPONSE, Http.HTTP_TOKEN, Http.HTTP_PLATFORM, Http.HTTP_VERSION, Http.HTTP_IP_ADDRESS, Http.HTTP_RUN_TIME, Http.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/http/save")
    public void save() {
        validateRequest(Http.HTTP_URL, Http.HTTP_CODE, Http.HTTP_REQUEST, Http.HTTP_RESPONSE, Http.HTTP_TOKEN, Http.HTTP_PLATFORM, Http.HTTP_VERSION, Http.HTTP_IP_ADDRESS, Http.HTTP_RUN_TIME);

        Http model = getModel(Http.class);
        model.setHttp_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = HttpService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/http/update")
    public void update() {
        validateRequest(Http.HTTP_ID, Http.HTTP_URL, Http.HTTP_CODE, Http.HTTP_REQUEST, Http.HTTP_RESPONSE, Http.HTTP_TOKEN, Http.HTTP_PLATFORM, Http.HTTP_VERSION, Http.HTTP_IP_ADDRESS, Http.HTTP_RUN_TIME, Http.SYSTEM_VERSION);

        Http model = getModel(Http.class);
        String request_user_id = getRequest_user_id();

        Boolean result = HttpService.instance.update(model, model.getHttp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/http/delete")
    public void delete() {
        validateRequest(Http.HTTP_ID, Http.SYSTEM_VERSION);

        Http model = getModel(Http.class);
        String request_user_id = getRequest_user_id();

        Boolean result = HttpService.instance.delete(model.getHttp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}