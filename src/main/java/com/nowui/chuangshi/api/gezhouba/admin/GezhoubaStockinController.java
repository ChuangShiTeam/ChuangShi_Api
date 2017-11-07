package com.nowui.chuangshi.api.gezhouba.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.gezhouba.model.GezhoubaStockin;
import com.nowui.chuangshi.api.gezhouba.service.GezhoubaStockinService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/gezhouba/stockin")
public class GezhoubaStockinController extends Controller {

    @ActionKey("/admin/gezhouba/stockin/list")
    public void list() {
        validateRequest(GezhoubaStockin.STOCKIN_NO, GezhoubaStockin.STOCKIN_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GezhoubaStockin model = getModel(GezhoubaStockin.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = GezhoubaStockinService.instance.adminCount(request_app_id, model.getStockin_no(), model.getStockin_name());
        List<GezhoubaStockin> resultList = GezhoubaStockinService.instance.adminList(request_app_id, model.getStockin_no(), model.getStockin_name(), getM(), getN());

        validateResponse(GezhoubaStockin.STOCKIN_ID, GezhoubaStockin.STOCKIN_NO, GezhoubaStockin.STOCKIN_NAME, GezhoubaStockin.STOCKIN_DATE, GezhoubaStockin.SUPPLIER_ID, GezhoubaStockin.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/gezhouba/stockin/find")
    public void find() {
        validateRequest(GezhoubaStockin.STOCKIN_ID);

        GezhoubaStockin model = getModel(GezhoubaStockin.class);

        GezhoubaStockin result = GezhoubaStockinService.instance.find(model.getStockin_id());

        validateResponse(GezhoubaStockin.STOCKIN_NO, GezhoubaStockin.STOCKIN_NAME, GezhoubaStockin.STOCKIN_DATE, GezhoubaStockin.SUPPLIER_ID, GezhoubaStockin.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/stockin/save")
    public void save() {
        validateRequest(GezhoubaStockin.STOCKIN_NO, GezhoubaStockin.STOCKIN_NAME, GezhoubaStockin.STOCKIN_DATE, GezhoubaStockin.SUPPLIER_ID);

        GezhoubaStockin model = getModel(GezhoubaStockin.class);
        model.setStockin_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaStockinService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/stockin/update")
    public void update() {
        validateRequest(GezhoubaStockin.STOCKIN_ID, GezhoubaStockin.STOCKIN_NO, GezhoubaStockin.STOCKIN_NAME, GezhoubaStockin.STOCKIN_DATE, GezhoubaStockin.SUPPLIER_ID, GezhoubaStockin.SYSTEM_VERSION);

        GezhoubaStockin model = getModel(GezhoubaStockin.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaStockinService.instance.update(model, model.getStockin_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/stockin/delete")
    public void delete() {
        validateRequest(GezhoubaStockin.STOCKIN_ID, GezhoubaStockin.SYSTEM_VERSION);

        GezhoubaStockin model = getModel(GezhoubaStockin.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaStockinService.instance.delete(model.getStockin_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}