package com.nowui.chuangshi.api.gezhouba.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.gezhouba.model.GezhoubaStockinfo;
import com.nowui.chuangshi.api.gezhouba.service.GezhoubaStockinfoService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/gezhouba/stockinfo")
public class GezhoubaStockinfoController extends Controller {

    @ActionKey("/admin/gezhouba/stockinfo/list")
    public void list() {
        validateRequest(GezhoubaStockinfo.SUPPLIER_ID, GezhoubaStockinfo.PRODUCT_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GezhoubaStockinfo model = getModel(GezhoubaStockinfo.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = GezhoubaStockinfoService.instance.adminCount(request_app_id, model.getSupplier_id(), model.getProduct_id());
        List<GezhoubaStockinfo> resultList = GezhoubaStockinfoService.instance.adminList(request_app_id, model.getSupplier_id(), model.getProduct_id(), getM(), getN());

        validateResponse(GezhoubaStockinfo.STOCK_ID, GezhoubaStockinfo.SUPPLIER_ID, GezhoubaStockinfo.PRODUCT_ID, GezhoubaStockinfo.STOCK_NUM, GezhoubaStockinfo.STOCK_LOCK_NUM, GezhoubaStockinfo.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/gezhouba/stockinfo/find")
    public void find() {
        validateRequest(GezhoubaStockinfo.STOCK_ID);

        GezhoubaStockinfo model = getModel(GezhoubaStockinfo.class);

        GezhoubaStockinfo result = GezhoubaStockinfoService.instance.find(model.getStock_id());

        validateResponse(GezhoubaStockinfo.SUPPLIER_ID, GezhoubaStockinfo.PRODUCT_ID, GezhoubaStockinfo.STOCK_NUM, GezhoubaStockinfo.STOCK_LOCK_NUM, GezhoubaStockinfo.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/stockinfo/save")
    public void save() {
        validateRequest(GezhoubaStockinfo.SUPPLIER_ID, GezhoubaStockinfo.PRODUCT_ID, GezhoubaStockinfo.STOCK_NUM, GezhoubaStockinfo.STOCK_LOCK_NUM);

        GezhoubaStockinfo model = getModel(GezhoubaStockinfo.class);
        model.setStock_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaStockinfoService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/stockinfo/update")
    public void update() {
        validateRequest(GezhoubaStockinfo.STOCK_ID, GezhoubaStockinfo.SUPPLIER_ID, GezhoubaStockinfo.PRODUCT_ID, GezhoubaStockinfo.STOCK_NUM, GezhoubaStockinfo.STOCK_LOCK_NUM, GezhoubaStockinfo.SYSTEM_VERSION);

        GezhoubaStockinfo model = getModel(GezhoubaStockinfo.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaStockinfoService.instance.update(model, model.getStock_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/stockinfo/delete")
    public void delete() {
        validateRequest(GezhoubaStockinfo.STOCK_ID, GezhoubaStockinfo.SYSTEM_VERSION);

        GezhoubaStockinfo model = getModel(GezhoubaStockinfo.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaStockinfoService.instance.delete(model.getStock_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}