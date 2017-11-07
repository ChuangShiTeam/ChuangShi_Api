package com.nowui.chuangshi.api.uni.admin;

import java.util.List;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.uni.model.UniPv;
import com.nowui.chuangshi.api.uni.service.UniPvService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;


@ControllerKey("/admin/uni/pv")
public class UniPvController extends Controller {

    @ActionKey("/admin/uni/pv/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        Integer resultCount = UniPvService.instance.adminCount(request_app_id);
        List<UniPv> resultList = UniPvService.instance.adminList(request_app_id, getM(), getN());

        validateResponse(UniPv.APP_ID, UniPv.PV, UniPv.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/uni/pv/find")
    public void find() {
        validateRequest(UniPv.APP_ID);

        UniPv model = getModel(UniPv.class);

        UniPv result = UniPvService.instance.find(model.getApp_id());

        validateResponse(UniPv.PV, UniPv.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/uni/pv/save")
    public void save() {
        validateRequest(UniPv.PV);

        UniPv model = getModel(UniPv.class);
        model.setApp_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = UniPvService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/uni/pv/update")
    public void update() {
        validateRequest(UniPv.APP_ID, UniPv.PV, UniPv.SYSTEM_VERSION);

        UniPv model = getModel(UniPv.class);
        String request_user_id = getRequest_user_id();

        Boolean result = UniPvService.instance.update(model, model.getApp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/uni/pv/delete")
    public void delete() {
        validateRequest(UniPv.APP_ID, UniPv.SYSTEM_VERSION);

        UniPv model = getModel(UniPv.class);
        String request_user_id = getRequest_user_id();

        Boolean result = UniPvService.instance.delete(model.getApp_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}