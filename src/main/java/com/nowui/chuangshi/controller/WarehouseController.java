package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Warehouse;
import com.nowui.chuangshi.service.WarehouseService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class WarehouseController extends Controller {

    private final WarehouseService warehouseService = new WarehouseService();

    @ActionKey(Url.WAREHOUSE_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Warehouse> resultList = warehouseService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Warehouse result : resultList) {
            result.keep(Warehouse.WAREHOUSE_ID, Warehouse.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.WAREHOUSE_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Warehouse.WAREHOUSE_ID);

        Warehouse model = getModel(Warehouse.class);

        authenticateRequest_app_idAndRequest_user_id();

        Warehouse warehouse = warehouseService.findByWarehouse_id(model.getWarehouse_id());

        authenticateApp_id(warehouse.getApp_id());
        authenticateSystem_create_user_id(warehouse.getSystem_create_user_id());

        warehouse.keep(Warehouse.WAREHOUSE_ID, Warehouse.SYSTEM_VERSION);

        renderSuccessJson(warehouse);
    }

    @ActionKey(Url.WAREHOUSE_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Warehouse.WAREHOUSE_CODE, Warehouse.WAREHOUSE_NAME, Warehouse.WAREHOUSE_STATUS, Warehouse.WAREHOUSE_REMARK);

        Warehouse model = getModel(Warehouse.class);
        String warehouse_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = warehouseService.save(warehouse_id, request_app_id, model.getWarehouse_code(), model.getWarehouse_name(), model.getWarehouse_status(), model.getWarehouse_remark(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.WAREHOUSE_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Warehouse.WAREHOUSE_ID, Warehouse.WAREHOUSE_CODE, Warehouse.WAREHOUSE_NAME, Warehouse.WAREHOUSE_STATUS, Warehouse.WAREHOUSE_REMARK, Warehouse.SYSTEM_VERSION);

        Warehouse model = getModel(Warehouse.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Warehouse warehouse = warehouseService.findByWarehouse_id(model.getWarehouse_id());

        authenticateApp_id(warehouse.getApp_id());
        authenticateSystem_create_user_id(warehouse.getSystem_create_user_id());

        Boolean result = warehouseService.updateValidateSystem_version(model.getWarehouse_id(), model.getWarehouse_code(), model.getWarehouse_name(), model.getWarehouse_status(), model.getWarehouse_remark(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.WAREHOUSE_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Warehouse.WAREHOUSE_ID, Warehouse.SYSTEM_VERSION);

        Warehouse model = getModel(Warehouse.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Warehouse warehouse = warehouseService.findByWarehouse_id(model.getWarehouse_id());

        authenticateApp_id(warehouse.getApp_id());
        authenticateSystem_create_user_id(warehouse.getSystem_create_user_id());

        Boolean result = warehouseService.deleteByWarehouse_idAndSystem_update_user_idValidateSystem_version(model.getWarehouse_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.WAREHOUSE_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Warehouse.WAREHOUSE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Warehouse model = getModel(Warehouse.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = warehouseService.countByApp_idOrLikeWarehouse_name(request_app_id, model.getWarehouse_name());
        List<Warehouse> resultList = warehouseService.listByApp_idOrLikeWarehouse_nameAndLimit(request_app_id, model.getWarehouse_name(), getM(), getN());

        for (Warehouse result : resultList) {
            result.keep(Warehouse.WAREHOUSE_ID, Warehouse.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.WAREHOUSE_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Warehouse.WAREHOUSE_ID);

        Warehouse model = getModel(Warehouse.class);

        authenticateRequest_app_idAndRequest_user_id();

        Warehouse warehouse = warehouseService.findByWarehouse_id(model.getWarehouse_id());

        authenticateApp_id(warehouse.getApp_id());

        warehouse.keep(Warehouse.WAREHOUSE_ID, Warehouse.SYSTEM_VERSION);

        renderSuccessJson(warehouse);
    }

    @ActionKey(Url.WAREHOUSE_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.WAREHOUSE_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Warehouse.WAREHOUSE_ID, Warehouse.WAREHOUSE_CODE, Warehouse.WAREHOUSE_NAME, Warehouse.WAREHOUSE_STATUS, Warehouse.WAREHOUSE_REMARK, Warehouse.SYSTEM_VERSION);

        Warehouse model = getModel(Warehouse.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Warehouse warehouse = warehouseService.findByWarehouse_id(model.getWarehouse_id());

        authenticateApp_id(warehouse.getApp_id());

        Boolean result = warehouseService.updateValidateSystem_version(model.getWarehouse_id(), model.getWarehouse_code(), model.getWarehouse_name(), model.getWarehouse_status(), model.getWarehouse_remark(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.WAREHOUSE_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Warehouse.WAREHOUSE_ID, Warehouse.SYSTEM_VERSION);

        Warehouse model = getModel(Warehouse.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Warehouse warehouse = warehouseService.findByWarehouse_id(model.getWarehouse_id());

        authenticateApp_id(warehouse.getApp_id());

        Boolean result = warehouseService.deleteByWarehouse_idAndSystem_update_user_idValidateSystem_version(model.getWarehouse_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.WAREHOUSE_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Warehouse.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Warehouse model = getModel(Warehouse.class);

        Integer total = warehouseService.countByOrApp_idOrLikeWarehouse_name(model.getApp_id(), model.getWarehouse_name());
        List<Warehouse> resultList = warehouseService.listByOrApp_idOrLikeWarehouse_nameAndLimit(model.getApp_id(), model.getWarehouse_name(), getM(), getN());

        for (Warehouse result : resultList) {
            result.keep(Warehouse.WAREHOUSE_ID, Warehouse.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.WAREHOUSE_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Warehouse.WAREHOUSE_ID);

        Warehouse model = getModel(Warehouse.class);

        Warehouse warehouse = warehouseService.findByWarehouse_id(model.getWarehouse_id());

        warehouse.keep(Warehouse.WAREHOUSE_ID, Warehouse.SYSTEM_VERSION);

        renderSuccessJson(warehouse);
    }

    @ActionKey(Url.WAREHOUSE_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Warehouse.APP_ID, Warehouse.WAREHOUSE_CODE, Warehouse.WAREHOUSE_NAME, Warehouse.WAREHOUSE_STATUS, Warehouse.WAREHOUSE_REMARK);

        Warehouse model = getModel(Warehouse.class);
        String warehouse_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = warehouseService.save(warehouse_id, model.getApp_id(), model.getWarehouse_code(), model.getWarehouse_name(), model.getWarehouse_status(), model.getWarehouse_remark(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.WAREHOUSE_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Warehouse.WAREHOUSE_ID, Warehouse.WAREHOUSE_CODE, Warehouse.WAREHOUSE_NAME, Warehouse.WAREHOUSE_STATUS, Warehouse.WAREHOUSE_REMARK, Warehouse.SYSTEM_VERSION);

        Warehouse model = getModel(Warehouse.class);
        String request_user_id = getRequest_user_id();

        Boolean result = warehouseService.updateValidateSystem_version(model.getWarehouse_id(), model.getWarehouse_code(), model.getWarehouse_name(), model.getWarehouse_status(), model.getWarehouse_remark(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.WAREHOUSE_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Warehouse.WAREHOUSE_ID, Warehouse.SYSTEM_VERSION);

        Warehouse model = getModel(Warehouse.class);
        String request_user_id = getRequest_user_id();

        Boolean result = warehouseService.deleteByWarehouse_idAndSystem_update_user_idValidateSystem_version(model.getWarehouse_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}