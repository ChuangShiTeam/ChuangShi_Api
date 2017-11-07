package com.nowui.chuangshi.api.gezhouba.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.gezhouba.model.GezhoubaSupplierinfo;
import com.nowui.chuangshi.api.gezhouba.service.GezhoubaSupplierinfoService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/gezhouba/supplierinfo")
public class GezhoubaSupplierinfoController extends Controller {

    @ActionKey("/admin/gezhouba/supplierinfo/list")
    public void list() {
        validateRequest(GezhoubaSupplierinfo.SUPPLIER_NAME, GezhoubaSupplierinfo.SUPPLIER_ADDRESS, GezhoubaSupplierinfo.SUPPLIER_TEL, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GezhoubaSupplierinfo model = getModel(GezhoubaSupplierinfo.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = GezhoubaSupplierinfoService.instance.adminCount(request_app_id, model.getSupplier_name(), model.getSupplier_address(), model.getSupplier_tel());
        List<GezhoubaSupplierinfo> resultList = GezhoubaSupplierinfoService.instance.adminList(request_app_id, model.getSupplier_name(), model.getSupplier_address(), model.getSupplier_tel(), getM(), getN());

        validateResponse(GezhoubaSupplierinfo.SUPPLIER_ID, GezhoubaSupplierinfo.SUPPLIER_NAME, GezhoubaSupplierinfo.SUPPLIER_ADDRESS, GezhoubaSupplierinfo.SUPPLIER_TEL, GezhoubaSupplierinfo.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/gezhouba/supplierinfo/find")
    public void find() {
        validateRequest(GezhoubaSupplierinfo.SUPPLIER_ID);

        GezhoubaSupplierinfo model = getModel(GezhoubaSupplierinfo.class);

        GezhoubaSupplierinfo result = GezhoubaSupplierinfoService.instance.find(model.getSupplier_id());

        validateResponse(GezhoubaSupplierinfo.SUPPLIER_NAME, GezhoubaSupplierinfo.SUPPLIER_ADDRESS, GezhoubaSupplierinfo.SUPPLIER_TEL, GezhoubaSupplierinfo.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/supplierinfo/save")
    public void save() {
        validateRequest(GezhoubaSupplierinfo.SUPPLIER_NAME, GezhoubaSupplierinfo.SUPPLIER_ADDRESS, GezhoubaSupplierinfo.SUPPLIER_TEL);

        GezhoubaSupplierinfo model = getModel(GezhoubaSupplierinfo.class);
        model.setSupplier_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaSupplierinfoService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/supplierinfo/update")
    public void update() {
        validateRequest(GezhoubaSupplierinfo.SUPPLIER_ID, GezhoubaSupplierinfo.SUPPLIER_NAME, GezhoubaSupplierinfo.SUPPLIER_ADDRESS, GezhoubaSupplierinfo.SUPPLIER_TEL, GezhoubaSupplierinfo.SYSTEM_VERSION);

        GezhoubaSupplierinfo model = getModel(GezhoubaSupplierinfo.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaSupplierinfoService.instance.update(model, model.getSupplier_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/supplierinfo/delete")
    public void delete() {
        validateRequest(GezhoubaSupplierinfo.SUPPLIER_ID, GezhoubaSupplierinfo.SYSTEM_VERSION);

        GezhoubaSupplierinfo model = getModel(GezhoubaSupplierinfo.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaSupplierinfoService.instance.delete(model.getSupplier_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}