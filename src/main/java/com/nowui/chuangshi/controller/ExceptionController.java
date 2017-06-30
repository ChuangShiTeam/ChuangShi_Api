package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Exception;
import com.nowui.chuangshi.service.ExceptionService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class ExceptionController extends Controller {

    private final ExceptionService exceptionService = new ExceptionService();

    @ActionKey(Url.EXCEPTION_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Exception> resultList = exceptionService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Exception result : resultList) {
            result.keep(Exception.EXCEPTION_ID, Exception.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.EXCEPTION_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Exception.EXCEPTION_ID);

        Exception model = getModel(Exception.class);

        authenticateRequest_app_idAndRequest_user_id();

        Exception exception = exceptionService.findByException_id(model.getException_id());

        authenticateApp_id(exception.getApp_id());
        authenticateSystem_create_user_id(exception.getSystem_create_user_id());

        exception.keep(Exception.EXCEPTION_ID, Exception.SYSTEM_VERSION);

        renderSuccessJson(exception);
    }

    @ActionKey(Url.EXCEPTION_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Exception.HTTP_ID, Exception.EXCEPTION_CONTENT, Exception.EXCEPTION_IS_CONFIRM, Exception.EXCEPTION_REMARK);

        Exception model = getModel(Exception.class);
        String exception_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = exceptionService.save(exception_id, request_app_id, model.getHttp_id(), model.getException_content(), model.getException_is_confirm(), model.getException_remark(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.EXCEPTION_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Exception.EXCEPTION_ID, Exception.HTTP_ID, Exception.EXCEPTION_CONTENT, Exception.EXCEPTION_IS_CONFIRM, Exception.EXCEPTION_REMARK, Exception.SYSTEM_VERSION);

        Exception model = getModel(Exception.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Exception exception = exceptionService.findByException_id(model.getException_id());

        authenticateApp_id(exception.getApp_id());
        authenticateSystem_create_user_id(exception.getSystem_create_user_id());

        Boolean result = exceptionService.updateValidateSystem_version(model.getException_id(), model.getHttp_id(), model.getException_content(), model.getException_is_confirm(), model.getException_remark(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.EXCEPTION_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Exception.EXCEPTION_ID, Exception.SYSTEM_VERSION);

        Exception model = getModel(Exception.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Exception exception = exceptionService.findByException_id(model.getException_id());

        authenticateApp_id(exception.getApp_id());
        authenticateSystem_create_user_id(exception.getSystem_create_user_id());

        Boolean result = exceptionService.deleteByException_idAndSystem_update_user_idValidateSystem_version(model.getException_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.EXCEPTION_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = exceptionService.countByApp_id(request_app_id);
        List<Exception> resultList = exceptionService.listByApp_idAndLimit(request_app_id, getM(), getN());

        for (Exception result : resultList) {
            result.keep(Exception.EXCEPTION_ID, Exception.EXCEPTION_CONTENT, Exception.EXCEPTION_IS_CONFIRM, Exception.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.EXCEPTION_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Exception.EXCEPTION_ID);

        Exception model = getModel(Exception.class);

        authenticateRequest_app_idAndRequest_user_id();

        Exception exception = exceptionService.findByException_id(model.getException_id());

        authenticateApp_id(exception.getApp_id());

        exception.keep(Exception.EXCEPTION_ID, Exception.EXCEPTION_CONTENT, Exception.EXCEPTION_IS_CONFIRM, Exception.EXCEPTION_REMARK, Exception.SYSTEM_VERSION);

        renderSuccessJson(exception);
    }

    @ActionKey(Url.EXCEPTION_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.EXCEPTION_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Exception.EXCEPTION_ID, Exception.HTTP_ID, Exception.EXCEPTION_CONTENT, Exception.EXCEPTION_IS_CONFIRM, Exception.EXCEPTION_REMARK, Exception.SYSTEM_VERSION);

        Exception model = getModel(Exception.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Exception exception = exceptionService.findByException_id(model.getException_id());

        authenticateApp_id(exception.getApp_id());

        Boolean result = exceptionService.updateValidateSystem_version(model.getException_id(), model.getHttp_id(), model.getException_content(), model.getException_is_confirm(), model.getException_remark(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.EXCEPTION_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Exception.EXCEPTION_ID, Exception.SYSTEM_VERSION);

        Exception model = getModel(Exception.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Exception exception = exceptionService.findByException_id(model.getException_id());
        authenticateApp_id(exception.getApp_id());

        Boolean result = exceptionService.deleteByException_idAndSystem_update_user_idValidateSystem_version(model.getException_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.EXCEPTION_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Exception.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Exception model = getModel(Exception.class);

        Integer total = exceptionService.countByOrApp_id(model.getApp_id());
        List<Exception> resultList = exceptionService.listByOrApp_idAndLimit(model.getApp_id(), getM(), getN());

        for (Exception result : resultList) {
            result.keep(Exception.EXCEPTION_ID, Exception.EXCEPTION_CONTENT, Exception.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.EXCEPTION_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Exception.EXCEPTION_ID);

        Exception model = getModel(Exception.class);

        Exception exception = exceptionService.findByException_id(model.getException_id());

        exception.keep(Exception.EXCEPTION_ID, Exception.EXCEPTION_CONTENT, Exception.EXCEPTION_IS_CONFIRM, Exception.EXCEPTION_REMARK, Exception.SYSTEM_VERSION);

        renderSuccessJson(exception);
    }

    @ActionKey(Url.EXCEPTION_SYSTEM_SAVE)
    public void systemSave() {
        save();
    }

    @ActionKey(Url.EXCEPTION_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Exception.EXCEPTION_ID, Exception.HTTP_ID, Exception.EXCEPTION_CONTENT, Exception.EXCEPTION_IS_CONFIRM, Exception.EXCEPTION_REMARK, Exception.SYSTEM_VERSION);

        Exception model = getModel(Exception.class);
        String request_user_id = getRequest_user_id();

        Boolean result = exceptionService.updateValidateSystem_version(model.getException_id(), model.getHttp_id(), model.getException_content(), model.getException_is_confirm(), model.getException_remark(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.EXCEPTION_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Exception.EXCEPTION_ID, Exception.SYSTEM_VERSION);

        Exception model = getModel(Exception.class);
        String request_user_id = getRequest_user_id();

        Boolean result = exceptionService.deleteByException_idAndSystem_update_user_idValidateSystem_version(model.getException_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}