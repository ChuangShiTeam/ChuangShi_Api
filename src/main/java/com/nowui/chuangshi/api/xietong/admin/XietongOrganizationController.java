package com.nowui.chuangshi.api.xietong.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongOrganization;
import com.nowui.chuangshi.api.xietong.service.XietongOrganizationService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/organization")
public class XietongOrganizationController extends Controller {

    @ActionKey("/admin/xietong/organization/list")
    public void list() {
        validateRequest(XietongOrganization.ORGANIZATION_NAME, XietongOrganization.ORGANIZATION_CODE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongOrganization model = getModel(XietongOrganization.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongOrganizationService.instance.adminCount(request_app_id, model.getOrganization_name(), model.getOrganization_code());
        List<XietongOrganization> resultList = XietongOrganizationService.instance.adminList(request_app_id, model.getOrganization_name(), model.getOrganization_code(), getM(), getN());

        validateResponse(XietongOrganization.ORGANIZATION_ID, XietongOrganization.ORGANIZATION_NAME, XietongOrganization.ORGANIZATION_CODE, XietongOrganization.ORGANIZAITON_SORT, XietongOrganization.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }
    
    @ActionKey("/admin/xietong/organization/all/list")
    public void allList() {
        String request_app_id = getRequest_app_id();

        List<XietongOrganization> resultList = XietongOrganizationService.instance.allList(request_app_id);

        validateResponse(XietongOrganization.ORGANIZATION_ID, XietongOrganization.ORGANIZATION_NAME, XietongOrganization.ORGANIZATION_CODE, XietongOrganization.ORGANIZAITON_SORT);

        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/xietong/organization/find")
    public void find() {
        validateRequest(XietongOrganization.ORGANIZATION_ID);

        XietongOrganization model = getModel(XietongOrganization.class);

        XietongOrganization result = XietongOrganizationService.instance.find(model.getOrganization_id());

        validateResponse(XietongOrganization.ORGANIZATION_NAME, XietongOrganization.ORGANIZATION_CODE, XietongOrganization.ORGANIZAITON_SORT, XietongOrganization.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/organization/save")
    public void save() {
        validateRequest(XietongOrganization.ORGANIZATION_NAME, XietongOrganization.ORGANIZATION_CODE, XietongOrganization.ORGANIZAITON_SORT);

        XietongOrganization model = getModel(XietongOrganization.class);
        model.setOrganization_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = XietongOrganizationService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/organization/update")
    public void update() {
        validateRequest(XietongOrganization.ORGANIZATION_ID, XietongOrganization.ORGANIZATION_NAME, XietongOrganization.ORGANIZATION_CODE, XietongOrganization.ORGANIZAITON_SORT, XietongOrganization.SYSTEM_VERSION);

        XietongOrganization model = getModel(XietongOrganization.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongOrganizationService.instance.update(model, model.getOrganization_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/organization/delete")
    public void delete() {
        validateRequest(XietongOrganization.ORGANIZATION_ID, XietongOrganization.SYSTEM_VERSION);

        XietongOrganization model = getModel(XietongOrganization.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongOrganizationService.instance.delete(model.getOrganization_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}