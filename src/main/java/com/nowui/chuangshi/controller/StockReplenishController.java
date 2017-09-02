package com.nowui.chuangshi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nowui.chuangshi.util.ValidateUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.StockReplenish;
import com.nowui.chuangshi.model.StockReplenishProductSku;
import com.nowui.chuangshi.service.StockReplenishProductSkuService;
import com.nowui.chuangshi.service.StockReplenishService;

public class StockReplenishController extends Controller {

    private final StockReplenishService stockReplenishService = new StockReplenishService();
    
    private final StockReplenishProductSkuService stockReplenishProductSkuService = new StockReplenishProductSkuService();
    @ActionKey(Url.STOCK_REPLENISH_LIST)
    public void list() {
        validateRequest_app_id();
        validate(StockReplenish.STOCK_REPLENISH_TYPE, Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<StockReplenish> resultList = stockReplenishService.listByApp_idAndStock_replenish_typeAndSystem_create_timeAndLimit(request_app_id, jsonObject.getString(StockReplenish.STOCK_REPLENISH_TYPE), jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (StockReplenish result : resultList) {
            result.keep(StockReplenish.STOCK_REPLENISH_ID, StockReplenish.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.STOCK_REPLENISH_FIND)
    public void find() {
        validateRequest_app_id();
        validate(StockReplenish.STOCK_REPLENISH_ID);

        StockReplenish model = getModel(StockReplenish.class);

        authenticateRequest_app_idAndRequest_user_id();

        StockReplenish stock_replenish = stockReplenishService.findByStock_replenish_id(model.getStock_replenish_id());

        authenticateApp_id(stock_replenish.getApp_id());
        authenticateSystem_create_user_id(stock_replenish.getSystem_create_user_id());

        stock_replenish.keep(StockReplenish.STOCK_REPLENISH_ID, StockReplenish.SYSTEM_VERSION);

        renderSuccessJson(stock_replenish);
    }

    @ActionKey(Url.STOCK_REPLENISH_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(StockReplenish.WAREHOUSE_ID, StockReplenish.STOCK_REPLENISH_TYPE, StockReplenish.STOCK_REPLENISH_ACTION);

        StockReplenish model = getModel(StockReplenish.class);
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        String object_id = model.getObject_id();
        if (ValidateUtil.isNullOrEmpty(object_id)) {
            object_id = request_app_id;
        }
        JSONObject jsonObject = getParameterJSONObject();
        JSONArray list = jsonObject.getJSONArray("stock_replenish_product_sku_list");
        if (list == null || list.size() == 0) {
            throw new RuntimeException("报损报溢明细不能为空");
        }
        List<StockReplenishProductSku> stock_replenish_product_sku_list = new ArrayList<StockReplenishProductSku>();
        for (int i = 0; i < list.size(); i++) {
            StockReplenishProductSku stockReplenishProductSku = list.getJSONObject(i).toJavaObject(StockReplenishProductSku.class);
            stock_replenish_product_sku_list.add(stockReplenishProductSku);
        }
        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = stockReplenishService.save(request_app_id, model.getWarehouse_id(), object_id, model.getStock_replenish_type(), model.getStock_replenish_action(), stock_replenish_product_sku_list, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.STOCK_REPLENISH_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(StockReplenish.STOCK_REPLENISH_ID, StockReplenish.WAREHOUSE_ID, StockReplenish.OBJECT_ID, StockReplenish.STOCK_REPLENISH_TYPE, StockReplenish.STOCK_REPLENISH_QUANTITY, StockReplenish.STOCK_REPLENISH_ACTION, StockReplenish.STOCK_REPLENISH_STATUS, StockReplenish.SYSTEM_VERSION);

        StockReplenish model = getModel(StockReplenish.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        StockReplenish stock_replenish = stockReplenishService.findByStock_replenish_id(model.getStock_replenish_id());

        authenticateApp_id(stock_replenish.getApp_id());
        authenticateSystem_create_user_id(stock_replenish.getSystem_create_user_id());

        Boolean result = stockReplenishService.updateValidateSystem_version(model.getStock_replenish_id(), model.getWarehouse_id(), model.getObject_id(), model.getStock_replenish_type(), model.getStock_replenish_quantity(), model.getStock_replenish_action(), model.getStock_replenish_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.STOCK_REPLENISH_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(StockReplenish.STOCK_REPLENISH_ID, StockReplenish.SYSTEM_VERSION);

        StockReplenish model = getModel(StockReplenish.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        StockReplenish stock_replenish = stockReplenishService.findByStock_replenish_id(model.getStock_replenish_id());

        authenticateApp_id(stock_replenish.getApp_id());
        authenticateSystem_create_user_id(stock_replenish.getSystem_create_user_id());

        Boolean result = stockReplenishService.deleteByStock_replenish_idAndSystem_update_user_idValidateSystem_version(model.getStock_replenish_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.STOCK_REPLENISH_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(StockReplenish.STOCK_REPLENISH_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        StockReplenish model = getModel(StockReplenish.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = stockReplenishService.countByApp_idOrWarehouse_idAndStock_replenish_typeOrLikeStock_replenish_batchOrLikeUser_name(request_app_id, model.getWarehouse_id(), model.getStock_replenish_type(), model.getStock_replenish_batch(), model.getUser_name());
        List<StockReplenish> resultList = stockReplenishService.listByApp_idOrWarehouse_idAndStock_replenish_typeOrLikeStock_replenish_batchOrLikeUser_nameAndLimit(request_app_id, model.getWarehouse_id(), model.getStock_replenish_type(), model.getStock_replenish_batch(), model.getUser_name(), getM(), getN());

        for (StockReplenish result : resultList) {
            result.keep(StockReplenish.STOCK_REPLENISH_ID, StockReplenish.STOCK_REPLENISH_QUANTITY, StockReplenish.STOCK_REPLENISH_ACTION, StockReplenish.USER_NAME, StockReplenish.WAREHOUSE_NAME, StockReplenish.SYSTEM_CREATE_TIME, StockReplenish.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.STOCK_REPLENISH_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(StockReplenish.STOCK_REPLENISH_ID);

        StockReplenish model = getModel(StockReplenish.class);

        authenticateRequest_app_idAndRequest_user_id();

        StockReplenish bean = stockReplenishService.findByStock_replenish_id(model.getStock_replenish_id());

        authenticateApp_id(bean.getApp_id());
        
        StockReplenish stock_replenish = stockReplenishService.findByStock_replenish_idAndStock_replenish_type(bean.getStock_replenish_id(), bean.getStock_replenish_type());
        
        List<StockReplenishProductSku> stock_replenish_product_sku_list = stockReplenishProductSkuService.listByStock_replenish_id(stock_replenish.getStock_replenish_id());

        stock_replenish.keep(StockReplenish.STOCK_REPLENISH_ID, StockReplenish.STOCK_REPLENISH_QUANTITY, StockReplenish.STOCK_REPLENISH_ACTION, StockReplenish.USER_NAME, StockReplenish.WAREHOUSE_ID, StockReplenish.SYSTEM_VERSION);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("stock_replenish", stock_replenish);
        resultMap.put("stock_replenish_product_sku_list", stock_replenish_product_sku_list);
        renderSuccessJson(resultMap);
    }

    @ActionKey(Url.STOCK_REPLENISH_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.STOCK_REPLENISH_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(StockReplenish.STOCK_REPLENISH_ID, StockReplenish.WAREHOUSE_ID, StockReplenish.OBJECT_ID, StockReplenish.STOCK_REPLENISH_TYPE, StockReplenish.STOCK_REPLENISH_QUANTITY, StockReplenish.STOCK_REPLENISH_ACTION, StockReplenish.STOCK_REPLENISH_STATUS, StockReplenish.SYSTEM_VERSION);

        StockReplenish model = getModel(StockReplenish.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        StockReplenish stock_replenish = stockReplenishService.findByStock_replenish_id(model.getStock_replenish_id());

        authenticateApp_id(stock_replenish.getApp_id());

        Boolean result = stockReplenishService.updateValidateSystem_version(model.getStock_replenish_id(), model.getWarehouse_id(), model.getObject_id(), model.getStock_replenish_type(), model.getStock_replenish_quantity(), model.getStock_replenish_action(), model.getStock_replenish_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.STOCK_REPLENISH_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(StockReplenish.STOCK_REPLENISH_ID, StockReplenish.SYSTEM_VERSION);

        StockReplenish model = getModel(StockReplenish.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        StockReplenish stock_replenish = stockReplenishService.findByStock_replenish_id(model.getStock_replenish_id());

        authenticateApp_id(stock_replenish.getApp_id());

        Boolean result = stockReplenishService.deleteByStock_replenish_idAndSystem_update_user_idValidateSystem_version(model.getStock_replenish_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.STOCK_REPLENISH_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(StockReplenish.APP_ID, StockReplenish.STOCK_REPLENISH_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        StockReplenish model = getModel(StockReplenish.class);

        Integer total = stockReplenishService.countByOrApp_idOrWarehouse_idAndStock_replenish_typeOrLikeStock_replenish_batchOrLikeUser_name(model.getApp_id(), model.getWarehouse_id(), model.getStock_replenish_type(), model.getStock_replenish_batch(), model.getUser_name());
        List<StockReplenish> resultList = stockReplenishService.listByOrApp_idOrWarehouse_idAndStock_replenish_typeOrLikeStock_replenish_batchOrLikeUser_nameAndLimit(model.getApp_id(), model.getWarehouse_id(), model.getStock_replenish_type(), model.getStock_replenish_batch(), model.getUser_name(), getM(), getN());

        for (StockReplenish result : resultList) {
            result.keep(StockReplenish.STOCK_REPLENISH_ID, StockReplenish.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.STOCK_REPLENISH_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(StockReplenish.STOCK_REPLENISH_ID);

        StockReplenish model = getModel(StockReplenish.class);

        StockReplenish stock_replenish = stockReplenishService.findByStock_replenish_id(model.getStock_replenish_id());

        stock_replenish.keep(StockReplenish.STOCK_REPLENISH_ID, StockReplenish.SYSTEM_VERSION);

        renderSuccessJson(stock_replenish);
    }

    @ActionKey(Url.STOCK_REPLENISH_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(StockReplenish.APP_ID, StockReplenish.WAREHOUSE_ID, StockReplenish.OBJECT_ID, StockReplenish.STOCK_REPLENISH_TYPE, StockReplenish.STOCK_REPLENISH_QUANTITY, StockReplenish.STOCK_REPLENISH_ACTION, StockReplenish.STOCK_REPLENISH_STATUS);

        StockReplenish model = getModel(StockReplenish.class);
        String request_user_id = getRequest_user_id();
        
        String object_id = model.getObject_id();
        if (ValidateUtil.isNullOrEmpty(object_id)) {
            object_id = model.getApp_id();
        }
        JSONObject jsonObject = getParameterJSONObject();
        JSONArray list = jsonObject.getJSONArray("stock_replenish_product_sku_list");
        if (list == null || list.size() == 0) {
            throw new RuntimeException("报损报溢明细不能为空");
        }
        List<StockReplenishProductSku> stock_replenish_product_sku_list = new ArrayList<StockReplenishProductSku>();
        for (int i = 0; i < list.size(); i++) {
            StockReplenishProductSku stockReplenishProductSku = list.getJSONObject(i).toJavaObject(StockReplenishProductSku.class);
            stock_replenish_product_sku_list.add(stockReplenishProductSku);
        }
        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = stockReplenishService.save(model.getApp_id(), model.getWarehouse_id(), object_id, model.getStock_replenish_type(), model.getStock_replenish_action(), stock_replenish_product_sku_list, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.STOCK_REPLENISH_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(StockReplenish.STOCK_REPLENISH_ID, StockReplenish.WAREHOUSE_ID, StockReplenish.OBJECT_ID, StockReplenish.STOCK_REPLENISH_TYPE, StockReplenish.STOCK_REPLENISH_QUANTITY, StockReplenish.STOCK_REPLENISH_ACTION, StockReplenish.STOCK_REPLENISH_STATUS, StockReplenish.SYSTEM_VERSION);

        StockReplenish model = getModel(StockReplenish.class);
        String request_user_id = getRequest_user_id();

        Boolean result = stockReplenishService.updateValidateSystem_version(model.getStock_replenish_id(), model.getWarehouse_id(), model.getObject_id(), model.getStock_replenish_type(), model.getStock_replenish_quantity(), model.getStock_replenish_action(), model.getStock_replenish_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.STOCK_REPLENISH_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(StockReplenish.STOCK_REPLENISH_ID, StockReplenish.SYSTEM_VERSION);

        StockReplenish model = getModel(StockReplenish.class);
        String request_user_id = getRequest_user_id();

        Boolean result = stockReplenishService.deleteByStock_replenish_idAndSystem_update_user_idValidateSystem_version(model.getStock_replenish_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}