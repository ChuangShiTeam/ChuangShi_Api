package com.nowui.chuangshi.api.renault.admin;


import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultNewYear;
import com.nowui.chuangshi.api.renault.service.RenaultNewYearService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/admin/renault/new/year")
public class RenaultNewYearController extends Controller {

    @ActionKey("/admin/renault/new/year/list")
    public void list() {
        validateRequest(RenaultNewYear.NEW_YEAR_NAME, RenaultNewYear.NEW_YEAR_PHONE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        RenaultNewYear model = getModel(RenaultNewYear.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = RenaultNewYearService.instance.adminCount(request_app_id, model.getNew_year_name(), model.getNew_year_phone());
        List<RenaultNewYear> resultList = RenaultNewYearService.instance.adminList(request_app_id, model.getNew_year_name(), model.getNew_year_phone(), getM(), getN());

        validateResponse(RenaultNewYear.NEW_YEAR_ID, RenaultNewYear.NEW_YEAR_NAME, RenaultNewYear.NEW_YEAR_PHONE, RenaultNewYear.NEW_YEAR_SUMMARY, RenaultNewYear.NEW_YEAR_WISH, RenaultNewYear.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/renault/new/year/find")
    public void find() {
        validateRequest(RenaultNewYear.NEW_YEAR_ID);

        RenaultNewYear model = getModel(RenaultNewYear.class);

        RenaultNewYear result = RenaultNewYearService.instance.find(model.getNew_year_id());

        validateResponse(RenaultNewYear.NEW_YEAR_NAME, RenaultNewYear.NEW_YEAR_PHONE, RenaultNewYear.NEW_YEAR_SUMMARY, RenaultNewYear.NEW_YEAR_WISH, RenaultNewYear.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/new/year/save")
    public void save() {
        validateRequest(RenaultNewYear.NEW_YEAR_NAME, RenaultNewYear.NEW_YEAR_PHONE, RenaultNewYear.NEW_YEAR_SUMMARY, RenaultNewYear.NEW_YEAR_WISH);

        RenaultNewYear model = getModel(RenaultNewYear.class);
        model.setNew_year_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultNewYearService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/new/year/update")
    public void update() {
        validateRequest(RenaultNewYear.NEW_YEAR_ID, RenaultNewYear.NEW_YEAR_NAME, RenaultNewYear.NEW_YEAR_PHONE, RenaultNewYear.NEW_YEAR_SUMMARY, RenaultNewYear.NEW_YEAR_WISH, RenaultNewYear.SYSTEM_VERSION);

        RenaultNewYear model = getModel(RenaultNewYear.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultNewYearService.instance.update(model, model.getNew_year_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/renault/new/year/delete")
    public void delete() {
        validateRequest(RenaultNewYear.NEW_YEAR_ID, RenaultNewYear.SYSTEM_VERSION);

        RenaultNewYear model = getModel(RenaultNewYear.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RenaultNewYearService.instance.delete(model.getNew_year_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    @ActionKey("/admin/renault/new/year/all/export")
    public void allExport() {
        render(RenaultNewYearService.instance.allExport());
    }

}