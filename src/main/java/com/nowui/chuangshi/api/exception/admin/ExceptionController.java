package com.nowui.chuangshi.api.exception.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.exception.model.Exception;
import com.nowui.chuangshi.api.exception.service.ExceptionService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/exception")
public class ExceptionController extends Controller {

    @ActionKey("/admin/exception/list")
    public void list() {
        validateRequest(Exception.EXCEPTION_IS_CONFIRM, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Exception model = getModel(Exception.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = ExceptionService.instance.adminCount(request_app_id, model.getException_is_confirm());
        List<Exception> resultList = ExceptionService.instance.adminList(request_app_id, model.getException_is_confirm(), getM(), getN());

        validateResponse(Exception.EXCEPTION_ID, Exception.EXCEPTION_IS_CONFIRM, Exception.SYSTEM_CREATE_TIME, Exception.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/exception/find")
    public void find() {
        validateRequest(Exception.EXCEPTION_ID);

        Exception model = getModel(Exception.class);

        Exception result = ExceptionService.instance.find(model.getException_id());

        validateResponse(Exception.HTTP_ID, Exception.EXCEPTION_CONTENT, Exception.EXCEPTION_IS_CONFIRM, Exception.EXCEPTION_REMARK, Exception.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/exception/save")
    public void save() {
        validateRequest(Exception.HTTP_ID, Exception.EXCEPTION_CONTENT, Exception.EXCEPTION_IS_CONFIRM, Exception.EXCEPTION_REMARK);

        Exception model = getModel(Exception.class);
        model.setException_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = ExceptionService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/exception/update")
    public void update() {
        validateRequest(Exception.EXCEPTION_ID, Exception.HTTP_ID, Exception.EXCEPTION_CONTENT, Exception.EXCEPTION_IS_CONFIRM, Exception.EXCEPTION_REMARK, Exception.SYSTEM_VERSION);

        Exception model = getModel(Exception.class);
        String request_user_id = getRequest_user_id();

        Boolean result = ExceptionService.instance.update(model, model.getException_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/exception/delete")
    public void delete() {
        validateRequest(Exception.EXCEPTION_ID, Exception.SYSTEM_VERSION);

        Exception model = getModel(Exception.class);
        String request_user_id = getRequest_user_id();

        Boolean result = ExceptionService.instance.delete(model.getException_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}