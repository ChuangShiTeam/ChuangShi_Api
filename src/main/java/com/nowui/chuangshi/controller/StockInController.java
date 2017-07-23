package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.StockIn;
import com.nowui.chuangshi.service.StockInService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class StockInController extends Controller {

    private final StockInService stockInService = new StockInService();

    @ActionKey(Url.STOCK_IN_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(StockIn.STOCK_IN_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        StockIn model = getModel(StockIn.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = stockInService.countByApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_name(request_app_id, model.getWarehouse_id(), model.getStock_in_type(), model.getUser_name());
        List<StockIn> resultList = stockInService.listByApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_nameAndLimit(request_app_id, model.getWarehouse_id(), model.getStock_in_type(), model.getUser_name(), getM(), getN());

        for (StockIn result : resultList) {
            result.keep(StockIn.STOCK_IN_ID, StockIn.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.STOCK_IN_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(StockIn.STOCK_IN_ID);

        StockIn model = getModel(StockIn.class);

        authenticateRequest_app_idAndRequest_user_id();

        StockIn stock_in = stockInService.findByStock_in_id(model.getStock_in_id());

        authenticateApp_id(stock_in.getApp_id());

        stock_in.keep(StockIn.STOCK_IN_ID, StockIn.SYSTEM_VERSION);

        renderSuccessJson(stock_in);
    }

    @ActionKey(Url.STOCK_IN_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(StockIn.APP_ID, StockIn.STOCK_IN_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        StockIn model = getModel(StockIn.class);

        Integer total = stockInService.countByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_name(model.getApp_id(), model.getWarehouse_id(), model.getStock_in_type(), model.getUser_name());
        List<StockIn> resultList = stockInService.listByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_nameAndLimit(model.getApp_id(), model.getWarehouse_id(), model.getStock_in_type(), model.getUser_name(), getM(), getN());

        for (StockIn result : resultList) {
            result.keep(StockIn.STOCK_IN_ID, StockIn.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.STOCK_IN_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(StockIn.STOCK_IN_ID);

        StockIn model = getModel(StockIn.class);

        StockIn stock_in = stockInService.findByStock_in_id(model.getStock_in_id());

        stock_in.keep(StockIn.STOCK_IN_ID, StockIn.SYSTEM_VERSION);

        renderSuccessJson(stock_in);
    }

}