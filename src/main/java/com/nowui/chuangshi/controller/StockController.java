package com.nowui.chuangshi.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.service.StockService;
import com.nowui.chuangshi.util.Util;

public class StockController extends Controller {

    private final StockService stockService = new StockService();

    @ActionKey(Url.STOCK_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Stock model = getModel(Stock.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = stockService.countByApp_idOrWarehouse_idOrStock_typeOrLikeStock_batchOrLikeProduct_nameOrLikeUser_name(request_app_id, model.getWarehouse_id(), model.getStock_type(), model.getStock_batch(), model.getProduct_name(), model.getUser_name());
        List<Stock> resultList = stockService.listByApp_idOrWarehouse_idOrStock_typeOrLikeStock_batchOrLikeProduct_nameOrLikeUser_nameAndLimit(request_app_id, model.getWarehouse_id(), model.getStock_type(), model.getStock_batch(), model.getProduct_name(), model.getUser_name(), getM(), getN());

        for (Stock result : resultList) {
            result.keep(Stock.STOCK_ID, Stock.PRODUCT_NAME, Stock.USER_NAME, Stock.STOCK_QUANTITY, Stock.STOCK_TYPE, Stock.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.STOCK_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID);

        Stock model = getModel(Stock.class);

        authenticateRequest_app_idAndRequest_user_id();

        Stock stock = stockService.findByStock_id(model.getStock_id());

        authenticateApp_id(stock.getApp_id());

        stock.keep(Stock.STOCK_ID, Stock.SYSTEM_VERSION);

        renderSuccessJson(stock);
    }

    @ActionKey(Url.STOCK_ADMIN_SAVE)
    public void adminSave() {
        validateRequest_app_id();
        validate(Stock.WAREHOUSE_ID, Stock.STOCK_TYPE, Stock.PRODUCT_CATEGORY_ID, Stock.PRODUCT_ID, Stock.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY);

        Stock model = getModel(Stock.class);
        String stock_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        String object_id = model.getObject_id();
        if (StringUtils.isBlank(object_id)) {
            object_id = request_app_id;
        }
        Stock stock = stockService.findByWarehouse_idAndObject_idAndProduct_sku_id(model.getWarehouse_id(), object_id, model.getProduct_sku_id());

        if (stock != null && StringUtils.isNotBlank(stock.getStock_id())) {
            throw new RuntimeException("库存已初始化过");
        }
        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = stockService.save(stock_id, request_app_id, model.getWarehouse_id(), object_id, model.getStock_batch(), model.getStock_type(), model.getProduct_category_id(), model.getProduct_id(), model.getProduct_sku_id(), model.getStock_quantity(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.STOCK_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID, Stock.WAREHOUSE_ID, Stock.OBJECT_ID, Stock.STOCK_TYPE, Stock.PRODUCT_CATEGORY_ID, Stock.PRODUCT_ID, Stock.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Stock stock = stockService.findByStock_id(model.getStock_id());

        authenticateApp_id(stock.getApp_id());

        Boolean result = stockService.updateValidateSystem_version(model.getStock_id(), model.getWarehouse_id(), model.getObject_id(), model.getStock_batch(), model.getStock_type(), model.getProduct_category_id(), model.getProduct_id(), model.getProduct_sku_id(), model.getStock_quantity(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.STOCK_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Stock stock = stockService.findByStock_id(model.getStock_id());

        authenticateApp_id(stock.getApp_id());

        Boolean result = stockService.deleteByStock_idAndSystem_update_user_idValidateSystem_version(model.getStock_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.STOCK_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Stock.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Stock model = getModel(Stock.class);

        Integer total = stockService.countByOrApp_idOrWarehouse_idOrStock_typeOrLikeStock_batchOrLikeProduct_nameOrLikeUser_name(model.getApp_id(), model.getWarehouse_id(), model.getStock_type(), model.getStock_batch(), model.getProduct_name(), model.getUser_name());
        List<Stock> resultList = stockService.listByOrApp_idOrWarehouse_idOrStock_typeOrLikeStock_batchOrLikeProduct_nameOrLikeUser_nameAndLimit(model.getApp_id(), model.getWarehouse_id(), model.getStock_type(), model.getStock_batch(), model.getProduct_name(), model.getUser_name(), getM(), getN());

        for (Stock result : resultList) {
            result.keep(Stock.STOCK_ID, Stock.PRODUCT_NAME, Stock.USER_NAME, Stock.STOCK_QUANTITY, Stock.STOCK_TYPE, Stock.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.STOCK_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID);

        Stock model = getModel(Stock.class);

        Stock stock = stockService.findByStock_id(model.getStock_id());

        stock.keep(Stock.STOCK_ID, Stock.SYSTEM_VERSION);

        renderSuccessJson(stock);
    }

    @ActionKey(Url.STOCK_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Stock.APP_ID, Stock.WAREHOUSE_ID, Stock.OBJECT_ID, Stock.STOCK_TYPE, Stock.PRODUCT_CATEGORY_ID, Stock.PRODUCT_ID, Stock.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY);

        Stock model = getModel(Stock.class);
        String stock_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = stockService.save(stock_id, model.getApp_id(), model.getWarehouse_id(), model.getObject_id(), model.getStock_batch(), model.getStock_type(), model.getProduct_category_id(), model.getProduct_id(), model.getProduct_sku_id(), model.getStock_quantity(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.STOCK_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID, Stock.WAREHOUSE_ID, Stock.OBJECT_ID, Stock.STOCK_TYPE, Stock.PRODUCT_CATEGORY_ID, Stock.PRODUCT_ID, Stock.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);
        String request_user_id = getRequest_user_id();

        Boolean result = stockService.updateValidateSystem_version(model.getStock_id(), model.getWarehouse_id(), model.getObject_id(), model.getStock_batch(), model.getStock_type(), model.getProduct_category_id(), model.getProduct_id(), model.getProduct_sku_id(), model.getStock_quantity(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.STOCK_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);
        String request_user_id = getRequest_user_id();

        Boolean result = stockService.deleteByStock_idAndSystem_update_user_idValidateSystem_version(model.getStock_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}