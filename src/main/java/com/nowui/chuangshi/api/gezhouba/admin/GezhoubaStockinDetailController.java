package com.nowui.chuangshi.api.gezhouba.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.gezhouba.model.GezhoubaStockinDetail;
import com.nowui.chuangshi.api.gezhouba.service.GezhoubaStockinDetailService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/gezhouba/stockin/detail")
public class GezhoubaStockinDetailController extends Controller {

    @ActionKey("/admin/gezhouba/stockin/detail/list")
    public void list() {
        validateRequest(GezhoubaStockinDetail.PRODUCT_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GezhoubaStockinDetail model = getModel(GezhoubaStockinDetail.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = GezhoubaStockinDetailService.instance.adminCount(request_app_id, model.getProduct_name());
        List<GezhoubaStockinDetail> resultList = GezhoubaStockinDetailService.instance.adminList(request_app_id, model.getProduct_name(), getM(), getN());

        validateResponse(GezhoubaStockinDetail.STOCKIN_DETAIL_ID, GezhoubaStockinDetail.STOCKIN_ID, GezhoubaStockinDetail.PRODUCT_ID, GezhoubaStockinDetail.PRODUCT_NAME, GezhoubaStockinDetail.PRODUCT_CART, GezhoubaStockinDetail.PRODUCT_UNIT, GezhoubaStockinDetail.PRODUCT_SPACE, GezhoubaStockinDetail.STOCKIN_DETAIL_NUM, GezhoubaStockinDetail.FARMER_NAME, GezhoubaStockinDetail.PARTY_A_NAME, GezhoubaStockinDetail.PARTY_B_NAME, GezhoubaStockinDetail.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/gezhouba/stockin/detail/find")
    public void find() {
        validateRequest(GezhoubaStockinDetail.STOCKIN_DETAIL_ID);

        GezhoubaStockinDetail model = getModel(GezhoubaStockinDetail.class);

        GezhoubaStockinDetail result = GezhoubaStockinDetailService.instance.find(model.getStockin_detail_id());

        validateResponse(GezhoubaStockinDetail.STOCKIN_ID, GezhoubaStockinDetail.PRODUCT_ID, GezhoubaStockinDetail.PRODUCT_NAME, GezhoubaStockinDetail.PRODUCT_CART, GezhoubaStockinDetail.PRODUCT_UNIT, GezhoubaStockinDetail.PRODUCT_SPACE, GezhoubaStockinDetail.STOCKIN_DETAIL_NUM, GezhoubaStockinDetail.FARMER_NAME, GezhoubaStockinDetail.PARTY_A_NAME, GezhoubaStockinDetail.PARTY_B_NAME, GezhoubaStockinDetail.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/stockin/detail/save")
    public void save() {
        validateRequest(GezhoubaStockinDetail.STOCKIN_ID, GezhoubaStockinDetail.PRODUCT_ID, GezhoubaStockinDetail.PRODUCT_NAME, GezhoubaStockinDetail.PRODUCT_CART, GezhoubaStockinDetail.PRODUCT_UNIT, GezhoubaStockinDetail.PRODUCT_SPACE, GezhoubaStockinDetail.STOCKIN_DETAIL_NUM, GezhoubaStockinDetail.FARMER_NAME, GezhoubaStockinDetail.PARTY_A_NAME, GezhoubaStockinDetail.PARTY_B_NAME);

        GezhoubaStockinDetail model = getModel(GezhoubaStockinDetail.class);
        model.setStockin_detail_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaStockinDetailService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/stockin/detail/update")
    public void update() {
        validateRequest(GezhoubaStockinDetail.STOCKIN_DETAIL_ID, GezhoubaStockinDetail.STOCKIN_ID, GezhoubaStockinDetail.PRODUCT_ID, GezhoubaStockinDetail.PRODUCT_NAME, GezhoubaStockinDetail.PRODUCT_CART, GezhoubaStockinDetail.PRODUCT_UNIT, GezhoubaStockinDetail.PRODUCT_SPACE, GezhoubaStockinDetail.STOCKIN_DETAIL_NUM, GezhoubaStockinDetail.FARMER_NAME, GezhoubaStockinDetail.PARTY_A_NAME, GezhoubaStockinDetail.PARTY_B_NAME, GezhoubaStockinDetail.SYSTEM_VERSION);

        GezhoubaStockinDetail model = getModel(GezhoubaStockinDetail.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaStockinDetailService.instance.update(model, model.getStockin_detail_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/gezhouba/stockin/detail/delete")
    public void delete() {
        validateRequest(GezhoubaStockinDetail.STOCKIN_DETAIL_ID, GezhoubaStockinDetail.SYSTEM_VERSION);

        GezhoubaStockinDetail model = getModel(GezhoubaStockinDetail.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GezhoubaStockinDetailService.instance.delete(model.getStockin_detail_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}