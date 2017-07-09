package com.nowui.chuangshi.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.service.StockService;
import com.nowui.chuangshi.type.StockType;
import com.nowui.chuangshi.util.Util;

public class AppStockController extends Controller {
    
    private final StockService stockService = new StockService();

    @ActionKey(Url.MEMBER_STOCK_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Stock> resultList = stockService.listByApp_idAndStock_typeAndSystem_create_timeAndLimit(request_app_id, StockType.MEMBER.getValue(), jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Stock result : resultList) {
            result.keep(Stock.STOCK_ID, Stock.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_STOCK_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID);

        Stock model = getModel(Stock.class);

        authenticateRequest_app_idAndRequest_user_id();

        Stock stock = stockService.findByStock_id(model.getStock_id());

        authenticateApp_id(stock.getApp_id());
        authenticateSystem_create_user_id(stock.getSystem_create_user_id());

        stock.keep(Stock.STOCK_ID, Stock.SYSTEM_VERSION);

        renderSuccessJson(stock);
    }

    @ActionKey(Url.MEMBER_STOCK_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID, Stock.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY, Stock.STOCK_ACTION);

        Stock model = getModel(Stock.class);
        
        String stock_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        
        JSONObject jsonObject = getParameterJSONObject();
        String object_id = jsonObject.getString("member_id");

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = stockService.save(stock_id, request_app_id, model.getProduct_sku_id(), object_id, StockType.MEMBER.getValue(), model.getStock_quantity(), model.getStock_action(), model.getStock_status(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID, Stock.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY, Stock.STOCK_ACTION, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Stock stock = stockService.findByStock_id(model.getStock_id());

        authenticateApp_id(stock.getApp_id());
        authenticateSystem_create_user_id(stock.getSystem_create_user_id());

        Boolean result = stockService.updateValidateSystem_version(model.getStock_id(), model.getProduct_sku_id(), model.getObject_id(), model.getStock_type(), model.getStock_quantity(), model.getStock_action(), model.getStock_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Stock stock = stockService.findByStock_id(model.getStock_id());

        authenticateApp_id(stock.getApp_id());
        authenticateSystem_create_user_id(stock.getSystem_create_user_id());

        Boolean result = stockService.deleteByStock_idAndSystem_update_user_idValidateSystem_version(model.getStock_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);
        
        Stock model = getModel(Stock.class);
        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString("user_name");
        String product_name = jsonObject.getString("product_name");

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = stockService.countByApp_idOrStock_typeOrUser_nameOrStock_actionOrLikeProduct_name(request_app_id, StockType.MEMBER.getValue(), user_name, model.getStock_action(), product_name);
        List<Stock> resultList = stockService.listByApp_idOrStock_typeOrUser_nameOrStock_actionOrLikeProduct_nameAndLimit(request_app_id, StockType.MEMBER.getValue(), user_name, model.getStock_action(), product_name, getM(), getN());

        for (Stock result : resultList) {
            result.keep(Stock.STOCK_ID, Product.PRODUCT_NAME, Stock.STOCK_QUANTITY, Stock.STOCK_ACTION, Stock.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_STOCK_ADMIN_FIND)
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

    @ActionKey(Url.MEMBER_STOCK_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.MEMBER_STOCK_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID, Stock.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY, Stock.STOCK_ACTION, Stock.STOCK_STATUS, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Stock stock = stockService.findByStock_id(model.getStock_id());

        authenticateApp_id(stock.getApp_id());

        Boolean result = stockService.updateValidateSystem_version(model.getStock_id(), model.getProduct_sku_id(), model.getObject_id(), model.getStock_type(), model.getStock_quantity(), model.getStock_action(), model.getStock_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_ADMIN_DELETE)
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
    
    @ActionKey(Url.MEMBER_STOCK_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Stock model = getModel(Stock.class);
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString("user_name");
        String product_name = jsonObject.getString("product_name");

        Integer total = stockService.countByOrApp_idOrStock_typeOrUser_nameOrStock_actionOrLikeProduct_name(model.getApp_id(), StockType.MEMBER.getValue(), user_name, model.getStock_action(), product_name);
        List<Stock> resultList = stockService.listByOrApp_idOrStock_typeOrUser_nameOrStock_actionOrLikeProduct_nameAndLimit(model.getApp_id(), StockType.MEMBER.getValue(), user_name, model.getStock_action(), product_name, getM(), getN());

        for (Stock result : resultList) {
            result.keep(Stock.STOCK_ID, Stock.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_STOCK_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID);

        Stock model = getModel(Stock.class);

        Stock stock = stockService.findByStock_id(model.getStock_id());

        stock.keep(Stock.STOCK_ID, Stock.SYSTEM_VERSION);

        renderSuccessJson(stock);
    }

    @ActionKey(Url.MEMBER_STOCK_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Stock.APP_ID, Stock.PRODUCT_SKU_ID, Stock.OBJECT_ID, Stock.STOCK_TYPE, Stock.STOCK_QUANTITY, Stock.STOCK_ACTION, Stock.STOCK_STATUS);

        Stock model = getModel(Stock.class);
        String stock_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = stockService.save(stock_id, model.getApp_id(), model.getProduct_sku_id(), model.getObject_id(), model.getStock_type(), model.getStock_quantity(), model.getStock_action(), model.getStock_status(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID, Stock.PRODUCT_SKU_ID, Stock.OBJECT_ID, Stock.STOCK_TYPE, Stock.STOCK_QUANTITY, Stock.STOCK_ACTION, Stock.STOCK_STATUS, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);
        String request_user_id = getRequest_user_id();

        Boolean result = stockService.updateValidateSystem_version(model.getStock_id(), model.getProduct_sku_id(), model.getObject_id(), model.getStock_type(), model.getStock_quantity(), model.getStock_action(), model.getStock_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Stock.STOCK_ID, Stock.SYSTEM_VERSION);

        Stock model = getModel(Stock.class);
        String request_user_id = getRequest_user_id();

        Boolean result = stockService.deleteByStock_idAndSystem_update_user_idValidateSystem_version(model.getStock_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}
