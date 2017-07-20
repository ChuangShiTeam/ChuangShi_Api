package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.service.AppService;
import com.nowui.chuangshi.util.Util;

import java.util.ArrayList;
import java.util.List;

public class AppController extends Controller {

    private final AppService appService = new AppService();

    @ActionKey(Url.APP_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<App> resultList = appService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (App result : resultList) {
            result.keep(App.APP_ID, App.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.APP_FIND)
    public void find() {
        validateRequest_app_id();
        validate(App.APP_ID);

        App model = getModel(App.class);

        authenticateRequest_app_idAndRequest_user_id();

        App app = appService.findByApp_id(model.getApp_id());

        authenticateApp_id(app.getApp_id());
        authenticateSystem_create_user_id(app.getSystem_create_user_id());

        app.keep(App.APP_ID, App.SYSTEM_VERSION);

        renderSuccessJson(app);
    }

    @ActionKey(Url.APP_SAVE)
    public void save() {
    	validateRequest_app_id();
    	validate(App.APP_NAME, App.APP_SECRET, App.WECHAT_APP_ID, App.WECHAT_APP_SECRET, App.WECHAT_MCH_ID, App.WECHAT_MCH_KEY, App.WECHAT_TOKEN, App.WECHAT_ENCODING_AES_KEY, App.APP_IS_CREATE_WAREHOUSE, App.APP_IS_DELIVERY, App.APP_IS_AUDIT_MEMBER, App.APP_IS_COMMISSION, App.APP_COMMISSION_LEVEL);

        App model = getModel(App.class);
        String app_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = appService.save(app_id, model.getApp_name(), model.getApp_secret(), model.getWechat_app_id(), model.getWechat_app_secret(), model.getWechat_mch_id(), model.getWechat_mch_key(), model.getWechat_token(), model.getWechat_encoding_aes_key(), model.getApp_is_create_warehouse(), model.getApp_is_delivery(), model.getApp_is_audit_member(), model.getApp_is_commission(), model.getApp_commission_level(), request_user_id);
        
        renderSuccessJson(result);
    }

    @ActionKey(Url.APP_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(App.APP_ID, App.APP_NAME, App.APP_SECRET, App.WECHAT_APP_ID, App.WECHAT_APP_SECRET, App.WECHAT_MCH_ID, App.WECHAT_MCH_KEY, App.WECHAT_TOKEN, App.WECHAT_ENCODING_AES_KEY, App.APP_IS_CREATE_WAREHOUSE, App.APP_IS_DELIVERY, App.APP_IS_AUDIT_MEMBER, App.APP_IS_COMMISSION, App.APP_COMMISSION_LEVEL, App.SYSTEM_VERSION);

        App model = getModel(App.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        App app = appService.findByApp_id(model.getApp_id());

        authenticateApp_id(app.getApp_id());
        authenticateSystem_create_user_id(app.getSystem_create_user_id());

        Boolean result = appService.updateValidateSystem_version(model.getApp_id(), model.getApp_name(), model.getApp_secret(), model.getWechat_app_id(), model.getWechat_app_secret(), model.getWechat_mch_id(), model.getWechat_mch_key(), model.getWechat_token(), model.getWechat_encoding_aes_key(), model.getApp_is_create_warehouse(), model.getApp_is_delivery(), model.getApp_is_audit_member(), model.getApp_is_commission(), model.getApp_commission_level(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.APP_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(App.APP_ID, App.SYSTEM_VERSION);

        App model = getModel(App.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        App app = appService.findByApp_id(model.getApp_id());

        authenticateApp_id(app.getApp_id());
        authenticateSystem_create_user_id(app.getSystem_create_user_id());

        Boolean result = appService.deleteByApp_idAndSystem_update_user_idValidateSystem_version(model.getApp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.APP_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        App model = getModel(App.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = appService.countByApp_idOrLikeApp_name(request_app_id, model.getApp_name());
        List<App> resultList = appService.listByApp_idOrLikeApp_nameAndLimit(request_app_id, model.getApp_name(), getM(), getN());

        for (App result : resultList) {
            result.keep(App.APP_ID, App.APP_NAME, App.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.APP_ADMIN_ALL_LIST)
    public void adminAllList() {
        validateRequest_app_id();

        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        App app = appService.findByApp_id(request_app_id);
        app.keep(App.APP_ID, App.APP_NAME);

        List<App> resultList = new ArrayList<App>();
        resultList.add(app);

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.APP_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(App.APP_ID);

        App model = getModel(App.class);

        authenticateRequest_app_idAndRequest_user_id();

        App app = appService.findByApp_id(model.getApp_id());

        authenticateApp_id(app.getApp_id());

        app.keep(App.APP_ID, App.APP_NAME, App.APP_SECRET, App.WECHAT_APP_ID, App.WECHAT_APP_SECRET, App.WECHAT_MCH_ID, App.WECHAT_MCH_KEY, App.WECHAT_TOKEN, App.WECHAT_ENCODING_AES_KEY, App.APP_IS_CREATE_WAREHOUSE, App.APP_IS_DELIVERY, App.APP_IS_AUDIT_MEMBER, App.APP_IS_COMMISSION, App.APP_COMMISSION_LEVEL, App.SYSTEM_VERSION);

        renderSuccessJson(app);
    }

    @ActionKey(Url.APP_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.APP_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(App.APP_ID, App.APP_NAME, App.APP_SECRET, App.WECHAT_APP_ID, App.WECHAT_APP_SECRET, App.WECHAT_MCH_ID, App.WECHAT_MCH_KEY, App.WECHAT_TOKEN, App.WECHAT_ENCODING_AES_KEY, App.APP_IS_CREATE_WAREHOUSE, App.APP_IS_DELIVERY, App.APP_IS_AUDIT_MEMBER, App.APP_IS_COMMISSION, App.APP_COMMISSION_LEVEL, App.SYSTEM_VERSION);

        App model = getModel(App.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        App app = appService.findByApp_id(model.getApp_id());

        authenticateApp_id(app.getApp_id());

        Boolean result = appService.updateValidateSystem_version(model.getApp_id(), model.getApp_name(), model.getApp_secret(), model.getWechat_app_id(), model.getWechat_app_secret(), model.getWechat_mch_id(), model.getWechat_mch_key(), model.getWechat_token(), model.getWechat_encoding_aes_key(), model.getApp_is_create_warehouse(), model.getApp_is_delivery(), model.getApp_is_audit_member(), model.getApp_is_commission(), model.getApp_commission_level(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.APP_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(App.APP_ID, App.SYSTEM_VERSION);

        App model = getModel(App.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        App app = appService.findByApp_id(model.getApp_id());

        authenticateApp_id(app.getApp_id());

        Boolean result = appService.deleteByApp_idAndSystem_update_user_idValidateSystem_version(model.getApp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.APP_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(App.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        App model = getModel(App.class);

        Integer total = appService.countByOrApp_idOrLikeApp_name(model.getApp_id(), model.getApp_name());
        List<App> resultList = appService.listByOrApp_idOrLikeApp_nameAndLimit(model.getApp_id(), model.getApp_name(), getM(), getN());

        for (App result : resultList) {
            result.keep(App.APP_ID, App.APP_NAME, App.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.APP_SYSTEM_ALL_LIST)
    public void systemAllList() {
        validateRequest_app_id();

        List<App> resultList = appService.list();

        for (App result : resultList) {
            result.keep(App.APP_ID, App.APP_NAME);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.APP_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(App.APP_ID);

        App model = getModel(App.class);

        App app = appService.findByApp_id(model.getApp_id());

        app.keep(App.APP_ID, App.APP_NAME, App.APP_SECRET, App.WECHAT_APP_ID, App.WECHAT_APP_SECRET, App.WECHAT_MCH_ID, App.WECHAT_MCH_KEY, App.APP_IS_CREATE_WAREHOUSE, App.APP_IS_DELIVERY, App.APP_IS_AUDIT_MEMBER, App.APP_IS_COMMISSION, App.APP_COMMISSION_LEVEL, App.SYSTEM_VERSION);

        renderSuccessJson(app);
    }

    @ActionKey(Url.APP_SYSTEM_SAVE)
    public void systemSave() {
        save();
    }

    @ActionKey(Url.APP_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(App.SYSTEM_VERSION);

        App model = getModel(App.class);
        String request_user_id = getRequest_user_id();

        Boolean result = appService.updateValidateSystem_version(model.getApp_id(), model.getApp_name(), model.getApp_secret(), model.getWechat_app_id(), model.getWechat_app_secret(), model.getWechat_mch_id(), model.getWechat_mch_key(), model.getWechat_token(), model.getWechat_encoding_aes_key(), model.getApp_is_create_warehouse(), model.getApp_is_delivery(), model.getApp_is_audit_member(), model.getApp_is_commission(), model.getApp_commission_level(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.APP_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(App.APP_ID, App.SYSTEM_VERSION);

        App model = getModel(App.class);
        String request_user_id = getRequest_user_id();

        Boolean result = appService.deleteByApp_idAndSystem_update_user_idValidateSystem_version(model.getApp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}