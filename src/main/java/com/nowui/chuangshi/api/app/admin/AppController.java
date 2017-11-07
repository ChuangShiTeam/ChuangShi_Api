package com.nowui.chuangshi.api.app.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.app.model.App;
import com.nowui.chuangshi.api.app.service.AppService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/app")
public class AppController extends Controller {

    @ActionKey("/admin/app/list")
    public void list() {
        validateRequest(App.APP_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        App model = getModel(App.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = AppService.instance.adminCount(request_app_id, model.getApp_name());
        List<App> resultList = AppService.instance.adminList(request_app_id, model.getApp_name(), getM(), getN());

        validateResponse(App.APP_ID, App.APP_NAME, App.APP_SECRET, App.WECHAT_APP_ID, App.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/app/find")
    public void find() {
        validateRequest(App.APP_ID);

        App model = getModel(App.class);

        App result = AppService.instance.find(model.getApp_id());

        validateResponse(App.APP_NAME, App.APP_SECRET, App.WECHAT_APP_ID, App.WECHAT_APP_SECRET, App.WECHAT_MCH_ID, App.WECHAT_MCH_KEY, App.WECHAT_TOKEN, App.WECHAT_ENCODING_AES_KEY, App.APP_IS_CREATE_WAREHOUSE, App.APP_IS_DELIVERY, App.APP_IS_AUDIT_MEMBER, App.APP_IS_COMMISSION, App.APP_COMMISSION_LEVEL, App.APP_WEBSITE_PATH, App.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/app/save")
    public void save() {
        validateRequest(App.APP_NAME, App.APP_SECRET, App.WECHAT_APP_ID, App.WECHAT_APP_SECRET, App.WECHAT_MCH_ID, App.WECHAT_MCH_KEY, App.WECHAT_TOKEN, App.WECHAT_ENCODING_AES_KEY, App.APP_IS_CREATE_WAREHOUSE, App.APP_IS_DELIVERY, App.APP_IS_AUDIT_MEMBER, App.APP_IS_COMMISSION, App.APP_COMMISSION_LEVEL, App.APP_WEBSITE_PATH);

        App model = getModel(App.class);
        model.setApp_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = AppService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/app/update")
    public void update() {
        validateRequest(App.APP_ID, App.APP_NAME, App.APP_SECRET, App.WECHAT_APP_ID, App.WECHAT_APP_SECRET, App.WECHAT_MCH_ID, App.WECHAT_MCH_KEY, App.WECHAT_TOKEN, App.WECHAT_ENCODING_AES_KEY, App.APP_IS_CREATE_WAREHOUSE, App.APP_IS_DELIVERY, App.APP_IS_AUDIT_MEMBER, App.APP_IS_COMMISSION, App.APP_COMMISSION_LEVEL, App.APP_WEBSITE_PATH, App.SYSTEM_VERSION);

        App model = getModel(App.class);
        String request_user_id = getRequest_user_id();

        Boolean result = AppService.instance.update(model, model.getApp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/app/delete")
    public void delete() {
        validateRequest(App.APP_ID, App.SYSTEM_VERSION);

        App model = getModel(App.class);
        String request_user_id = getRequest_user_id();

        Boolean result = AppService.instance.delete(model.getApp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}