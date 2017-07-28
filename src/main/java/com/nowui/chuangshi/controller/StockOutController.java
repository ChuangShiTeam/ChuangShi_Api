package com.nowui.chuangshi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.StockIn;
import com.nowui.chuangshi.model.StockOut;
import com.nowui.chuangshi.model.StockOutProductSku;
import com.nowui.chuangshi.service.StockOutProductSkuService;
import com.nowui.chuangshi.service.StockOutService;

public class StockOutController extends Controller {

    private final StockOutService stockOutService = new StockOutService();
    private final StockOutProductSkuService stockOutProductSkuService = new StockOutProductSkuService();

    @ActionKey(Url.STOCK_OUT_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(StockOut.STOCK_OUT_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        StockOut model = getModel(StockOut.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = stockOutService.countByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name(request_app_id, model.getWarehouse_id(), model.getStock_out_type(), model.getStock_out_batch(), model.getUser_name());
        List<StockOut> resultList = stockOutService.listByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit(request_app_id, model.getWarehouse_id(), model.getStock_out_type(), model.getStock_out_batch(), model.getUser_name(), getM(), getN());

        for (StockOut result : resultList) {
            result.keep(StockOut.STOCK_OUT_ID, StockOut.WAREHOUSE_NAME, StockOut.USER_NAME, StockOut.STOCK_OUT_QUANTITY, StockOut.SYSTEM_CREATE_TIME, StockOut.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.STOCK_OUT_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(StockOut.STOCK_OUT_ID);

        StockOut model = getModel(StockOut.class);

        authenticateRequest_app_idAndRequest_user_id();

        StockOut stock_out = stockOutService.findByStock_out_id(model.getStock_out_id());

        authenticateApp_id(stock_out.getApp_id());

        List<StockOutProductSku> stock_out_product_sku_list = stockOutProductSkuService.listByStock_out_id(model.getStock_out_id());
        
        stock_out.keep(StockOut.STOCK_OUT_ID, StockOut.WAREHOUSE_ID, StockOut.STOCK_OUT_QUANTITY, StockOut.SYSTEM_VERSION);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("stock_out", stock_out);
        resultMap.put("stock_out_product_sku_list", stock_out_product_sku_list);
        renderSuccessJson(resultMap);
    }

    @ActionKey(Url.STOCK_OUT_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(StockOut.APP_ID, StockOut.STOCK_OUT_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        StockOut model = getModel(StockOut.class);

        Integer total = stockOutService.countByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name(model.getApp_id(), model.getWarehouse_id(), model.getStock_out_type(), model.getStock_out_batch(), model.getUser_name());
        List<StockOut> resultList = stockOutService.listByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit(model.getApp_id(), model.getWarehouse_id(), model.getStock_out_type(), model.getStock_out_batch(), model.getUser_name(), getM(), getN());

        for (StockOut result : resultList) {
            result.keep(StockOut.STOCK_OUT_ID, StockOut.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.STOCK_OUT_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(StockOut.STOCK_OUT_ID);

        StockOut model = getModel(StockOut.class);

        StockOut stock_out = stockOutService.findByStock_out_id(model.getStock_out_id());

        stock_out.keep(StockOut.STOCK_OUT_ID, StockOut.SYSTEM_VERSION);

        renderSuccessJson(stock_out);
    }


}