package com.nowui.chuangshi.api.renault.admin;


import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultNewYearShare;
import com.nowui.chuangshi.api.renault.service.RenaultNewYearShareService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

@ControllerKey("/admin/renault/new/year/share")
public class RenaultNewYearShareController extends Controller {

    @ActionKey("/admin/renault/new/year/share/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE);
        
        String request_app_id = getRequest_app_id();

        Integer resultCount = RenaultNewYearShareService.instance.adminCount(request_app_id);
        List<RenaultNewYearShare> resultList = RenaultNewYearShareService.instance.adminList(request_app_id, getM(), getN());

        validateResponse(RenaultNewYearShare.APP_ID, RenaultNewYearShare.NEW_YEAR_SHARE_NUM, RenaultNewYearShare.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/renault/new/year/share/find")
    public void find() {
        validateRequest(RenaultNewYearShare.APP_ID);

        RenaultNewYearShare model = getModel(RenaultNewYearShare.class);

        RenaultNewYearShare result = RenaultNewYearShareService.instance.find(model.getApp_id());

        validateResponse(RenaultNewYearShare.NEW_YEAR_SHARE_NUM, RenaultNewYearShare.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/new/year/share/save")
    public void save() {
        validateRequest(RenaultNewYearShare.APP_ID, RenaultNewYearShare.NEW_YEAR_SHARE_NUM);

        RenaultNewYearShare model = getModel(RenaultNewYearShare.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultNewYearShareService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/new/year/share/update")
    public void update() {
        validateRequest(RenaultNewYearShare.APP_ID, RenaultNewYearShare.NEW_YEAR_SHARE_NUM, RenaultNewYearShare.SYSTEM_VERSION);

        RenaultNewYearShare model = getModel(RenaultNewYearShare.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultNewYearShareService.instance.update(model, model.getApp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/new/year/share/delete")
    public void delete() {
        validateRequest(RenaultNewYearShare.APP_ID, RenaultNewYearShare.SYSTEM_VERSION);

        RenaultNewYearShare model = getModel(RenaultNewYearShare.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultNewYearShareService.instance.delete(model.getApp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}