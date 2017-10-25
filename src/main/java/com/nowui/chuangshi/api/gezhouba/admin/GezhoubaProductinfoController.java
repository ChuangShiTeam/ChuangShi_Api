package com.nowui.chuangshi.api.gezhouba.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.gezhouba.model.GezhoubaProductinfo;
import com.nowui.chuangshi.api.gezhouba.service.GezhoubaProductinfoService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/gezhouba/productinfo")
public class GezhoubaProductinfoController extends Controller {

    @ActionKey("/admin/gezhouba/productinfo/list")
    public void list() {
        validateRequest(GezhoubaProductinfo.PRODUCT_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GezhoubaProductinfo model = getModel(GezhoubaProductinfo.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = GezhoubaProductinfoService.instance.adminCount(request_app_id, model.getProduct_name());
        List<GezhoubaProductinfo> resultList = GezhoubaProductinfoService.instance.adminList(request_app_id, model.getProduct_name(), getM(), getN());

        validateResponse(GezhoubaProductinfo.PRODUCT_ID, GezhoubaProductinfo.PRODUCT_NAME, GezhoubaProductinfo.REMARK, GezhoubaProductinfo.PRODUCT_CART, GezhoubaProductinfo.PRODUCT_UNIT, GezhoubaProductinfo.PRODUCT_SPACE, GezhoubaProductinfo.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/gezhouba/productinfo/find")
    public void find() {
        validateRequest(GezhoubaProductinfo.PRODUCT_ID);

        GezhoubaProductinfo model = getModel(GezhoubaProductinfo.class);

        GezhoubaProductinfo result = GezhoubaProductinfoService.instance.find(model.getProduct_id());

        validateResponse(GezhoubaProductinfo.PRODUCT_NAME, GezhoubaProductinfo.REMARK, GezhoubaProductinfo.PRODUCT_CART, GezhoubaProductinfo.PRODUCT_UNIT, GezhoubaProductinfo.PRODUCT_SPACE, GezhoubaProductinfo.SUPPLIER_ID, GezhoubaProductinfo.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/productinfo/save")
    public void save() {
        validateRequest(GezhoubaProductinfo.PRODUCT_NAME, GezhoubaProductinfo.REMARK, GezhoubaProductinfo.PRODUCT_CART, GezhoubaProductinfo.PRODUCT_UNIT, GezhoubaProductinfo.PRODUCT_SPACE, GezhoubaProductinfo.SUPPLIER_ID);

        GezhoubaProductinfo model = getModel(GezhoubaProductinfo.class);
        model.setProduct_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaProductinfoService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/productinfo/update")
    public void update() {
        validateRequest(GezhoubaProductinfo.PRODUCT_ID, GezhoubaProductinfo.PRODUCT_NAME, GezhoubaProductinfo.REMARK, GezhoubaProductinfo.PRODUCT_CART, GezhoubaProductinfo.PRODUCT_UNIT, GezhoubaProductinfo.PRODUCT_SPACE, GezhoubaProductinfo.SUPPLIER_ID, GezhoubaProductinfo.SYSTEM_VERSION);

        GezhoubaProductinfo model = getModel(GezhoubaProductinfo.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaProductinfoService.instance.update(model, model.getProduct_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/productinfo/delete")
    public void delete() {
        validateRequest(GezhoubaProductinfo.PRODUCT_ID, GezhoubaProductinfo.SYSTEM_VERSION);

        GezhoubaProductinfo model = getModel(GezhoubaProductinfo.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaProductinfoService.instance.delete(model.getProduct_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}