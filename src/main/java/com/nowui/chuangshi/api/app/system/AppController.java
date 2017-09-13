package com.nowui.chuangshi.api.app.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.app.model.App;
import com.nowui.chuangshi.api.app.service.AppService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(SystemInterceptor.class)
@ControllerKey("/system/app")
public class AppController extends Controller {

    @ActionKey("/system/app/list")
    public void list() {
        validateRequest(App.APP_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        App model = getModel(App.class);

        Integer resultCount = AppService.instance.systemCount(model.getApp_name());
        List<App> resultList = AppService.instance.systemList(model.getApp_name(), getM(), getN());

        validateResponse(App.APP_ID, App.APP_NAME, App.APP_SECRET, App.WECHAT_APP_ID, App.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/system/app/all/list")
    public void allList() {
        List<App> resultList = AppService.instance.allList();

        validateResponse(App.APP_ID, App.APP_NAME);

        renderSuccessJson(resultList);
    }

    @ActionKey("/system/app/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/save")
    public void save() {
        validateRequest(App.APP_NAME, App.APP_SECRET, App.WECHAT_APP_ID, App.WECHAT_APP_SECRET, App.WECHAT_MCH_ID, App.WECHAT_MCH_KEY, App.WECHAT_TOKEN, App.WECHAT_ENCODING_AES_KEY, App.APP_IS_CREATE_WAREHOUSE, App.APP_IS_DELIVERY, App.APP_IS_AUDIT_MEMBER, App.APP_IS_COMMISSION, App.APP_COMMISSION_LEVEL, App.APP_WEBSITE_PATH);

        App model = getModel(App.class);
        model.setApp_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = AppService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/system/app/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/delete")
    public void delete() {

        renderSuccessJson();
    }

}