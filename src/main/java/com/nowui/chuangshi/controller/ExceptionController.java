package com.nowui.chuangshi.controller;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Exception;
import com.nowui.chuangshi.service.ExceptionService;

import java.util.List;

public class ExceptionController extends Controller {

    private final ExceptionService exceptionService = new ExceptionService();

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

}